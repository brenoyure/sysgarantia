package br.albatross.sysgarantia.dto.garantia;

import io.quarkus.runtime.annotations.RegisterForReflection;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import jakarta.ws.rs.FormParam;

/**
 * 
 * <p>Este DTO representa os dados vindo de um formulário do tipo <code>multipart/form-data</code>
 * para abertura de uma nova solicitação de garantia.</p>
 *
 * <p>OBS: O DTO foi anotado com <code>@io.quarkus.runtime.annotations.RegisterForReflection</code> para 
 * que funcione no caso de gerar imagem nativa do projeto, utilizando a <code>GraalVM</code> por exemplo.</p>
 * 
 * @author breno.brito
 */
@RegisterForReflection
public class DadosParaNovaSolicitacaoDeGarantia {

    @Positive(message = "Id do Cliente é obrigatório e deve ser um número inteiro positivo")
    @FormParam("cliente_id")
    private int clienteId;
    
    @Positive(message = "Id do Fornecedor é obrigatório e deve ser um número inteiro positivo")
    @FormParam("fornecedor_id")
    private int fornecedorId;
    
    @Positive(message = "Id da Descrição do Problema é obrigatório e deve ser um número inteiro positivo")
    @FormParam("descricao_problema_id")
    private int descricaoProblemaId;
    
    @Positive(message = "Id do Chamado não é obrigatório, mas deve ser um número inteiro positivo")
    @FormParam("chamado_id")
    private Integer chamadoId;
    
    @NotBlank(message = "Número de Série do Equipamento é Obrigatório")
    @FormParam("numero_de_serie")
    private String numeroDeSerie;

    @NotBlank(message = "Assunto é Obrigatório")
    @FormParam("assunto")
    private String assunto;

    @NotBlank(message = "Corpo do E-mail é Obrigatório")
    @FormParam("corpo_do_email")
    private String corpoDoEmail;

    @FormParam("copia_para")
    private String copiaPara;

    @FormParam("copia_oculta")
    private String copiaOculta;    

    public DadosParaNovaSolicitacaoDeGarantia() {

    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getFornecedorId() {
        return fornecedorId;
    }

    public void setFornecedorId(int fornecedorId) {
        this.fornecedorId = fornecedorId;
    }

    public int getDescricaoProblemaId() {
        return descricaoProblemaId;
    }

    public void setDescricaoProblemaId(int descricaoProblemaId) {
        this.descricaoProblemaId = descricaoProblemaId;
    }

    public Integer getChamadoId() {
        return chamadoId;
    }

    public void setChamadoId(Integer chamadoId) {
        this.chamadoId = chamadoId;
    }

    public String getNumeroDeSerie() {
        return numeroDeSerie;
    }

    public void setNumeroDeSerie(String numeroDeSerie) {
        this.numeroDeSerie = numeroDeSerie;
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

    public void setCopiaPara(String copiaPara) {
        this.copiaPara = copiaPara;
    }

    public String getCopiaPara() {
        return copiaPara;
    }

    public void setCopiaOculta(String copiaOculta) {
        this.copiaOculta = copiaOculta;
    }

    public String getCopiaOculta() {
        return copiaOculta;
    }    

}
