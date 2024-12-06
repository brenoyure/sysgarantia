package br.albatross.sysgarantia.models;

import br.albatross.sysgarantia.domain.models.garantia.apis.problemas.DescricaoProblema;
import br.albatross.sysgarantia.domain.models.garantia.apis.solicitacao.SolicitacaoDeGarantia;
import br.albatross.sysgarantia.dto.cliente.DadosDoCliente;
import br.albatross.sysgarantia.dto.fornecedor.DadosDoFornecedor;
import br.albatross.sysgarantia.externos.dto.Chamado;

public class SolicitacaoDeGarantiaImpl implements SolicitacaoDeGarantia {

    private static final long serialVersionUID = 1L;

    private String numeroDeSerie;

    private DescricaoProblema descricaoDoProblema;

    private Chamado chamado;

    private DadosDoFornecedor dadosDoFornecedor;

    private DadosDoCliente dadosDoCliente;

    public SolicitacaoDeGarantiaImpl() {

    }

    public SolicitacaoDeGarantiaImpl(String numeroDeSerie, DescricaoProblema descricaoDoProblema, Chamado chamado,
            DadosDoFornecedor dadosDoFornecedor, DadosDoCliente dadosDoCliente) {
        this.numeroDeSerie = numeroDeSerie;
        this.descricaoDoProblema = descricaoDoProblema;
        this.chamado = chamado;
        this.dadosDoFornecedor = dadosDoFornecedor;
        this.dadosDoCliente = dadosDoCliente;
    }

    public String getNumeroDeSerie() {
        return numeroDeSerie;
    }

    public void setNumeroDeSerie(String numeroDeSerie) {
        this.numeroDeSerie = numeroDeSerie;
    }

    public DescricaoProblema getDescricaoDoProblema() {
        return descricaoDoProblema;
    }

    public void setDescricaoDoProblema(DescricaoProblema descricaoDoProblema) {
        this.descricaoDoProblema = descricaoDoProblema;
    }

    public Chamado getChamado() {
        return chamado;
    }

    public void setChamado(Chamado chamado) {
        this.chamado = chamado;
    }

    public DadosDoFornecedor getDadosDoFornecedor() {
        return dadosDoFornecedor;
    }

    public void setDadosDoFornecedor(DadosDoFornecedor dadosDoFornecedor) {
        this.dadosDoFornecedor = dadosDoFornecedor;
    }

    public DadosDoCliente getDadosDoCliente() {
        return dadosDoCliente;
    }

    public void setDadosDoCliente(DadosDoCliente dadosDoCliente) {
        this.dadosDoCliente = dadosDoCliente;
    }

}
