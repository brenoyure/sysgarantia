package br.albatross.sysgarantia.repositories;

import java.util.List;

import br.albatross.sysgarantia.dto.cliente.ClienteComboBox;
import br.albatross.sysgarantia.models.Cliente;
import io.quarkus.hibernate.orm.PersistenceUnit;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@ApplicationScoped
public class ClienteRepositoryImpl extends RepositoryImpl<Cliente, Integer> implements ClienteRepository {

    @Inject
    @PersistenceUnit("sysgarantia-pu")
    EntityManager entityManager;

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

    @Override
    public List<ClienteComboBox> findAllAsComboboxOrderByNome() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ClienteComboBox> query = criteriaBuilder.createQuery(ClienteComboBox.class);
        Root<Cliente> cliente = query.from(Cliente.class);
        
        query
            .select(criteriaBuilder
                    .construct(ClienteComboBox.class, 
                            cliente.get("id"), 
                            cliente.get("nome"), 
                            cliente.get("descricao")))
            .orderBy(criteriaBuilder.asc(cliente.get("nome")));

        return entityManager.createQuery(query).getResultList();
    }

}
