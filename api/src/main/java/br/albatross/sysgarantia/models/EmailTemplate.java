package br.albatross.sysgarantia.models;

import java.util.Objects;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "email_template")
public class EmailTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "descricao", unique = true, nullable = false)
    private String descricao;

    @Column(name = "assunto", unique = false, nullable = false)
    private String assunto;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "corpo_do_email", unique = false, nullable = false)
    private String corpoDoEmail;

    public EmailTemplate() {

    }

    public EmailTemplate(String descricao, String assunto, String corpoDoEmail) {
        this.descricao = descricao;
        this.assunto = assunto;
        this.corpoDoEmail = corpoDoEmail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getCorpoDoEmail() {
        return corpoDoEmail;
    }

    public void setCorpoDoEmail(String corpoDoEmail) {
        this.corpoDoEmail = corpoDoEmail;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EmailTemplate other = (EmailTemplate) obj;
        return Objects.equals(id, other.id);
    }

}
