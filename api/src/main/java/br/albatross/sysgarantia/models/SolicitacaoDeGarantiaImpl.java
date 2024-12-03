package br.albatross.sysgarantia.models;

import br.albatross.sysgarantia.domain.models.garantia.apis.problemas.DescricaoProblema;
import br.albatross.sysgarantia.domain.models.garantia.apis.solicitacao.SolicitacaoDeGarantia;
import br.albatross.sysgarantia.dto.cliente.DadosDoCliente;
import br.albatross.sysgarantia.dto.fornecedor.DadosDoFornecedor;
import br.albatross.sysgarantia.models.garantia.DadosDoChamado;
import jakarta.enterprise.context.Dependent;
import lombok.Getter;
import lombok.Setter;

@Dependent
@Getter @Setter
public class SolicitacaoDeGarantiaImpl implements SolicitacaoDeGarantia {

    private static final long serialVersionUID = 1L;

    private String numeroDeSerie;

    private DescricaoProblema descricaoDoProblema;

    private DadosDoChamado chamado;

    private DadosDoFornecedor dadosDoFornecedor;

    private DadosDoCliente dadosDoCliente;

    public void setDadosDoFornecedor(DadosDoFornecedor dadosDoFornecedor) {

        this.dadosDoFornecedor = dadosDoFornecedor;

    }

}
