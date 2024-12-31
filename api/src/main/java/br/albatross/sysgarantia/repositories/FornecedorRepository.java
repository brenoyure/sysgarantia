package br.albatross.sysgarantia.repositories;

import java.util.List;

import br.albatross.sysgarantia.models.Fornecedor;

public interface FornecedorRepository extends Repository<Fornecedor, Integer> {

    boolean existsByNome(String nome);

    boolean existsByNomeAndNotById(String nome, int id);

    List<Integer> findIdsDosServicosDoFornecedorNoSistemaDeChamadosById(int id);

}
