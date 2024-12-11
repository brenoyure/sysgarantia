package br.albatross.sysgarantia.repositories;

import static br.albatross.sysgarantia.models.Fornecedor_.idsDosServicosDoFornecedorNoSistemaDeChamados;

import java.util.List;
import java.util.Optional;

import br.albatross.sysgarantia.dto.fornecedor.FornecedorComboBox;
import br.albatross.sysgarantia.models.Fornecedor;
import br.albatross.sysgarantia.models.Fornecedor_;
import io.quarkus.hibernate.orm.PersistenceUnit;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Root;

@ApplicationScoped
public class FornecedoresRepositoryImpl extends RepositoryImpl<Fornecedor, Integer> implements FornecedorRepository {

    @Inject
    @PersistenceUnit("sysgarantia-pu")
    EntityManager entityManager;

    public FornecedoresRepositoryImpl() {
        super(Fornecedor.class);
    }

    @Override
    public List<Fornecedor> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Fornecedor> query = criteriaBuilder.createQuery(Fornecedor.class);
        Root<Fornecedor> fornecedor = query.from(Fornecedor.class);

        fornecedor.fetch(idsDosServicosDoFornecedorNoSistemaDeChamados, JoinType.INNER);

        query.orderBy(criteriaBuilder.asc(fornecedor.get(Fornecedor_.nome)));

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Optional<Fornecedor> findById(Integer id) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Fornecedor> query = criteriaBuilder.createQuery(Fornecedor.class);
            Root<Fornecedor> fornecedor = query.from(Fornecedor.class);

            fornecedor.fetch(idsDosServicosDoFornecedorNoSistemaDeChamados, JoinType.INNER);

            ParameterExpression<Integer> parameterId = criteriaBuilder.parameter(Integer.class);

            query.where(criteriaBuilder.equal(fornecedor.get(Fornecedor_.id), parameterId));

            return Optional.of(entityManager.createQuery(query).setParameter(parameterId, id).getSingleResult());

        } catch (NoResultException e) { return Optional.empty(); }        

    }

    @Override
    public boolean existsByNome(String nome) {
        try {

            return entityManager
                    .createQuery("SELECT EXISTS(SELECT f FROM Fornecedor f WHERE f.nome = ?1)", Boolean.class)
                    .setParameter(1, nome)
                    .getSingleResult();

        } catch (NoResultException e) { return false; }

    }

    @Override
    public boolean existsByNomeAndNotById(String nome, int id) {
        try {

            return entityManager
                    .createQuery("SELECT EXISTS(SELECT f FROM Fornecedor f WHERE f.nome = ?1 AND f.id != ?2)", Boolean.class)
                    .setParameter(1, nome)
                    .setParameter(2, id)
                    .getSingleResult();

        } catch (NoResultException e) { return false; }

    }

    @Override
    public List<Integer> findIdsDosServicosDoFornecedorNoSistemaDeChamadosById(int id) {

        return entityManager
                .createQuery("SELECT s.idsDosServicosDoFornecedorNoSistemaDeChamados FROM Fornecedor s WHERE id = ?1", Integer.class)
                .setParameter(1, id)
                .getResultList();

    }

//    @Override
//    public List<FornecedorComboBox> findAllOrderByNomeAsCombobox() {
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<FornecedorComboBox> query = criteriaBuilder.createQuery(FornecedorComboBox.class);
//        Root<Fornecedor> fornecedor = query.from(Fornecedor.class);
//
//        query
//            .select(criteriaBuilder.construct(FornecedorComboBox.class, 
//                    fornecedor.get(Fornecedor_.id), 
//                    fornecedor.get(Fornecedor_.nome),
//                    fornecedor.get(Fornecedor_.emails)))
//            .orderBy(criteriaBuilder.asc(fornecedor.get(Fornecedor_.nome)));
//
//        return entityManager.createQuery(query).getResultList();
//
//    }

}
