package br.albatross.sysgarantia.domain.services.clientes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.albatross.sysgarantia.domain.models.cliente.DadosAtualizacaoCliente;
import br.albatross.sysgarantia.domain.models.cliente.DadosDoClienteDto;
import br.albatross.sysgarantia.domain.models.cliente.DadosParaCadastroDeCliente;
import br.albatross.sysgarantia.domain.models.garantia.apis.cliente.DadosDoCliente;
import br.albatross.sysgarantia.domain.services.clientes.validacoes.ValidacaoAtualizacaoCliente;
import br.albatross.sysgarantia.domain.services.clientes.validacoes.ValidacaoCadastroNovoCliente;
import br.albatross.sysgarantia.persistence.entities.cliente.Cliente;
import br.albatross.sysgarantia.persistence.repositories.cliente.ClienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

@ApplicationScoped
public class ClientesServiceImpl implements ClientesService {

    @Inject
    private ClienteRepository clienteRepository;

    @Inject
    private Instance<ValidacaoCadastroNovoCliente> validacoesNovoCliente;

    @Inject
    private Instance<ValidacaoAtualizacaoCliente> validacoesAtualizacaoCliente;

    @Override
    public DadosDoCliente cadastrarNovoCliente(@Valid DadosParaCadastroDeCliente dadosDoNovoCliente) {

        validacoesNovoCliente.forEach(validacao -> validacao.validar(dadosDoNovoCliente));

        Cliente novoCliente = new Cliente(dadosDoNovoCliente);
        clienteRepository.persist(novoCliente);

        return new DadosDoClienteDto(novoCliente);

    }

    @Override
    public DadosDoCliente atualizarCadastroDeCliente(@Valid DadosAtualizacaoCliente dadosAtualizados) {

        validacoesAtualizacaoCliente.forEach(validacao -> validacao.validar(dadosAtualizados));

        Cliente cliente = new Cliente(dadosAtualizados);
        cliente.setId(dadosAtualizados.getId());
        
        return new DadosDoClienteDto(clienteRepository.merge(cliente));

    }

    @Override
    public List<DadosDoCliente> listarClientesDisponiveis() {
        return clienteRepository
                .findAll()
                .stream()
                .map(DadosDoClienteDto::new)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public void excluirClientePeloId(int id) {

        clienteRepository.deleteById(id);

    }

    @Override
    public Optional<DadosDoCliente> buscarPorId(int id) {
        return clienteRepository.findById(id).map(DadosDoClienteDto::new);
    }

}
