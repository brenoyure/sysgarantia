package br.albatross.sysgarantia.domain.models.garantia.apis.chamado;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode(of = "idDoServico")
@AllArgsConstructor @NoArgsConstructor
public class DadosDoServico implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idDoServico;
	private String  nomeDoServico;
	
}
