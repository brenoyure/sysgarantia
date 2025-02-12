package br.albatross.sysgarantia.dto.emailtemplate;

/**
 * 
 * DTO útil com os dados básicos de um email template, para exibição em uma
 * combobox ou table, por exemplo
 * 
 * @author breno.brito
 */
public record EmailTemplateDto(int id, String descricao, String assunto) {

}
