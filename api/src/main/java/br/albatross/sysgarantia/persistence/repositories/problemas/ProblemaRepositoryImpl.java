package br.albatross.sysgarantia.persistence.repositories.problemas;

import br.albatross.sysgarantia.persistence.entities.problemas.Problema;
import br.albatross.sysgarantia.persistence.repositories.RepositoryImpl;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class ProblemaRepositoryImpl extends RepositoryImpl<Problema, Short> implements ProblemaRepository {

    public ProblemaRepositoryImpl() {
        super(Problema.class);
    }

}
