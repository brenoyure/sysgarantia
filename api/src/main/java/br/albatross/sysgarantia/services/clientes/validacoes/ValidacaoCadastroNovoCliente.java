package br.albatross.sysgarantia.services.clientes.validacoes;

import br.albatross.sysgarantia.dto.cliente.DadosParaCadastroDeCliente;

/**
 * 
 * Representa uma validação que podem ser executadas durante o cadastro de um novo cliente.
 * 
 * @author breno.brito
 * 
 */
public interface ValidacaoCadastroNovoCliente {

    void validar(DadosParaCadastroDeCliente dadosDoNovoCliente);

}
