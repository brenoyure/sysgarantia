package br.albatross.sysgarantia.externos.otrs;

import static br.albatross.sysgarantia.externos.otrs.entities.service.Service_.id;
import static br.albatross.sysgarantia.externos.otrs.entities.service.Service_.name;

import java.util.List;
import java.util.Optional;

import br.albatross.sysgarantia.externos.SistemaDeChamados;
import br.albatross.sysgarantia.externos.dto.Chamado;
import br.albatross.sysgarantia.externos.dto.Servico;
import br.albatross.sysgarantia.externos.otrs.entities.service.Service;

import io.quarkus.hibernate.orm.PersistenceUnit;

import jakarta.enterprise.context.ApplicationScoped;

import jakarta.inject.Inject;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

/**
 * Representa a integração, utilizando o JPA, com o Sistema de Chamados OTRS/Znuny
 * 
 * @author breno.brito
 */
@ApplicationScoped
public class OtrsZnunySistemaDeChamados implements SistemaDeChamados {

    @Inject
    @PersistenceUnit("sistema-de-chamados")
    EntityManager entityManager;

    private static final byte TRASH_QUEUE_ID = 3;
    private static final byte TICKET_STATE_AS_NEW  = 1;
    private static final byte TICKET_STATE_AS_OPEN = 2;

    @Override
    public List<Chamado> findByService(List<Integer> servicesIds) {

        @SuppressWarnings("unchecked")
        List<Chamado> chamados = 
                    (List<Chamado>) entityManager
                                                    .createNativeQuery(
"""
SELECT 
    t.id,
    t.tn,
    t.title,
    t.service_id,
    REVERSE(LEFT(REVERSE(s.name), (INSTR(REVERSE(s.name), '::') - 1))),
    t.customer_user_id
FROM
    ticket t
        INNER JOIN
    service s ON t.service_id = s.id
        INNER JOIN
    ticket_state ts ON t.ticket_state_id = ts.id

WHERE
        t.queue_id != ?1
    AND
        (ts.type_id = ?2 
        OR ts.type_id = ?3)
    AND 
        t.service_id IN (?4)

ORDER BY
        t.create_time DESC

""", Chamado.class)
                                                                    .setParameter(1, TRASH_QUEUE_ID)
                                                                    .setParameter(2, TICKET_STATE_AS_NEW)
                                                                    .setParameter(3, TICKET_STATE_AS_OPEN)
                                                                    .setParameter(4, servicesIds)
                                                                    .getResultList();

        return chamados;

    }

    /**
     * Busca o número de série pelo nome do Item de Configuração (ConfigItem), 
     * no CMDB do Otrs/Znuny.
     * 
     * @param configItemName
     * @return optional contendo ou não o número de série.
     */
    public Optional<String> findSerialNumberByIdentifier(String configItemName) {

        try {
            return Optional.of(
                    (String) entityManager
                                .createNativeQuery("""
SELECT 
    x.xml_content_value
FROM
    configitem_version civ
        INNER JOIN
    xml_storage x ON civ.id = x.xml_key
WHERE
            (x.xml_content_key = '[1]{''Version''}[1]{''NumeroDeSerie''}[1]{''Content''}'
            OR
            x.xml_content_key = '[1]{''Version''}[1]{''SerialNumber_Hardware''}[1]{''Content''}')

        AND

            NOT x.xml_type = 'ITSM::ConfigItem::Archiv::22'

        AND
            civ.name = ?1

                    """, String.class)
                                .setParameter(1, configItemName)
                                .setMaxResults(1)
                                .getSingleResult());

        } catch (NoResultException e) { return Optional.empty(); }

    }

    @Override
    public List<Servico> findAllTicketsServices() {
        CriteriaBuilder builder       =  entityManager.getCriteriaBuilder();
        CriteriaQuery<Servico> query  =  builder.createQuery(Servico.class);
        Root<Service> service         =  query.from(Service.class);

        query
            .select(
                builder.construct(Servico.class, 
                                        service.get(id),
                                        service.get(name)))
            .orderBy(
                builder.asc(service.get(name))
            );

        return entityManager.createQuery(query).getResultList();
    }
    
}
