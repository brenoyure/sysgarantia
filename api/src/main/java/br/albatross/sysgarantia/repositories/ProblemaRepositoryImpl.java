package br.albatross.sysgarantia.repositories;

import br.albatross.sysgarantia.models.Problema;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProblemaRepositoryImpl extends RepositoryImpl<Problema, Short> implements ProblemaRepository {

    public ProblemaRepositoryImpl() {
        super(Problema.class);
    }

}
