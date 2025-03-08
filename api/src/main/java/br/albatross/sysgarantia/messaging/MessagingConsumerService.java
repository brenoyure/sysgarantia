package br.albatross.sysgarantia.messaging;

import java.io.StringReader;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.context.ManagedExecutor;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;

import br.albatross.sysgarantia.models.Email;
import br.albatross.sysgarantia.repositories.EmailRepository;
import br.albatross.sysgarantia.services.garantia.QuarkusMailerService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonReader;
import jakarta.transaction.Transactional;

/**
 * <p>CDI Responsável por receber as mensagens de novas solicitações de garantia.</p>
 * 
 *  A implementação atual: 
 *  <ol>
 *    <li>Extrai o Id do e-mail do payload</li>
 *    <li>Obtém os dados do e-mail a partir do <code>EmailRepository</code></li>
 *    <li>Valida se esta Solicitação de Garantia já foi enviada, através de seu status</li>
 *    <li>Caso sim, será dado um <i>acknowledge</i> na mensagem, evitando reenvio</li>
 *    <li>Caso não, será realizada a tentativa de envio, em caso de sucesso, 
 *    a solicitação será marcada como enviada e será dado um <i>acknowledge</i> na mensagem.</li>
 *  </ol>
 * 
 * 
 * @author breno.brito
 */
@ApplicationScoped
public class MessagingConsumerService {

    private static final String EMAIL_ID_JSON_FIELD = "email_id";

    @Inject
    ManagedExecutor executor;    

    @Inject
    EmailRepository emailRepository;    

    @Inject
    QuarkusMailerService emailService;

    @Inject
    @ConfigProperty(name="quarkus-mailer-service-timeout-seconds", defaultValue="30")
    long timeOutSeconds;

    @Incoming("in-sysgarantia-novas-solicitacoes-channel")
    public CompletionStage<Void> enviarEmailDeGarantia(Message<String> emailGarantiaAsJson) {
        return executor.supplyAsync(() -> this.obterIdDoEmailAPartirDoMessagePayload(emailGarantiaAsJson))
                 .thenApplyAsync(this::buscarEmailPorId)
                 .thenAcceptAsync(emailService::enviar).orTimeout(timeOutSeconds, TimeUnit.SECONDS)
                 .thenRunAsync(emailGarantiaAsJson::ack)
                 .exceptionally(e -> {
                     emailGarantiaAsJson.nack(e).thenRunAsync(e::printStackTrace);
                     return null;
                 });
    }

    long obterIdDoEmailAPartirDoMessagePayload(Message<String> message) {
        try (JsonReader jsonReader = Json.createReader(new StringReader(message.getPayload()))) {
            return jsonReader.readObject().getJsonNumber(EMAIL_ID_JSON_FIELD).longValueExact();          
        }
    }

    @Transactional
    Email buscarEmailPorId(Long id) {
        return emailRepository.getById((id));
    }

}
