package br.albatross.sysgarantia.persistence.repositories.emailtemplate;

import java.util.List;

import br.albatross.sysgarantia.domain.models.emailtemplate.DadosDoEmailTemplateDto;
import br.albatross.sysgarantia.domain.models.emailtemplate.DadosParaAtualizacaoDeEmailTemplate;
import br.albatross.sysgarantia.domain.models.emailtemplate.EmailTemplateComboBox;
import br.albatross.sysgarantia.persistence.entities.emailtemplate.EmailTemplate;
import br.albatross.sysgarantia.persistence.repositories.Repository;

public interface EmailTemplateRepository extends Repository<EmailTemplate, Integer> {

    boolean existsByDescricao(String descricao);
    boolean existsByDescricaoAndNotById(String descricao, Integer id);

    List<DadosDoEmailTemplateDto> findAllAsDtoOrderByAssunto();
    List<EmailTemplateComboBox> findAllAsEmailTemplateComboBoxOrderByDescricao();

    void update(DadosParaAtualizacaoDeEmailTemplate dadosAtualizados);

}
