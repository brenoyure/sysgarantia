package br.albatross.sysgarantia.repositories;

import static br.albatross.sysgarantia.models.DescricaoProblema_.problema;

import java.util.List;

import br.albatross.sysgarantia.models.DescricaoProblema;
import br.albatross.sysgarantia.models.Problema_;
import io.quarkus.hibernate.orm.PersistenceUnit;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.JoinType;

@ApplicationScoped
public class DescricaoProblemaRepositoryImpl extends RepositoryImpl<DescricaoProblema, Integer> implements DescricaoProblemaRepository {

    @Inject
    @PersistenceUnit("sysgarantia-pu")
    EntityManager entityManager;

    public DescricaoProblemaRepositoryImpl() {
        super(DescricaoProblema.class);
    }    

	@Override
    public List<DescricaoProblema> findAll() {
		var cb                =  entityManager.getCriteriaBuilder();
		var cq                =  cb.createQuery(DescricaoProblema.class);
		var descricaoProblema =  cq.from(DescricaoProblema.class); 

		descricaoProblema
		                 .fetch(problema, JoinType.INNER);

		cq
		  .orderBy(cb.asc(descricaoProblema.get(problema).get(Problema_.id)));

		return entityManager
				       .createQuery(cq)
				       .getResultList();

	}

}
