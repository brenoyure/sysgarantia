package br.albatross.sysgarantia.persistence.repositories.cliente;

import java.util.List;

import br.albatross.sysgarantia.domain.models.cliente.ClienteComboBox;
import br.albatross.sysgarantia.persistence.entities.cliente.Cliente;
import br.albatross.sysgarantia.persistence.repositories.Repository;

public interface ClienteRepository extends Repository<Cliente, Integer> {

    boolean existsByNome(String nome);

    boolean existsByDescricao(String descricao);

    boolean existsByNomeAndNotById(String nome, int id);

    boolean existsByDescricaoAndNotById(String descricao, int id);

    List<ClienteComboBox> findAllAsComboboxOrderByNome();

}