package br.albatross.sysgarantia.services.garantia;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.hibernate.engine.jdbc.BlobProxy;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;

import br.albatross.sysgarantia.dto.garantia.DadosParaNovaSolicitacaoDeGarantia;
import br.albatross.sysgarantia.models.Anexo;
import br.albatross.sysgarantia.models.Email;
import br.albatross.sysgarantia.models.SolicitacaoGarantia;
import br.albatross.sysgarantia.repositories.EmailRepository;
import io.quarkus.mailer.Mailer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class EmailGarantiaService {

    @Inject
    Mailer mailer;    

    @Inject
    @ConfigProperty(name = "quarkus.mailer.from")
    String remetente;

    @Inject
    EmailRepository emailRepository;

    public void enviarEmail(Email email) {
        
    }

    public Email criarNovoEmailDeGarantia(DadosParaNovaSolicitacaoDeGarantia dadosSolicitacao, SolicitacaoGarantia solicitacaoGarantia) {
        try {
            Email email = 
                    new Email(remetente, 
                              solicitacaoGarantia.getFornecedor().getEmails(), 
                              dadosSolicitacao.getAssunto(),
                              dadosSolicitacao.getCorpoDoEmail(),
                              dadosSolicitacao.getCopiaPara(), 
                              dadosSolicitacao.getCopiaOculta(), 
                              solicitacaoGarantia);

            if (dadosSolicitacao.getAnexo() != null) {
                Anexo anexoDaSolicitacao = getAnexoFromInputPart(dadosSolicitacao.getAnexo(), email);
                email.getAnexos().add(anexoDaSolicitacao);
            }

            emailRepository.persist(email);

        } catch(IOException e) { throw new RuntimeException(e); }

    }

    private Anexo getAnexoFromInputPart(InputPart submittedFile, Email email) throws IOException {
        Anexo anexo = new Anexo();
        anexo.setNome(submittedFile.getFileName());
        anexo.setArquivo(getBlobFromInputStream(submittedFile.getBody()));
        anexo.setEmail(email);
        return anexo;
    }

    private Blob getBlobFromInputStream(InputStream inputStream) throws IOException {
        int offset = 0;
        int read = 9;
        byte[] buffer = new byte[1024 * 10];
        try (BufferedInputStream bis = new BufferedInputStream(inputStream)) {
            Blob blob = BlobProxy.generateProxy(bis.readNBytes(buffer.length));
            while(bis.available() > 0) {
                read = bis.read(buffer, offset, buffer.length - offset);
                offset = buffer.length - 1;
            }
        
        }

    }

}



































