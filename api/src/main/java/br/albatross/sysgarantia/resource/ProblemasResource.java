package br.albatross.sysgarantia.resource;

import br.albatross.sysgarantia.persistence.repositories.problemas.ProblemaRepository;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/problemas")
@Produces(MediaType.APPLICATION_JSON)
public class ProblemasResource {

    @Inject
    ProblemaRepository problemaRepository;

    @GET
    @RolesAllowed({"USER"})
    public Response buscarTodos() {
        return Response.ok(problemaRepository.findAll()).build();
    }
    
}
