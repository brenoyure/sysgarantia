package br.albatross.sysgarantia.services.garantia;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Set;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.context.ManagedExecutor;
import org.hibernate.engine.jdbc.BlobProxy;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;

import br.albatross.sysgarantia.dto.garantia.DadosParaNovaSolicitacaoDeGarantia;
import br.albatross.sysgarantia.models.Anexo;
import br.albatross.sysgarantia.models.Email;
import br.albatross.sysgarantia.models.SolicitacaoGarantia;
import br.albatross.sysgarantia.repositories.EmailRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class EmailGarantiaService {

    @Inject
    ManagedExecutor executorService;    

    @Inject
    @ConfigProperty(name = "quarkus.mailer.from")
    String remetente;

    @Inject
    EmailRepository emailRepository;

    @Inject
    QuarkusMailerService quarkusMailerService;    

    @Inject
    FormularioGenerator formularioGenerator;

    public void enviarEmail(DadosParaNovaSolicitacaoDeGarantia dadosSolicitacao, SolicitacaoGarantia solicitacaoGarantia) {
        Email emailDeGarantia = criarNovoEmailDeGarantia(dadosSolicitacao, solicitacaoGarantia);
        quarkusMailerService.enviar(emailDeGarantia);
    }

    public Email criarNovoEmailDeGarantia(DadosParaNovaSolicitacaoDeGarantia dadosSolicitacao, SolicitacaoGarantia solicitacaoGarantia) {
        Email email = 
                new Email(remetente, 
                          solicitacaoGarantia.getFornecedor().getEmails(), 
                          dadosSolicitacao.getAssunto(),
                          dadosSolicitacao.getCorpoDoEmail(),
                          dadosSolicitacao.getCopiaPara(), 
                          dadosSolicitacao.getCopiaOculta(), 
                          solicitacaoGarantia);

        Set<Anexo> anexos = email.getAnexos();

        executorService
            .supplyAsync(() -> gerarAnexoDoFormulario(formularioGenerator.getFormulario(solicitacaoGarantia), email))
            .thenAccept(anexos::add)
            .join();

        if (dadosSolicitacao.getAnexo() != null) {
            executorService
                .supplyAsync(() -> getAnexoFromInputPart(dadosSolicitacao.getAnexo(), email))
                .thenAccept(anexos::add)
                .join();
        }

        emailRepository.persist(email);
        email.getSolicitacaoGarantia().marcarAgendada();

        return email;

    }

    private Anexo gerarAnexoDoFormulario(File formularioFile, Email email) {
        Anexo anexo = new Anexo();
        anexo.setNome(formularioFile.getName());
        anexo.setEmail(email);
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(formularioFile))) {
            anexo.setArquivo(BlobProxy.generateProxy(bis.readAllBytes()));
            return anexo;
        }
        catch (Exception e) { throw new RuntimeException(e); }
    }

    private Anexo getAnexoFromInputPart(InputPart submittedFile, Email email) {
        Anexo anexo = new Anexo();
        anexo.setNome(submittedFile.getFileName());
        anexo.setEmail(email);
        try (BufferedInputStream bis = new BufferedInputStream(submittedFile.getBody())) {
            anexo.setArquivo(BlobProxy.generateProxy(submittedFile.getBody().readAllBytes()));
            return anexo;
        }
        catch (Exception e) { throw new RuntimeException(e); }
    }

}
