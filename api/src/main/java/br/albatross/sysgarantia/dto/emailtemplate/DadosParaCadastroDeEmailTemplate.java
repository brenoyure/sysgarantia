package br.albatross.sysgarantia.dto.emailtemplate;

import jakarta.validation.constraints.NotBlank;

public class DadosParaCadastroDeEmailTemplate {

    @NotBlank(message = "Descrição do Template Obrigatória")
    private String descricao;

    @NotBlank(message = "Assunto do Email Template Obrigatório")
    private String assunto;

    @NotBlank(message = "Corpo do Email Template Obrigatório")
    private String corpoDoEmail;

    public DadosParaCadastroDeEmailTemplate() {

    }

    public DadosParaCadastroDeEmailTemplate(String descricao, String assunto, String corpoDoEmail) {
        this.descricao = descricao;
        this.assunto = assunto;
        this.corpoDoEmail = corpoDoEmail;
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

}
