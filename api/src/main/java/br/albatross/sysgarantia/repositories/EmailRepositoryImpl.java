package br.albatross.sysgarantia.repositories;

import java.util.Optional;

import br.albatross.sysgarantia.models.Email;
import br.albatross.sysgarantia.models.Email_;
import br.albatross.sysgarantia.models.SolicitacaoGarantia_;

import jakarta.enterprise.context.ApplicationScoped;

import jakarta.persistence.LockModeType;
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
        try {
            return Optional.of(this.getById(id));
        } catch (NoResultException e) { return Optional.empty(); }
    }

    @Override
    public Email getById(Long id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Email> query = cb.createQuery(Email.class);
        Root<Email> email = query.from(Email.class);

        email
            .fetch(Email_.anexos, JoinType.INNER);
        email
            .fetch(Email_.solicitacaoGarantia,   JoinType.INNER)
            .fetch(SolicitacaoGarantia_.cliente, JoinType.INNER);

        ParameterExpression<Long> paramId = cb.parameter(Long.class);
        query.where(cb.equal(email.get(Email_.id), paramId));

        return entityManager.createQuery(query)
                .setParameter(paramId, id)
                .setLockMode(LockModeType.PESSIMISTIC_READ)
                .getSingleResult();
    }
    
}
