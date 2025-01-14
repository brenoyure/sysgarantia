package br.albatross.sysgarantia.repositories;

import br.albatross.sysgarantia.models.Cliente;

public interface ClienteRepository extends Repository<Cliente, Integer> {

    boolean existsByNome(String nome);

    boolean existsByDescricao(String descricao);

    boolean existsByNomeAndNotById(String nome, int id);

    boolean existsByDescricaoAndNotById(String descricao, int id);

}