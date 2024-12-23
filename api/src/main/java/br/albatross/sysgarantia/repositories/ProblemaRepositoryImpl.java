package br.albatross.sysgarantia.repositories;

import br.albatross.sysgarantia.models.Problema;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;

@ApplicationScoped
public class ProblemaRepositoryImpl extends RepositoryImpl<Problema, Short> implements ProblemaRepository {

    public ProblemaRepositoryImpl() {
        super(Problema.class);
    }

    @Override
    public boolean existsByTipo(String tipo) {
        try {
            return entityManager
                .createQuery("SELECT EXISTS (SELECT p FROM Problema p WHERE p.tipo = ?1)", Boolean.class)
                .setParameter(1, tipo)
                .getSingleResult();
        } catch(NoResultException e) { return false; }
    }

    @Override
    public boolean existsByTipoAndNotById(String tipo, Short id) {
        try {
            return entityManager
                .createQuery("SELECT EXISTS (SELECT p FROM Problema p WHERE p.tipo = ?1 AND p.id != ?2)", Boolean.class)
                .setParameter(1, tipo)
                .setParameter(2, id)
                .getSingleResult();
        } catch(NoResultException e) { return false; }
    }

    @Override
    public boolean hasDescricaoProblemas(Short id) {
        try {
            return entityManager
                .createQuery("SELECT EXISTS (SELECT dp FROM DescricaoProblema dp WHERE dp.problema.id = ?1)", Boolean.class)
                .setParameter(1, id)
                .getSingleResult();
        } catch(NoResultException e) { return false; }
    }

}
