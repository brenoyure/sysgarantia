package br.albatross.sysgarantia.services.clientes;

import java.util.List;
import java.util.Optional;

import br.albatross.sysgarantia.dto.cliente.DadosAtualizacaoCliente;
import br.albatross.sysgarantia.dto.cliente.DadosDoCliente;
import br.albatross.sysgarantia.dto.cliente.DadosParaCadastroDeCliente;
import br.albatross.sysgarantia.models.Cliente;
import jakarta.validation.Valid;

public interface ClientesService {

    Cliente cadastrarNovoCliente(@Valid DadosParaCadastroDeCliente dadosDoNovoCliente);

    void atualizarCadastroDeCliente(@Valid DadosAtualizacaoCliente dadosAtualizados);

    Optional<DadosDoCliente> buscarPorId(int id);

    List<DadosDoCliente> listarClientesDisponiveis();

    void excluirClientePeloId(int id);

}
