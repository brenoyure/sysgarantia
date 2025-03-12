package br.albatross.sysgarantia.repositories;

import java.util.Set;
import java.util.stream.Collectors;

import br.albatross.sysgarantia.models.Anexo;
import br.albatross.sysgarantia.models.Anexo_;
import br.albatross.sysgarantia.models.Email;
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

        query.where(cb.equal(anexo.get(Anexo_.id), idParam));

        return entityManager
                .createQuery(query)
                .setParameter(idParam, id)
                .setLockMode(LockModeType.PESSIMISTIC_READ)
                .getResultStream().collect(Collectors.toUnmodifiableSet());

    }

}
