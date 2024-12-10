package br.albatross.sysgarantia.dto.garantia;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import jakarta.ws.rs.FormParam;

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

}
