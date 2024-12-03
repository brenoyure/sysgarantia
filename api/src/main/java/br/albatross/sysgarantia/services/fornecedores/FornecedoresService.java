package br.albatross.sysgarantia.services.fornecedores;

import java.util.List;
import java.util.Optional;

import br.albatross.sysgarantia.dto.fornecedor.DadosDoFornecedor;
import br.albatross.sysgarantia.dto.fornecedor.DadosParaAtualizacaoDeFornecedor;
import br.albatross.sysgarantia.dto.fornecedor.DadosParaCadastroDeNovoFornecedor;
import br.albatross.sysgarantia.models.Fornecedor;
import jakarta.validation.Valid;

public interface FornecedoresService {

    Fornecedor cadastrarNovoFornecedor(@Valid DadosParaCadastroDeNovoFornecedor dados);

    List<DadosDoFornecedor> listarFornecedoresDisponiveis();

    void excluirFornecedorPeloId(int id);

    void atualizarFornecedor(@Valid DadosParaAtualizacaoDeFornecedor dados);

    Optional<DadosDoFornecedor> buscarPorId(int id);

    List<Integer> listarOsIdsDosServicosDoFornecedor(int idDoFornecedor);
}
