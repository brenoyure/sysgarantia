package br.albatross.sysgarantia.repositories;

import br.albatross.sysgarantia.dto.problemas.DescricaoProblemaDto;
import br.albatross.sysgarantia.models.DescricaoProblema;

public interface DescricaoProblemaRepository extends Repository<DescricaoProblema, Integer> {

    boolean existsByDescricaoResumidaOrDescricaoDetalhada(String descricaoResumida, String descricaoDetalhada);

    boolean existsByDescricaoResumidaOrDescricaoDetalhadaAndNotById(String descricaoResumida, String descricaoDetalhada, Integer id);

    Iterable<DescricaoProblemaDto> findAllAsDtoOrderByCategoriaAsc();

}