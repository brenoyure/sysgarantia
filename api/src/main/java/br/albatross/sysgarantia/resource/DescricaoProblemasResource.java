package br.albatross.sysgarantia.resource;

import br.albatross.sysgarantia.persistence.repositories.problemas.DescricaoProblemaRepository;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/descricaoProblemas")
@Produces(MediaType.APPLICATION_JSON)
public class DescricaoProblemasResource {

    @Inject
    DescricaoProblemaRepository descricaoProblemasRepository;

    @GET
    @RolesAllowed({"ADMIN"})
    public Response buscarTodos() {
        return Response.ok(descricaoProblemasRepository.findAll()).build();
    }

}
