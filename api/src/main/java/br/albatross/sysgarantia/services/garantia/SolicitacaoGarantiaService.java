package br.albatross.sysgarantia.services.garantia;

import br.albatross.sysgarantia.dto.garantia.DadosParaNovaSolicitacaoDeGarantia;

import br.albatross.sysgarantia.models.Email;
import br.albatross.sysgarantia.models.SolicitacaoGarantia;
import br.albatross.sysgarantia.models.SolicitacaoGarantia.Status;

import br.albatross.sysgarantia.repositories.ClienteRepository;
import br.albatross.sysgarantia.repositories.DescricaoProblemaRepository;
import br.albatross.sysgarantia.repositories.EmailRepository;
import br.albatross.sysgarantia.repositories.FornecedorRepository;
import br.albatross.sysgarantia.repositories.SolicitacaoGarantiaRepository;

import jakarta.enterprise.context.ApplicationScoped;

import jakarta.inject.Inject;

import jakarta.transaction.Transactional;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

/**
 *
 * CDI Bean responsável por colocar os <code>Email</code>s de Garantia na fila
 * de mensagens
 *
 * @author breno.brito
 */
@ApplicationScoped
public class SolicitacaoGarantiaService {

    @Inject
    ClienteRepository clienteRepository;

    @Inject
    FornecedorRepository fornecedorRepository;

    @Inject
    DescricaoProblemaRepository descricaoProblemaRepository;

    @Inject
    EmailRepository emailRepository;

    @Inject
    SolicitacaoGarantiaRepository solicitacaoGarantiaRepository;

    @Transactional
    public SolicitacaoGarantia solicitarGarantia(@Valid DadosParaNovaSolicitacaoDeGarantia dadosSolicitacao) {
        validaExistenciaDoClienteFornecedorEDescricaoProblema(dadosSolicitacao);
        SolicitacaoGarantia solicitacaoGarantia = criaSolicitacaoGarantiaAPartirDeDtoEPersiste(dadosSolicitacao);
        criaEmailAPartirDeDtoEPersiste(dadosSolicitacao, solicitacaoGarantia); 
        return solicitacaoGarantia;
    }

    private void validaExistenciaDoClienteFornecedorEDescricaoProblema(DadosParaNovaSolicitacaoDeGarantia dadosSolicitacao) {

        if (!clienteRepository.existsById(dadosSolicitacao.getClienteId())) {
            throw new ValidationException("Cliente com o ID informado não encontrado");
        }

        if (!fornecedorRepository.existsById(dadosSolicitacao.getFornecedorId())) {
            throw new ValidationException("Fornecedor com o ID informado não encontrado");
        }

        if (!descricaoProblemaRepository.existsById(dadosSolicitacao.getDescricaoProblemaId())) {
            throw new ValidationException("Descrição Problema com o ID informado não encontrado");
        }

    }

    private SolicitacaoGarantia criaSolicitacaoGarantiaAPartirDeDtoEPersiste(DadosParaNovaSolicitacaoDeGarantia dadosSolicitacao) {
        return solicitacaoGarantiaRepository.persist(
                new SolicitacaoGarantia(
                        dadosSolicitacao.getNumeroDeSerie(), 
                        clienteRepository.getReferenceById(dadosSolicitacao.getClienteId()), 
                        fornecedorRepository.getReferenceById(dadosSolicitacao.getFornecedorId()), 
                        descricaoProblemaRepository.getReferenceById(dadosSolicitacao.getDescricaoProblemaId()), 
                        Status.CRIADA));
    }

    private Email criaEmailAPartirDeDtoEPersiste(DadosParaNovaSolicitacaoDeGarantia dadosSolicitacao, SolicitacaoGarantia solicitacaoGarantia) {
        return emailRepository.persist(new Email(
                                            solicitacaoGarantia.getCliente().getEmailsParaContato(), 
                                            solicitacaoGarantia.getFornecedor().getEmails(), 
                                            dadosSolicitacao.getCopiaPara(), 
                                            dadosSolicitacao.getCopiaOculta(), 
                                            solicitacaoGarantia));
    }

}
