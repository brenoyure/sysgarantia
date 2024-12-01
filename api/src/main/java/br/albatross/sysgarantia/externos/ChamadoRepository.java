package br.albatross.sysgarantia.externos;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import br.albatross.sysgarantia.domain.models.garantia.apis.chamado.DadosDoChamado;
import jakarta.validation.constraints.NotEmpty;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * 
 * Representa o contexto de persistência (DAO) com o Sistema de Chamados.
 * 
 * @author breno.brito
 * 
 */
@Path("/chamados")
@RegisterRestClient
public interface ChamadoRepository {

    /**
     * 
     * Retorna uma lista de chamados filtrada pelo ID do Serviço.
     * 
     * @param serviceIds
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	List<DadosDoChamado> findByService(@NotEmpty List<Integer> servicesIds);

}
