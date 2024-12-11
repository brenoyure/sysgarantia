package br.albatross.sysgarantia.repositories;

import br.albatross.sysgarantia.models.SolicitacaoGarantia;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SolicitacaoGarantiaRepositoryImpl extends RepositoryImpl<SolicitacaoGarantia, Long> implements SolicitacaoGarantiaRepository {

    public SolicitacaoGarantiaRepositoryImpl() {
        super(SolicitacaoGarantia.class);
    }

}
