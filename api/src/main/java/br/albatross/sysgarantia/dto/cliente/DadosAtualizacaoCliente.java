package br.albatross.sysgarantia.dto.cliente;

import jakarta.validation.constraints.Positive;

public class DadosAtualizacaoCliente extends DadosParaCadastroDeCliente {

    @Positive
    private int id;

    public DadosAtualizacaoCliente() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
