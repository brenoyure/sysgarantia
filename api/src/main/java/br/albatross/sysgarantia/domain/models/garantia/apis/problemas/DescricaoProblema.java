package br.albatross.sysgarantia.domain.models.garantia.apis.problemas;

import java.io.Serializable;

public interface DescricaoProblema extends Serializable {

	Number getId();
	String getDescricaoDetalhada();
	Problema getProblema();

}
