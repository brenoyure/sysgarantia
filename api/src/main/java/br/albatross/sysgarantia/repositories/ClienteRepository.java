package br.albatross.sysgarantia.repositories;

import java.util.List;

import br.albatross.sysgarantia.dto.cliente.ClienteComboBox;
import br.albatross.sysgarantia.models.Cliente;

public interface ClienteRepository extends Repository<Cliente, Integer> {

    boolean existsByNome(String nome);

    boolean existsByDescricao(String descricao);

    boolean existsByNomeAndNotById(String nome, int id);

    boolean existsByDescricaoAndNotById(String descricao, int id);

    List<ClienteComboBox> findAllAsComboboxOrderByNome();

}