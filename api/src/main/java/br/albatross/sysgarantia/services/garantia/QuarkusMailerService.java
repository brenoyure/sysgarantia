package br.albatross.sysgarantia.services.garantia;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import br.albatross.sysgarantia.models.Anexo;
import br.albatross.sysgarantia.models.Email;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.reactive.ReactiveMailer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class QuarkusMailerService {

    @Inject
    ReactiveMailer mailer;

    @Inject
    @ConfigProperty(name="quarkus-mailer-service-timeout-seconds", defaultValue="30")
    long timeOutSeconds;

    public CompletionStage<Void> enviar(Email email) {
        return mailer.send(criarMailerMailAPartirDoEmail(email))
                .ifNoItem().after(Duration.ofSeconds(timeOutSeconds)).fail()
                .subscribeAsCompletionStage();
    }

    private Mail criarMailerMailAPartirDoEmail(Email email) {
        Mail mail = 
                new Mail()
                    .setSubject(email.getAssunto())
                    .setText(email.getCorpo())
                    .setTo(enderecosStringParaList(email.getDestinatario()));

        enderecosStringParaList(
                email.getSolicitacaoGarantia().getCliente().getEmailsParaContato())
                .forEach(mail::addReplyTo);

        if (email.getCopiaPara() != null && !email.getCopiaPara().isBlank()) {
            mail.setCc(enderecosStringParaList(email.getCopiaPara()));
        }

        if (email.getCopiaOculta() != null && !email.getCopiaOculta().isBlank()) {
            mail.setBcc(enderecosStringParaList(email.getCopiaOculta()));
        }

        email.getAnexos().forEach(anexo ->
            mail.addAttachment(anexo.getNome(), criarFileAPartirDoAnexo(anexo), null));

        return mail;

    }

    private File criarFileAPartirDoAnexo(Anexo anexo) {
        try {
            File anexoTempFile = File.createTempFile(anexo.getNome(), "");
            try (BufferedInputStream bis = new BufferedInputStream(anexo.getArquivo().getBinaryStream())) {
                try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(anexoTempFile))) {
                    bis.transferTo(bos);
                }
            }

            return anexoTempFile;

        } catch (IOException | SQLException e) { throw new RuntimeException(e); }

    }

    private List<String> enderecosStringParaList(String enderecos) {
        return Stream.of(enderecos.split(",")).map(String::trim).toList();
    }

}
