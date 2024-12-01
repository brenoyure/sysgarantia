package br.albatross.sysgarantia.domain.models.garantia.entidades.solicitacao;

import br.albatross.sysgarantia.domain.models.garantia.apis.chamado.DadosDoChamado;
import br.albatross.sysgarantia.domain.models.garantia.apis.cliente.DadosDoCliente;
import br.albatross.sysgarantia.domain.models.garantia.apis.fornecedores.DadosDoFornecedor;
import br.albatross.sysgarantia.domain.models.garantia.apis.problemas.DescricaoProblema;
import br.albatross.sysgarantia.domain.models.garantia.apis.solicitacao.SolicitacaoDeGarantia;
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
