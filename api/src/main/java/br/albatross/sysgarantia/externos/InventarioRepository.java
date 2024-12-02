package br.albatross.sysgarantia.externos;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.validation.constraints.NotBlank;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/inventario")
@RegisterRestClient
public interface InventarioRepository {

    /**
     * Retorna o número de série de algum equipamento, recebendo como 
     * parâmetro algum identificador que o sistema possua, por exemplo número de patrimônio.
     * 
     * TODO Implementar um MessageBodyReader<Optional<String>> ainda.
     *
     * @author breno.brito
     */
    @GET
    @Path("/{identificador}")
    @Produces(MediaType.TEXT_PLAIN)
	String findSerialNumberByIdentifier(@PathParam("identificador") @NotBlank String identificador);

}
