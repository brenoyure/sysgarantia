package br.albatross.sysgarantia.messaging;

import java.io.StringReader;
import java.util.concurrent.CompletionStage;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;

import br.albatross.sysgarantia.models.Email;
import br.albatross.sysgarantia.models.SolicitacaoGarantia;
import br.albatross.sysgarantia.models.SolicitacaoGarantia.Status;
import br.albatross.sysgarantia.repositories.EmailRepository;
import br.albatross.sysgarantia.services.garantia.QuarkusMailerService;
import br.albatross.sysgarantia.services.garantia.SolicitacaoGarantiaService;
import jakarta.enterprise.context.ApplicationScoped;

import jakarta.inject.Inject;

import jakarta.json.Json;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

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
    EmailRepository emailRepository;    

    @Inject
    QuarkusMailerService emailService;

    @Inject
    SolicitacaoGarantiaService solicitacaoGarantiaService;

    @Transactional(value = TxType.NOT_SUPPORTED)
    @Incoming("in-sysgarantia-novas-solicitacoes-channel")
    public CompletionStage<Void> enviarEmailDeGarantia(Message<String> emailGarantiaAsJson) {
        long emailId = Long.parseLong(Json.createReader(new StringReader(emailGarantiaAsJson.getPayload())).readObject().get("email_id").toString());

        Email email = obterEmailPorId(emailId);

        if (solicitacaoJaFoiEnviada(email.getSolicitacaoGarantia())) {
            return emailGarantiaAsJson.ack();
        }

        return emailService
                .enviar(email)
                .thenRun(() -> {
                    marcarSolicitacaoComoEnviada(email.getSolicitacaoGarantia().getId());
                    emailGarantiaAsJson.ack();
                })
                .exceptionally(e -> {
                    emailGarantiaAsJson.nack(e);
                    e.printStackTrace();
                    return null;
                });
    }

    @Transactional
    Email obterEmailPorId(long emailId) {
        return emailRepository.findById(emailId).orElseThrow();
    }

    @Transactional
    void marcarSolicitacaoComoEnviada(long solicitacaoGarantiaId) {
        solicitacaoGarantiaService.marcarComoEnviada(solicitacaoGarantiaId);
    }

    boolean solicitacaoJaFoiEnviada(SolicitacaoGarantia solicitacaoGarantia) {
        return solicitacaoGarantia.getStatus().equals(Status.ENVIADA);
    }

}

//String solicitacaoGarantiaOutputJsonMessage(Email email) {
//return  
//Json
// .createObjectBuilder()
//   .add("email_id",                email.getId())
//   .add("solicitacao_garantia_id", email.getSolicitacaoGarantia().getId())
//   .add("assunto",                 email.getAssunto())
//   .add("numero_de_serie",         email.getSolicitacaoGarantia().getNumeroDeSerie())
//   .add("cliente",                 email.getSolicitacaoGarantia().getCliente().getNome())
//   .add("fornecedor",              email.getSolicitacaoGarantia().getFornecedor().getNome())
// .build()
// .toString();
//}