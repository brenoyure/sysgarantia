package br.albatross.sysgarantia.persistence.repositories.problemas;

import static br.albatross.sysgarantia.persistence.entities.problemas.DescricaoProblema_.problema;
import static org.hibernate.jpa.HibernateHints.HINT_CACHEABLE;

import java.util.List;

import br.albatross.sysgarantia.persistence.entities.problemas.DescricaoProblema;
import br.albatross.sysgarantia.persistence.entities.problemas.Problema_;
import br.albatross.sysgarantia.persistence.repositories.RepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.JoinType;

@ApplicationScoped
public class DescricaoProblemaRepositoryImpl extends RepositoryImpl<DescricaoProblema, Integer> implements DescricaoProblemaRepository {

    @Inject
	private EntityManager entityManager;

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
				       .setHint(HINT_CACHEABLE, true)
				       .getResultList();

	}

}
