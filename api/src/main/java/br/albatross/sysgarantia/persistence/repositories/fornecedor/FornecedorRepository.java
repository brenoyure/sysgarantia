package br.albatross.sysgarantia.persistence.repositories.fornecedor;

import java.util.List;

import br.albatross.sysgarantia.domain.models.fornecedor.FornecedorComboBox;
import br.albatross.sysgarantia.persistence.entities.fornecedor.Fornecedor;
import br.albatross.sysgarantia.persistence.repositories.Repository;

public interface FornecedorRepository extends Repository<Fornecedor, Integer> {

    boolean existsByNome(String nome);

    boolean existsByNomeAndNotById(String nome, int id);

    List<Integer> findIdsDosServicosDoFornecedorNoSistemaDeChamadosById(int id);

    List<FornecedorComboBox> findAllAsFornecedorComboBoxOrderByNome();

}
