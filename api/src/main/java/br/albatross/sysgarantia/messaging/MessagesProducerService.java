package br.albatross.sysgarantia.messaging;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Message;

import br.albatross.sysgarantia.models.Email;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@ApplicationScoped
public class MessagesProducerService {

    @Inject
    @Channel("out-sysgarantia-novas-solicitacoes-channel")
    Emitter<Email> novasSolicitacoesMessageProducer;

    @Transactional(TxType.NOT_SUPPORTED)
    public void send(Email emailGarantia) {
        novasSolicitacoesMessageProducer.send(Message.of(emailGarantia));

    }

}
