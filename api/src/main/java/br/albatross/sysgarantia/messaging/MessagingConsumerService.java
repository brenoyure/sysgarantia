package br.albatross.sysgarantia.messaging;

import java.util.Set;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.context.ManagedExecutor;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;

import br.albatross.sysgarantia.models.Anexo;
import br.albatross.sysgarantia.models.Email;
import br.albatross.sysgarantia.repositories.EmailRepository;
import br.albatross.sysgarantia.services.garantia.QuarkusMailerService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
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
    public CompletionStage<Void> enviarEmailDeGarantia(Message<Email> emailGarantiaMessage) {
        return executor.supplyAsync(() -> recuperarAnexosPeloIdDoEmail(emailGarantiaMessage.getPayload().getId()))
                 .thenApplyAsync(anexos -> {
                     Email email = emailGarantiaMessage.getPayload();
                     email.setAnexos(anexos);
                     return email;
                 })
                 .thenAcceptAsync(emailService::enviar).orTimeout(timeOutSeconds, TimeUnit.SECONDS)
                 .thenRunAsync(emailGarantiaMessage::ack)
                 .exceptionally(e -> {
                     emailGarantiaMessage.nack(e).thenRunAsync(e::printStackTrace);
                     return null;
                 });
    }

    @Transactional
    Set<Anexo> recuperarAnexosPeloIdDoEmail(long id) {
        return emailRepository.findAnexosById(id);
    }

}
