package br.albatross.sysgarantia.messaging;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Message;

import br.albatross.sysgarantia.models.Email;
import br.albatross.sysgarantia.models.SolicitacaoGarantia;

import jakarta.enterprise.context.ApplicationScoped;

import jakarta.inject.Inject;

import jakarta.json.Json;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@ApplicationScoped
public class MessagesProducerService {

    @Inject
    @Channel("out-sysgarantia-novas-solicitacoes-channel")
    Emitter<String> novasSolicitacoesMessageProducer;

    @Transactional(TxType.NOT_SUPPORTED)
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
        marcarComoAgendada(solicitacao);
    }

    @Transactional
    void marcarComoAgendada(SolicitacaoGarantia solicitacaoGarantia) {
        solicitacaoGarantia.marcarAgendada();
    }

}
