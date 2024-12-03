package br.albatross.sysgarantia.repositories;

import java.util.List;

import br.albatross.sysgarantia.dto.emailtemplate.DadosDoEmailTemplateDto;
import br.albatross.sysgarantia.dto.emailtemplate.DadosParaAtualizacaoDeEmailTemplate;
import br.albatross.sysgarantia.dto.emailtemplate.EmailTemplateComboBox;
import br.albatross.sysgarantia.models.EmailTemplate;

public interface EmailTemplateRepository extends Repository<EmailTemplate, Integer> {

    boolean existsByDescricao(String descricao);
    boolean existsByDescricaoAndNotById(String descricao, Integer id);

    List<DadosDoEmailTemplateDto> findAllAsDtoOrderByAssunto();
    List<EmailTemplateComboBox> findAllAsEmailTemplateComboBoxOrderByDescricao();

    void update(DadosParaAtualizacaoDeEmailTemplate dadosAtualizados);

}
