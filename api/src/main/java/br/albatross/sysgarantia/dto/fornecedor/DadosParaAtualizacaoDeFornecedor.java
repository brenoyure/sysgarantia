package br.albatross.sysgarantia.dto.fornecedor;

import jakarta.validation.constraints.Positive;

public class DadosParaAtualizacaoDeFornecedor extends DadosParaCadastroDeNovoFornecedor {

    @Positive
    private int id;

    public DadosParaAtualizacaoDeFornecedor() {

    }

    public DadosParaAtualizacaoDeFornecedor(DadosDoFornecedor fornecedor) {
        this.id = fornecedor.getId();
        this.setNome(fornecedor.getNome());
        this.setEmails(fornecedor.getEmails());
        this.setIdsDosServicosDoFornecedorNoSistemaDeChamados(
                fornecedor.getIdsDosServicosDoFornecedorNoSistemaDeChamados());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
