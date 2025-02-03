package br.albatross.sysgarantia.resource;

import java.net.URI;

import br.albatross.sysgarantia.dto.emailtemplate.DadosParaAtualizacaoDeEmailTemplate;
import br.albatross.sysgarantia.dto.emailtemplate.DadosParaCadastroDeEmailTemplate;
import br.albatross.sysgarantia.models.EmailTemplate;
import br.albatross.sysgarantia.repositories.EmailTemplateRepository;
import br.albatross.sysgarantia.services.emailtemplate.EmailTemplateService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriInfo;

@Path("/emailstemplates")
public class EmailsTemplatesResource {

    @Inject
    UriInfo uriInfo;    

    @Inject
    EmailTemplateService emailsTemplatesService;

    @Inject
    EmailTemplateRepository emailsTemplatesRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarTodos() {
        return Response.ok(emailsTemplatesRepository.findAll()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorId(@Positive @PathParam("id") int id) {
        return emailsTemplatesRepository.findById(id)
                .map(emailTemplate -> Response.ok(emailTemplate).build())
                .orElse(Response.status(Status.NOT_FOUND).build());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarNovo(@Valid DadosParaCadastroDeEmailTemplate novosDados) {
        EmailTemplate novoModelo = emailsTemplatesService.cadastrar(novosDados);
        URI novaUri = uriInfo.getRequestUriBuilder().path("/{id}").build(novoModelo.getId());
        return Response.created(novaUri).entity(novoModelo).build();
    }

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizar(@Valid DadosParaAtualizacaoDeEmailTemplate dadosAtualizados) {
        emailsTemplatesService.atualizar(dadosAtualizados);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response excluirPorId(@Positive @PathParam("id") int id) {
        return emailsTemplatesService.excluirPorId(id) ? 
                Response.noContent().build() : 
                Response.status(Status.NOT_FOUND).build();
    }

}
