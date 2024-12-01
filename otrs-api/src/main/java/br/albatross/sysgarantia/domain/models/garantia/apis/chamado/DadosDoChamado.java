package br.albatross.sysgarantia.domain.models.garantia.apis.chamado;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public interface DadosDoChamado extends Serializable {

	@NotNull
	Long getId();

	@NotBlank
	String getNumeroDoChamado();

	@NotBlank
	String getTitulo();	

	DadosDoServico getDadosDoServico();

	@NotNull
	DadosDoUsuarioCliente getDadosDoUsuarioCliente();

}
