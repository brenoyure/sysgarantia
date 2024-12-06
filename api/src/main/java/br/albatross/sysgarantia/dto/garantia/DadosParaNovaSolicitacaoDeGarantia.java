package br.albatross.sysgarantia.dto.garantia;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class DadosParaNovaSolicitacaoDeGarantia {

    @Positive
    @JsonbProperty("cliente_id")
    private int clienteId;

    @Positive
    @JsonbProperty("fornecedor_id")
    private int fornecedorId;

    @Positive
    @JsonbProperty("descricao_problema_id")
    private int descricaoProblemaId;

    @Positive
    @JsonbProperty("chamado_id")
    private int chamadoId;

    @NotBlank
    @JsonbProperty("numero_de_serie")
    private String numeroDeSerie;

    public DadosParaNovaSolicitacaoDeGarantia() {

    }

    public DadosParaNovaSolicitacaoDeGarantia(int clienteId, int fornecedorId, int descricaoProblemaId, int chamadoId, String numeroDeSerie) {
        this.clienteId = clienteId;
        this.fornecedorId = fornecedorId;
        this.descricaoProblemaId = descricaoProblemaId;
        this.chamadoId = chamadoId;
        this.numeroDeSerie = numeroDeSerie;
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

    public int getChamadoId() {
        return chamadoId;
    }

    public void setChamadoId(int chamadoId) {
        this.chamadoId = chamadoId;
    }

    public String getNumeroDeSerie() {
        return numeroDeSerie;
    }

    public void setNumeroDeSerie(String numeroDeSerie) {
        this.numeroDeSerie = numeroDeSerie;
    }

}
