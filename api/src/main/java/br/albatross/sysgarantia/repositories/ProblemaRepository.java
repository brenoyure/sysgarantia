package br.albatross.sysgarantia.repositories;

import br.albatross.sysgarantia.models.Problema;

public interface ProblemaRepository extends Repository<Problema, Short> {

    boolean existsByTipo(String tipo);

    boolean existsByTipoAndNotById(String tipo, Short id);

    boolean hasDescricaoProblemas(Short id);

}