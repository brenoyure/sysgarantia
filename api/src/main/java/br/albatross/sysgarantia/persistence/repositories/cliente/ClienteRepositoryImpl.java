package br.albatross.sysgarantia.persistence.repositories.cliente;

import br.albatross.sysgarantia.persistence.entities.cliente.Cliente;
import br.albatross.sysgarantia.persistence.repositories.RepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

@ApplicationScoped
public class ClienteRepositoryImpl extends RepositoryImpl<Cliente, Integer> implements ClienteRepository {

    @Inject
    private EntityManager entityManager;

    public ClienteRepositoryImpl() {
        super(Cliente.class);
    }

    @Override
    public boolean existsByNome(String nome) {
        try {

            return entityManager
                    .createQuery("SELECT EXISTS(SELECT c FROM Cliente c WHERE c.nome = ?1)", Boolean.class)
                    .setParameter(1, nome)
                    .getSingleResult();

        } catch (NoResultException e) { return false; }
    }

    @Override
    public boolean existsByDescricao(String descricao) {
        try {

            return entityManager
                    .createQuery("SELECT EXISTS(SELECT c FROM Cliente c WHERE c.descricao = ?1)", Boolean.class)
                    .setParameter(1, descricao)
                    .getSingleResult();

        } catch (NoResultException e) { return false; }
    }

    @Override
    public boolean existsByNomeAndNotById(String nome, int id) {
        try {

            return entityManager
                    .createQuery("SELECT EXISTS(SELECT c FROM Cliente c WHERE c.nome = ?1 AND c.id != ?2)", Boolean.class)
                    .setParameter(1, nome)
                    .setParameter(2, id)
                    .getSingleResult();

        } catch (NoResultException e) { return false; }
    }

    @Override
    public boolean existsByDescricaoAndNotById(String descricao, int id) {
        try {

            return entityManager
                    .createQuery("SELECT EXISTS(SELECT c FROM Cliente c WHERE c.descricao = ?1 AND c.id != ?2)", Boolean.class)
                    .setParameter(1, descricao)
                    .setParameter(2, id)
                    .getSingleResult();

        } catch (NoResultException e) { return false; }
    }

}
