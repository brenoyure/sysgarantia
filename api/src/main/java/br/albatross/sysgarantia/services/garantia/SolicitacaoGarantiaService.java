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
    EmailRepository emailRepository;    

    @Inject
    ClienteRepository clienteRepository;

    @Inject
    FornecedorRepository fornecedorRepository;

    @Inject
    DescricaoProblemaRepository descricaoProblemaRepository;

    @Inject
    SolicitacaoGarantiaRepository solicitacaoGarantiaRepository;    

    @Transactional
    public void solicitarGarantia(@Valid DadosParaNovaSolicitacaoDeGarantia dadosSolicitacao) {
        validaExistenciaDoClienteFornecedorEDescricaoProblema(dadosSolicitacao);
        SolicitacaoGarantia solicitacaoGarantia = criaSolicitacaoGarantiaAPartirDeDtoEPersiste(dadosSolicitacao);
        criaEmailAPartirDeDtoESolicitacaoExistenteEPersiste(dadosSolicitacao, solicitacaoGarantia); 
    }

    /**
     * Valida se as entidades, como Fornecedor, Cliente e etc, com os IDs informados no DTO de fato existem nos repositórios. 
     * 
     * @param dadosSolicitacao
     */
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

    /**
     * 
     * Cria uma nova Solicitação de Garantia a partir dos dados recebidos do DTO.
     * 
     * @param dadosSolicitacao
     * @return <code>SolicitacaoGarantia</code>
     * @author breno.brito
     */
    private SolicitacaoGarantia criaSolicitacaoGarantiaAPartirDeDtoEPersiste(DadosParaNovaSolicitacaoDeGarantia dadosSolicitacao) {
        return solicitacaoGarantiaRepository.persist(
                new SolicitacaoGarantia(
                        dadosSolicitacao.getNumeroDeSerie(),                                                     /* Número de Série do Equipamento */
                        clienteRepository.getReferenceById(dadosSolicitacao.getClienteId()),                     /* Cliente */
                        fornecedorRepository.getReferenceById(dadosSolicitacao.getFornecedorId()),               /* Fornecedor */
                        descricaoProblemaRepository.getReferenceById(dadosSolicitacao.getDescricaoProblemaId()), /* Descrição do Problema */
                        Status.CRIADA));                                                                         /* Status da Solicitação */
    }

    private Email criaEmailAPartirDeDtoESolicitacaoExistenteEPersiste(DadosParaNovaSolicitacaoDeGarantia dadosSolicitacao, SolicitacaoGarantia solicitacaoGarantia) {
        return emailRepository.persist(new Email(
                                            solicitacaoGarantia.getCliente().getEmailsParaContato(), 
                                            solicitacaoGarantia.getFornecedor().getEmails(), 
                                            dadosSolicitacao.getAssunto(),
                                            dadosSolicitacao.getCorpoDoEmail(),
                                            dadosSolicitacao.getCopiaPara(), 
                                            dadosSolicitacao.getCopiaOculta(), 
                                            solicitacaoGarantia));
    }

}
