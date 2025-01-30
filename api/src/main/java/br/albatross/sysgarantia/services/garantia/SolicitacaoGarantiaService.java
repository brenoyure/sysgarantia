package br.albatross.sysgarantia.services.garantia;

import br.albatross.sysgarantia.dto.garantia.DadosParaNovaSolicitacaoDeGarantia;
import br.albatross.sysgarantia.messaging.MessagesProducerService;
import br.albatross.sysgarantia.models.Email;
import br.albatross.sysgarantia.models.SolicitacaoGarantia;
import br.albatross.sysgarantia.models.SolicitacaoGarantia.Status;
import br.albatross.sysgarantia.repositories.ClienteRepository;
import br.albatross.sysgarantia.repositories.DescricaoProblemaRepository;
import br.albatross.sysgarantia.repositories.FornecedorRepository;
import br.albatross.sysgarantia.repositories.SolicitacaoGarantiaRepository;

import jakarta.enterprise.context.ApplicationScoped;

import jakarta.inject.Inject;

import jakarta.transaction.Transactional;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

/**
 *
 * @author breno.brito
 */
@ApplicationScoped
public class SolicitacaoGarantiaService {

    @Inject
    MessagesProducerService messagingService;

    @Inject
    EmailGarantiaService emailGarantiaService;

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

        Email emailDeGarantia = emailGarantiaService.criarNovoEmailDeGarantia(dadosSolicitacao, solicitacaoGarantia);

        messagingService.send(emailDeGarantia);

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
                        dadosSolicitacao.getNumeroDeSerie(),                                                           /* Número de Série do Equipamento */
                        clienteRepository.findById(dadosSolicitacao.getClienteId()).orElseThrow(),                     /* Cliente */
                        fornecedorRepository.findById(dadosSolicitacao.getFornecedorId()).orElseThrow(),               /* Fornecedor */
                        descricaoProblemaRepository.findById(dadosSolicitacao.getDescricaoProblemaId()).orElseThrow(), /* Descrição do Problema */
                        Status.CRIADA));                                                                               /* Status da Solicitação */
    }

}
