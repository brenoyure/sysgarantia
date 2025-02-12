package br.albatross.sysgarantia.resource;

import br.albatross.sysgarantia.dto.problemas.AtualizacaoDescricaoProblemaDto;
import br.albatross.sysgarantia.dto.problemas.NovaDescricaoProblemaDto;
import br.albatross.sysgarantia.models.DescricaoProblema;
import br.albatross.sysgarantia.repositories.DescricaoProblemaRepository;
import br.albatross.sysgarantia.services.problemas.DescricaoProblemaService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.ws.rs.Consumes;
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

@Path("/descricaoProblemas")
@Produces(MediaType.APPLICATION_JSON)
public class DescricaoProblemasResource {

    @Inject
    UriInfo uriInfo;

    @Inject
    DescricaoProblemaService descricaoProblemaService;

    @Inject
    DescricaoProblemaRepository descricaoProblemasRepository;

    @GET
    public Response buscarTodos() {
        return Response.ok(descricaoProblemasRepository.findAllAsDtoOrderByCategoriaAsc()).build();
    }

    @GET
    @Path("/allattrs")
    public Response buscarComTodosOsAtributos() {
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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarNovo(NovaDescricaoProblemaDto dadosNovaDescricao) {
        DescricaoProblema novaDescricao = descricaoProblemaService.cadastrar(dadosNovaDescricao);
        return Response
                .created(uriInfo
                        .getRequestUriBuilder()
                        .path("/{id}")
                        .build(novaDescricao.getId()))
                .build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarExistente(AtualizacaoDescricaoProblemaDto dadosAtualizacao) {
        descricaoProblemaService.atualizar(dadosAtualizacao);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response excluirPorId(@PathParam("id") @NotNull @Positive Integer id) {
        return descricaoProblemasRepository.deleteById(id) ?
                Response.noContent().build() :
                Response.status(Status.NOT_FOUND).build();
    }

}
