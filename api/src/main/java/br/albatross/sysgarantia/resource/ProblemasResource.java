package br.albatross.sysgarantia.resource;

import br.albatross.sysgarantia.models.Problema;
import br.albatross.sysgarantia.repositories.ProblemaRepository;

import jakarta.inject.Inject;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriInfo;

@Path("/problemas")
@Produces(MediaType.APPLICATION_JSON)
public class ProblemasResource {

    @Inject
    UriInfo uriInfo;

    @Inject
    ProblemaRepository problemaRepository;

    @POST
    @Transactional
    public Response cadastrarNovo(@Valid Problema problema) {

        if (problemaRepository.existsByTipo(problema.getTipo())) {
            throw new ValidationException("Já existe outra categoria cadastrada com o Tipo informado");
        }

        problema.setId(null);

        problemaRepository.persist(problema);
        return Response
                .created(uriInfo.getRequestUriBuilder().path("/{id}").build(problema.getId()))
                .entity(problema)
                .build();
    }

    @PUT
    @Transactional
    public Response atualizar(@Valid Problema problema) {
        if (problema.getId() == null || !problemaRepository.existsById(problema.getId())) {
            return Response.status(Status.NOT_FOUND).build();
        }

        if (problemaRepository.existsByTipoAndNotById(problema.getTipo(), problema.getId())) {
            throw new ValidationException("Já existe outra categoria cadastrada com o Tipo informado");
        }

        problemaRepository.merge(problema);
        return Response.noContent().build();
    }

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

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response excluirPorId(@PathParam("id") @NotNull @Positive Short id) {
        if (problemaRepository.hasDescricaoProblemas(id)) {
            throw new ValidationException("A Categoria possui Textos Prontos (Descrições de Problemas) associados a ela, exclusão não permitda");
        }
        return problemaRepository.deleteById(id) ?
                Response.noContent().build():
                Response.status(Status.NOT_FOUND).build();
    }

}
