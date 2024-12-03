package br.albatross.sysgarantia.externos.otrs.repositories;

import java.util.Optional;

import br.albatross.sysgarantia.externos.InventarioRepository;
import io.quarkus.hibernate.orm.PersistenceUnit;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

/**
 * Contexto de persistência com o sistema de chamados OTRS/Znuny, 
 * ao acessar o CMDB e recuperar um equipamento através do seu identificador.
 * 
 * @author breno.brito
 */
@ApplicationScoped
public class OtrsZnunyCMDBInventarioRepository implements InventarioRepository {

    @Inject
    @PersistenceUnit("sistema-de-chamados")
    EntityManager entityManager;

    /**
     * Busca o número de série pelo BM do equipamento utilizando a NativeQuery JPA.
     * 
     * @param bm
     * @return optional contendo ou não o número de série.
     */
    public Optional<String> findSerialNumberByIdentifier(String bm) {

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
                                .setParameter(1, bm)
                                .setMaxResults(1)
                                .getSingleResult());

        } catch (NoResultException e) { return Optional.empty(); }

    }

}
