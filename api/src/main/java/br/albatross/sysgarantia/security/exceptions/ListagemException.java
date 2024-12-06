package br.albatross.sysgarantia.security.exceptions;

public class ListagemException extends UsuarioServiceException {

	private static final long serialVersionUID = 1L;

	public ListagemException(String mensagem, String mensagemDetalhada) {
		super(mensagem, mensagemDetalhada);
	}

}
