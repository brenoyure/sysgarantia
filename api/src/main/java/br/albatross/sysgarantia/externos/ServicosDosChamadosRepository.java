package br.albatross.sysgarantia.externos;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import br.albatross.sysgarantia.domain.models.garantia.apis.chamado.DadosDoServico;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * 
 * Representa o contexto de persistência (DAO) com algum Sistema de Chamados, 
 * por exemplo, exibindo a lista de Serviços que os chamados podem possuir.
 * 
 * @author breno.brito
 * 
 */
@RegisterRestClient
@Path("/chamados")
public interface ServicosDosChamadosRepository {

    @GET
    @Path("/servicos")
    @Produces(MediaType.APPLICATION_JSON)
    List<DadosDoServico> findAll();

}
