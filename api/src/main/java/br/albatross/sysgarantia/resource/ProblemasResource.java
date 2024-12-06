package br.albatross.sysgarantia.resource;

import br.albatross.sysgarantia.repositories.ProblemaRepository;

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

@Path("/problemas")
@Produces(MediaType.APPLICATION_JSON)
public class ProblemasResource {

    @Inject
    ProblemaRepository problemaRepository;

    @GET
    public Response buscarTodos() {
        return Response.ok(problemaRepository.findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") @NotNull @Positive Short id) {
        return problemaRepository
                .findById(id).map(problema -> Response.ok(problema).build())
                .orElse(Response.status(Status.NOT_FOUND).build());
    }

}
