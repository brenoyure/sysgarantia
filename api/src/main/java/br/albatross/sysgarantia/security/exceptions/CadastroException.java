package br.albatross.sysgarantia.security.exceptions;

public class CadastroException extends UsuarioServiceException {

	private static final long serialVersionUID = 1L;

	public CadastroException(String mensagem, String mensagemDetalhada) {
		super(mensagem, mensagemDetalhada);
	}
	
}
