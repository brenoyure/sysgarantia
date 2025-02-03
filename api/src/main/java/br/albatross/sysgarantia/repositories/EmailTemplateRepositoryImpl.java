package br.albatross.sysgarantia.repositories;

import static br.albatross.sysgarantia.models.EmailTemplate_.assunto;
import static br.albatross.sysgarantia.models.EmailTemplate_.descricao;
import static br.albatross.sysgarantia.models.EmailTemplate_.id;

import java.util.List;

import br.albatross.sysgarantia.dto.emailtemplate.DadosDoEmailTemplateDto;
import br.albatross.sysgarantia.dto.emailtemplate.EmailTemplateComboBox;
import br.albatross.sysgarantia.models.EmailTemplate;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CompoundSelection;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Root;

@ApplicationScoped
public class EmailTemplateRepositoryImpl extends RepositoryImpl<EmailTemplate, Integer>
        implements EmailTemplateRepository {

    public EmailTemplateRepositoryImpl() {
        super(EmailTemplate.class);
    }

    @Override
    public List<DadosDoEmailTemplateDto> findAllAsDtoOrderByAssunto() {

        CriteriaBuilder cb = 
                entityManager.getCriteriaBuilder();
        CriteriaQuery<DadosDoEmailTemplateDto> query = 
                cb.createQuery(DadosDoEmailTemplateDto.class);
        Root<EmailTemplate> emailTemplate = 
                query.from(EmailTemplate.class);

        CompoundSelection<DadosDoEmailTemplateDto> emailTemplateSelect = 
                cb.construct(
                        DadosDoEmailTemplateDto.class, 
                            emailTemplate.get(id),
                            emailTemplate.get(descricao),
                            emailTemplate.get(assunto));
        Order assuntoAsc = 
                cb.asc(emailTemplate.get(descricao));

        query
            .select(emailTemplateSelect)
            .orderBy(assuntoAsc);        

        return entityManager.createQuery(query).getResultList();

    }
    
    @Override
    public boolean existsByDescricao(String descricao) {
        try {

            return entityManager
                    .createQuery("SELECT EXISTS ( SELECT e FROM EmailTemplate e WHERE e.descricao = ?1 )", Boolean.class)
                    .setParameter(1, descricao)
                    .getSingleResult();

        } catch (NoResultException e) { return false; }
    }

    @Override
    public boolean existsByDescricaoAndNotById(String descricao, Integer id) {
        try {

            return entityManager
                    .createQuery("SELECT EXISTS ( SELECT e FROM EmailTemplate e WHERE e.descricao = ?1 AND e.id != ?2 )", Boolean.class)
                    .setParameter(1, descricao)
                    .setParameter(2, id)
                    .getSingleResult();

        } catch (NoResultException e) { return false; }

    }

    @Override
    public List<EmailTemplateComboBox> findAllAsEmailTemplateComboBoxOrderByDescricao() {
        CriteriaBuilder cb = 
                entityManager.getCriteriaBuilder();
        CriteriaQuery<EmailTemplateComboBox> query = 
                cb.createQuery(EmailTemplateComboBox.class);
        Root<EmailTemplate> emailTemplate = 
                query.from(EmailTemplate.class);

        CompoundSelection<EmailTemplateComboBox> emailTemplateSelect = 
                cb.construct(
                        EmailTemplateComboBox.class, 
                            emailTemplate.get(id),
                            emailTemplate.get(descricao));
        Order assuntoAsc = 
                cb.asc(emailTemplate.get(descricao));

        query
            .select(emailTemplateSelect)
            .orderBy(assuntoAsc);        

        return entityManager.createQuery(query).getResultList();

    }

}
