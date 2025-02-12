package br.albatross.sysgarantia.repositories;

import java.util.List;

import static br.albatross.sysgarantia.models.DescricaoProblema_.descricaoResumida;
import static br.albatross.sysgarantia.models.DescricaoProblema_.id;
import static br.albatross.sysgarantia.models.DescricaoProblema_.problema;
import static br.albatross.sysgarantia.models.Problema_.tipo;

import br.albatross.sysgarantia.dto.problemas.DescricaoProblemaDto;
import br.albatross.sysgarantia.models.DescricaoProblema;
import br.albatross.sysgarantia.models.Problema_;

import jakarta.enterprise.context.ApplicationScoped;

import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.JoinType;

@ApplicationScoped
public class DescricaoProblemaRepositoryImpl extends RepositoryImpl<DescricaoProblema, Integer> implements DescricaoProblemaRepository {

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

    @Override
    public boolean existsByDescricaoResumidaOrDescricaoDetalhada(String descricaoResumida, String descricaoDetalhada) {
        try {
            return entityManager
                    .createQuery("SELECT EXISTS(SELECT dp FROM DescricaoProblema dp WHERE dp.descricaoResumida = ?1 OR dp.descricaoDetalhada = ?2)", Boolean.class)
                    .setParameter(1, descricaoResumida)
                    .setParameter(2, descricaoDetalhada)
                    .getSingleResult();
        } catch (NoResultException e) { return false; }
    }

    @Override
    public boolean existsByDescricaoResumidaOrDescricaoDetalhadaAndNotById(String descricaoResumida, String descricaoDetalhada, Integer id) {
        try {
            return entityManager
                    .createQuery("SELECT EXISTS(SELECT dp FROM DescricaoProblema dp WHERE (dp.descricaoResumida = ?1 OR dp.descricaoDetalhada = ?2) AND dp.id != ?3)", Boolean.class)
                    .setParameter(1, descricaoResumida)
                    .setParameter(2, descricaoDetalhada)
                    .setParameter(3, id)
                    .getSingleResult();
        } catch (NoResultException e) { return false; }

    }

    @Override
    public Iterable<DescricaoProblemaDto> findAllAsDtoOrderByCategoriaAsc() {
        var cb                =  entityManager.getCriteriaBuilder();
        var cq                =  cb.createQuery(DescricaoProblemaDto.class);
        var descricaoProblema =  cq.from(DescricaoProblema.class); 

        cq.select(cb.construct(DescricaoProblemaDto.class, 
                descricaoProblema.get(id),
                descricaoProblema.get(descricaoResumida),
                descricaoProblema.get(problema).get(Problema_.id),
                descricaoProblema.get(problema).get(tipo)));

        descricaoProblema.join(problema, JoinType.INNER);

        cq.orderBy(cb.asc(descricaoProblema.get(problema).get(tipo)));

        return entityManager
                       .createQuery(cq)
                       .getResultList();
    }

}
