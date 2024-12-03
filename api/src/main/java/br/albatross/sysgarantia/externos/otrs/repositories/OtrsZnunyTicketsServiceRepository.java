package br.albatross.sysgarantia.externos.otrs.repositories;

import static br.albatross.sysgarantia.externos.otrs.entities.service.Service_.id;
import static br.albatross.sysgarantia.externos.otrs.entities.service.Service_.name;

import java.util.List;

import br.albatross.sysgarantia.externos.ServicosDosChamadosRepository;
import br.albatross.sysgarantia.externos.otrs.entities.service.Service;
import br.albatross.sysgarantia.models.garantia.DadosDoServico;

import io.quarkus.hibernate.orm.PersistenceUnit;

import jakarta.enterprise.context.ApplicationScoped;

import jakarta.inject.Inject;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

/**
 * Representa o contexto de persistência com a entidade Service(Serviço do Ticket) do Sistema de Chamados OTRS/Znuny.
 */
@ApplicationScoped
public class OtrsZnunyTicketsServiceRepository implements ServicosDosChamadosRepository {

    @Inject
    @PersistenceUnit("sistema-de-chamados")
    EntityManager entityManager;

    @Override
    public List<DadosDoServico> findAll() {

        CriteriaBuilder builder              =  entityManager.getCriteriaBuilder();
        CriteriaQuery<DadosDoServico> query  =  builder.createQuery(DadosDoServico.class);
        Root<Service> service                =  query.from(Service.class);

        query
            .select(
                builder.construct(DadosDoServico.class, 
                                                      service.get(id),
                                                      service.get(name)))
            .orderBy(
                builder.asc(service.get(name))
            );

        return entityManager.createQuery(query).getResultList();

    }

}