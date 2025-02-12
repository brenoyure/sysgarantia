package br.albatross.sysgarantia.services.clientes;

import br.albatross.sysgarantia.dto.cliente.DadosAtualizacaoCliente;
import br.albatross.sysgarantia.dto.cliente.DadosParaCadastroDeCliente;
import br.albatross.sysgarantia.models.Cliente;
import br.albatross.sysgarantia.repositories.ClienteRepository;
import br.albatross.sysgarantia.services.clientes.validacoes.ValidacaoAtualizacaoCliente;
import br.albatross.sysgarantia.services.clientes.validacoes.ValidacaoCadastroNovoCliente;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

@ApplicationScoped
public class ClientesService {

    @Inject
    private ClienteRepository clienteRepository;

    @Inject
    private Instance<ValidacaoCadastroNovoCliente> validacoesNovoCliente;

    @Inject
    private Instance<ValidacaoAtualizacaoCliente> validacoesAtualizacaoCliente;

    public Cliente cadastrarNovoCliente(@Valid DadosParaCadastroDeCliente dadosDoNovoCliente) {

        validacoesNovoCliente.forEach(validacao -> validacao.validar(dadosDoNovoCliente));

        Cliente novoCliente = new Cliente(dadosDoNovoCliente);
        clienteRepository.persist(novoCliente);

        return novoCliente;

    }

    public void atualizarCadastroDeCliente(@Valid DadosAtualizacaoCliente dadosAtualizados) {

        validacoesAtualizacaoCliente.forEach(validacao -> validacao.validar(dadosAtualizados));

        Cliente cliente = new Cliente(dadosAtualizados);
        cliente.setId(dadosAtualizados.getId());

        clienteRepository.merge(cliente);

    }

}
