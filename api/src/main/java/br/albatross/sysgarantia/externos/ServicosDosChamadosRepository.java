package br.albatross.sysgarantia.externos;

import java.util.List;

import br.albatross.sysgarantia.domain.models.garantia.apis.chamado.DadosDoServico;

/**
 * 
 * Representa o contexto de persistência (DAO) com algum Sistema de Chamados, 
 * por exemplo, exibindo a lista de Serviços que os chamados podem possuir.
 * 
 * @author breno.brito
 * 
 */
public interface ServicosDosChamadosRepository {

    List<DadosDoServico> findAll();

}
