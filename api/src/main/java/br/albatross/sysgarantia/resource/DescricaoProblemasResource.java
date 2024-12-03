package br.albatross.sysgarantia.resource;

import br.albatross.sysgarantia.repositories.DescricaoProblemaRepository;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/descricaoProblemas")
@Produces(MediaType.APPLICATION_JSON)
public class DescricaoProblemasResource {

    @Inject
    DescricaoProblemaRepository descricaoProblemasRepository;

    @GET
    public Response buscarTodos() {
        return Response.ok(descricaoProblemasRepository.findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") @NotNull @Positive Integer id) {
        return descricaoProblemasRepository
                .findById(id)
                .map(problema -> Response.ok(problema).build())
                .orElse(Response.status(Status.NOT_FOUND).build());
    }

}
