package br.albatross.sysgarantia.services.relatorios;

/**
 * 
 * Representa uma exception que pode ocorrer ao gerar um relatório
 * 
 * @author breno.brito
 */
public class ServicoDeRelatorioException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ServicoDeRelatorioException(String message) {
        super(message);
    }

}
