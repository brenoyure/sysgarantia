package br.albatross.sysgarantia.services.problemas;

import br.albatross.sysgarantia.dto.problemas.AtualizacaoDescricaoProblemaDto;
import br.albatross.sysgarantia.dto.problemas.NovaDescricaoProblemaDto;
import br.albatross.sysgarantia.models.DescricaoProblema;
import br.albatross.sysgarantia.repositories.DescricaoProblemaRepository;
import br.albatross.sysgarantia.repositories.ProblemaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

@ApplicationScoped
public class DescricaoProblemaService {

    @Inject
    ProblemaRepository problemaRepository;    

    @Inject
    DescricaoProblemaRepository descricaoProblemaRepository;

    @Transactional
    public DescricaoProblema cadastrar(@Valid NovaDescricaoProblemaDto novaDescricao) {
        if (descricaoProblemaRepository
                .existsByDescricaoResumidaOrDescricaoDetalhada(novaDescricao.descricaoResumida(), 
                                                               novaDescricao.descricaoDetalhada())) {
            throw new ValidationException("Já existe um Texto Pronto cadastrado com a Descrição Resumida(ou a Detalhada) Informada");
        }

        return descricaoProblemaRepository
                .persist(new DescricaoProblema(novaDescricao.descricaoResumida(), 
                                               novaDescricao.descricaoDetalhada(), 
                                               problemaRepository.getReferenceById(novaDescricao.problemaId())));
    }

    @Transactional
    public void atualizar(@Valid AtualizacaoDescricaoProblemaDto dadosAtualizacao) {
        if (descricaoProblemaRepository
                .existsByDescricaoResumidaOrDescricaoDetalhadaAndNotById(dadosAtualizacao.descricaoResumida(), 
                                                                         dadosAtualizacao.descricaoDetalhada(), 
                                                                         dadosAtualizacao.id())) {
            throw new ValidationException("Já existe um Texto Pronto cadastrado com a Descrição Resumida(ou a Detalhada) Informada");
        }

        descricaoProblemaRepository
            .merge(new DescricaoProblema(dadosAtualizacao.id(),
                                         dadosAtualizacao.descricaoResumida(), 
                                         dadosAtualizacao.descricaoDetalhada(), 
                                         problemaRepository.getReferenceById(dadosAtualizacao.problemaId())));

    }

}
