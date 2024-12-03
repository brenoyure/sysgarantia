package br.albatross.sysgarantia.domain.services.fornecedores;

import java.util.List;
import java.util.Optional;

import br.albatross.sysgarantia.domain.models.fornecedor.DadosParaAtualizacaoDeFornecedor;
import br.albatross.sysgarantia.domain.models.fornecedor.DadosParaCadastroDeNovoFornecedor;
import br.albatross.sysgarantia.domain.models.garantia.apis.fornecedores.DadosDoFornecedor;
import jakarta.validation.Valid;

public interface FornecedoresService {

    DadosDoFornecedor cadastrarNovoFornecedor(@Valid DadosParaCadastroDeNovoFornecedor dados);

    List<DadosDoFornecedor> listarFornecedoresDisponiveis();

    void excluirFornecedorPeloId(int id);

    DadosDoFornecedor atualizarFornecedor(@Valid DadosParaAtualizacaoDeFornecedor dados);

    Optional<DadosDoFornecedor> buscarPorId(int id);

    List<Integer> listarOsIdsDosServicosDoFornecedor(int idDoFornecedor);
}
