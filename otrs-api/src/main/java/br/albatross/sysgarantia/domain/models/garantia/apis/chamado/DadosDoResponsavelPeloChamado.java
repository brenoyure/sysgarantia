package br.albatross.sysgarantia.domain.models.garantia.apis.chamado;

import jakarta.validation.constraints.NotBlank;

public interface DadosDoResponsavelPeloChamado {

	@NotBlank
	String getLogin();

	@NotBlank
	String getNomeCompleto();

}
