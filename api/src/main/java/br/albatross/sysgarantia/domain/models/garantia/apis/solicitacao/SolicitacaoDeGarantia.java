package br.albatross.sysgarantia.domain.models.garantia.apis.solicitacao;

import java.io.Serializable;

import br.albatross.sysgarantia.domain.models.garantia.apis.problemas.DescricaoProblema;
import br.albatross.sysgarantia.dto.cliente.DadosDoCliente;
import br.albatross.sysgarantia.dto.fornecedor.DadosDoFornecedor;
import br.albatross.sysgarantia.externos.dto.Chamado;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Representa uma Solicitação de Garantia que pode ser aberta para algum fornecedor. 
 */
public interface SolicitacaoDeGarantia extends Serializable {

	/**
	 * 
	 * @return O Nº de Série do Equipamento (ex. Computador, Monitor...) (<strong>Não</strong> podendo ficar em branco).
	 */
	@NotBlank
	String getNumeroDeSerie();

	/**
	 * 
	 * @return A Descrição do Problema (<strong>Não</strong> podendo ficar em branco).
	 */
	@NotNull
	DescricaoProblema getDescricaoDoProblema();

	/**
	 * 
	 * @return Os Dados do Chamado a qual esta solicitação pertence (<strong>Não</strong> podendo ficar nulo).
	 */
	@NotNull
	Chamado getChamado();

//	/**
//	 * 
//	 * @return O E-mail que será enviado para o fabricante/fornecedor do equipamento (<strong>Não</strong> podendo ficar nulo).
//	 */
//	@NotNull
//	Email getEmailDeGarantia();

	@NotNull
	DadosDoFornecedor getDadosDoFornecedor();

	@NotNull
	DadosDoCliente getDadosDoCliente();

	/**
	 * 
	 * Define o Nº de Série do Equipamento (ex. Computador, Monitor...) (<strong>Não</strong> podendo ficar em branco).
	 */
	void setNumeroDeSerie(String numeroDeSerie);

	/**
	 * 
	 * Define a Descrição do Problema (<strong>Não</strong> podendo ficar em branco).
	 */
	void setDescricaoDoProblema(DescricaoProblema descricaoDoProblema);

	/**
	 * 
	 * Define qual os Dados do Chamado a qual esta Solicitação pertence (<strong>Não</strong> podendo ficar em nulo).
	 */
	void setChamado(Chamado chamado);

//	/**
//	 * 
//	 * Define o E-mail que será enviado para o fabricante/fornecedor do equipamento (<strong>Não</strong> podendo ficar nulo).
//	 */
//    void setEmailDeGarantia(Email emailDeGarantia);

    void setDadosDoFornecedor(DadosDoFornecedor dadosDoFornecedor);

    void setDadosDoCliente(DadosDoCliente dadosDoCliente);

}
