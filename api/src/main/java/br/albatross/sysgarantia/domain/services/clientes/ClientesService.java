package br.albatross.sysgarantia.domain.services.clientes;

import java.util.List;
import java.util.Optional;

import br.albatross.sysgarantia.domain.models.cliente.DadosAtualizacaoCliente;
import br.albatross.sysgarantia.domain.models.cliente.DadosParaCadastroDeCliente;
import br.albatross.sysgarantia.domain.models.garantia.apis.cliente.DadosDoCliente;
import jakarta.validation.Valid;

public interface ClientesService {

    DadosDoCliente cadastrarNovoCliente(@Valid DadosParaCadastroDeCliente dadosDoNovoCliente);

    DadosDoCliente atualizarCadastroDeCliente(@Valid DadosAtualizacaoCliente dadosAtualizados);

    Optional<DadosDoCliente> buscarPorId(int id);

    List<DadosDoCliente> listarClientesDisponiveis();

    void excluirClientePeloId(int id);

}
