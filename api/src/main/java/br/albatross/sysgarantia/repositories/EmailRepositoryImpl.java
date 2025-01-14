package br.albatross.sysgarantia.repositories;

import java.util.Optional;

import br.albatross.sysgarantia.models.Email;

import jakarta.enterprise.context.ApplicationScoped;

import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Root;

@ApplicationScoped
public class EmailRepositoryImpl extends RepositoryImpl<Email, Long> implements EmailRepository {

    public EmailRepositoryImpl() {
        super(Email.class);
    }

    @Override
    public Optional<Email> findById(Long id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Email> query = cb.createQuery(Email.class);
        Root<Email> email = query.from(Email.class);

        email
            .fetch("anexos", JoinType.INNER);
        email
            .fetch("solicitacaoGarantia", JoinType.INNER)
            .fetch("cliente", JoinType.INNER);

        ParameterExpression<Long> paramId = cb.parameter(Long.class);
        query.where(cb.equal(email.get("id"), paramId));
        try {
            return Optional.of(entityManager.createQuery(query)
                                            .setParameter(paramId, id)
                                            .getSingleResult());
        } catch (NoResultException e) { return Optional.empty(); }
    }
    
}
