package br.albatross.sysgarantia.domain.services.garantia;

import br.albatross.sysgarantia.domain.models.garantia.apis.solicitacao.SolicitacaoDeGarantia;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;

/**
*
* CDI Bean responsável por colocar os <code>Email</code>s de Garantia na fila de mensagens
*
* @author breno.brito
*/
@ApplicationScoped
public class SolicitacaoService {

	public void solicitarGarantia(@Valid SolicitacaoDeGarantia solicitacaoDeGarantia) {
	    //TODO Needs Reimplementation
	}

}
