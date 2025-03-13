package br.albatross.sysgarantia.repositories;

import static br.albatross.sysgarantia.models.Anexo_.email;

import java.util.Set;
import java.util.stream.Collectors;

import br.albatross.sysgarantia.models.Anexo;
import br.albatross.sysgarantia.models.Email;
import br.albatross.sysgarantia.models.Email_;

import jakarta.enterprise.context.ApplicationScoped;

import jakarta.persistence.LockModeType;
import jakarta.persistence.Parameter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@ApplicationScoped
public class EmailRepositoryImpl extends RepositoryImpl<Email, Long> implements EmailRepository {

    public EmailRepositoryImpl() {
        super(Email.class);
    }

    @Override
    public Set<Anexo> findAnexosById(long id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Anexo> query = cb.createQuery(Anexo.class);
        Root<Anexo> anexo = query.from(Anexo.class);

        Parameter<Long> idParam = cb.parameter(Long.class);

        query.where(cb.equal(anexo.get(email).get(Email_.id), idParam));

        return entityManager
                .createQuery(query)
                .setParameter(idParam, id)
                .setLockMode(LockModeType.PESSIMISTIC_READ)
                .getResultStream().collect(Collectors.toUnmodifiableSet());

    }

}
