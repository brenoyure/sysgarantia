package br.albatross.sysgarantia.persistence.repositories.fornecedor;

import java.util.List;
import java.util.Optional;

import org.hibernate.jpa.AvailableHints;

import br.albatross.sysgarantia.domain.models.fornecedor.FornecedorComboBox;
import br.albatross.sysgarantia.persistence.entities.fornecedor.Fornecedor;
import br.albatross.sysgarantia.persistence.repositories.RepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

@ApplicationScoped
public class FornecedoresRepositoryImpl extends RepositoryImpl<Fornecedor, Integer> implements FornecedorRepository {

    @Inject
    private EntityManager entityManager;

    public FornecedoresRepositoryImpl() {
        super(Fornecedor.class);
    }

    @Override
    public Optional<Fornecedor> findById(Integer id) {

        try {

            return Optional.of(entityManager
                                    .createQuery("SELECT f FROM Fornecedor f JOIN FETCH f.idsDosServicosDoFornecedorNoSistemaDeChamados WHERE id = ?1", Fornecedor.class)
                                    .setParameter(1, id)
                                    .getSingleResult());

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
    public List<Fornecedor> findAll() {
        return entityManager
                .createQuery("SELECT f FROM Fornecedor f JOIN FETCH f.idsDosServicosDoFornecedorNoSistemaDeChamados ORDER BY f.nome", Fornecedor.class)
                .setHint(AvailableHints.HINT_CACHEABLE, true)
                .getResultList();
    }

    @Override
    public List<Integer> findIdsDosServicosDoFornecedorNoSistemaDeChamadosById(int id) {

        return entityManager
                .createQuery("SELECT s.idsDosServicosDoFornecedorNoSistemaDeChamados FROM Fornecedor s WHERE id = ?1", Integer.class)
                .setParameter(1, id)
                .getResultList();

    }

    @Override
    public List<FornecedorComboBox> findAllAsFornecedorComboBoxOrderByNome() {

        return entityManager
                .createQuery("SELECT new br.albatross.sysgarantia.domain.models.fornecedor.FornecedorComboBox(f.id, f.nome) FROM Fornecedor f ORDER BY f.nome", FornecedorComboBox.class)
                .getResultList();

    }

}
