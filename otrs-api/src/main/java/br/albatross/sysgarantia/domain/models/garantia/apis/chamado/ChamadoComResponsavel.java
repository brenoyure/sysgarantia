package br.albatross.sysgarantia.domain.models.garantia.apis.chamado;

import jakarta.validation.constraints.NotNull;

public interface ChamadoComResponsavel extends DadosDoChamado {

	@NotNull
	DadosDoResponsavelPeloChamado getDadosDoResponsavelPeloChamado();

	void setDadosDoResponsavel(DadosDoResponsavelPeloChamado dadosDoResponsavel);

}
