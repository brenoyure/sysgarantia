package br.albatross.sysgarantia.domain.services.clientes.validacoes;

import br.albatross.sysgarantia.domain.models.cliente.DadosAtualizacaoCliente;

/**
 * 
 * Representa uma validação que podem ser executadas durante o cadastro de um novo cliente.
 * 
 * @author breno.brito
 * 
 */
public interface ValidacaoAtualizacaoCliente {

    void validar(DadosAtualizacaoCliente dadosDoCliente);

}
