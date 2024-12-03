package br.albatross.sysgarantia.services.fornecedores;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.albatross.sysgarantia.dto.fornecedor.DadosDoFornecedor;
import br.albatross.sysgarantia.dto.fornecedor.DadosParaAtualizacaoDeFornecedor;
import br.albatross.sysgarantia.dto.fornecedor.DadosParaCadastroDeNovoFornecedor;
import br.albatross.sysgarantia.models.Fornecedor;
import br.albatross.sysgarantia.repositories.FornecedorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

@ApplicationScoped
public class FornecedoresServiceImpl implements FornecedoresService {

    @Inject
    private FornecedorRepository repository;

    @Override
    public Fornecedor cadastrarNovoFornecedor(@Valid DadosParaCadastroDeNovoFornecedor novosDados) {

        if (repository.existsByNome(novosDados.getNome())) {
            throw new ValidationException("Já existe outro Fornecedor cadastrado com o nome informado");
        }

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(novosDados.getNome());
        fornecedor.setEmails(novosDados.getEmails());
        fornecedor.getIdsDosServicosDoFornecedorNoSistemaDeChamados().addAll(novosDados.getIdsDosServicosDoFornecedorNoSistemaDeChamados());

        return repository.persist(fornecedor);

    }

    @Override
    public List<DadosDoFornecedor> listarFornecedoresDisponiveis() {
        return repository.findAll().stream().map(DadosDoFornecedor::new).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public void excluirFornecedorPeloId(int id) {

        repository.deleteById(id);

    }

    @Override
    public void atualizarFornecedor(@Valid DadosParaAtualizacaoDeFornecedor dados) {

        if (repository.existsByNomeAndNotById(dados.getNome(), dados.getId())) {
            throw new ValidationException("Já existe outro Fornecedor cadastrado com o nome informado");
        }

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(dados.getId());
        fornecedor.setNome(dados.getNome());
        fornecedor.setEmails(dados.getEmails());
        fornecedor.getIdsDosServicosDoFornecedorNoSistemaDeChamados().addAll(dados.getIdsDosServicosDoFornecedorNoSistemaDeChamados());

        repository.merge(fornecedor);        

    }

    @Override
    public Optional<DadosDoFornecedor> buscarPorId(int id) {
        return repository.findById(id).map(DadosDoFornecedor::new);
    }

    @Override
    public List<Integer> listarOsIdsDosServicosDoFornecedor(int idDoFornecedor) {

        return repository.findIdsDosServicosDoFornecedorNoSistemaDeChamadosById(idDoFornecedor);

    }

}