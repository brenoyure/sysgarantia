package br.albatross.sysgarantia.repositories;

import java.util.List;

import br.albatross.sysgarantia.dto.emailtemplate.EmailTemplateDto;
import br.albatross.sysgarantia.models.EmailTemplate;

public interface EmailTemplateRepository extends Repository<EmailTemplate, Integer> {

    boolean existsByDescricao(String descricao);
    boolean existsByDescricaoAndNotById(String descricao, Integer id);

    List<EmailTemplateDto> findAllAsDtoOrderByAssunto();

}
