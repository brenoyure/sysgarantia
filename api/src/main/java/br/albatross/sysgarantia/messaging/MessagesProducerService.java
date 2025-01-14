package br.albatross.sysgarantia.messaging;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Message;

import br.albatross.sysgarantia.models.Email;
import br.albatross.sysgarantia.models.SolicitacaoGarantia;
import br.albatross.sysgarantia.repositories.EmailRepository;
import br.albatross.sysgarantia.services.garantia.QuarkusMailerService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.Json;

@ApplicationScoped
public class MessagesProducerService {

    @Inject
    @Channel("out-sysgarantia-novas-solicitacoes-channel")
    Emitter<String> novasSolicitacoesMessageProducer;

    @Inject
    EmailRepository emailRepository;    

    @Inject
    QuarkusMailerService emailService; 

    public void send(Email emailGarantia) {
        SolicitacaoGarantia solicitacao = emailGarantia.getSolicitacaoGarantia();
        String emailGarantiaJson = 
                Json
                  .createObjectBuilder()
                  .add("email_id",        emailGarantia.getId())
                  .add("assunto",         emailGarantia.getAssunto())
                  .add("numero_de_serie", solicitacao.getNumeroDeSerie())
                  .add("fornecedor",      solicitacao.getFornecedor().getNome())
                  .add("cliente",         solicitacao.getCliente().getNome())
                .build().toString();

        novasSolicitacoesMessageProducer.send(Message.of(emailGarantiaJson));

    }

}
