package br.albatross.sysgarantia.resource;

import br.albatross.sysgarantia.persistence.repositories.emailtemplate.EmailTemplateRepository;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/emailstemplates")
@Produces(MediaType.APPLICATION_JSON)
public class EmailsTemplatesResource {

    @Inject
    EmailTemplateRepository emailsTemplatesRepository;

    @GET
    public Response buscarTodos() {

        return Response.ok(emailsTemplatesRepository.findAll()).build();

    }

}
