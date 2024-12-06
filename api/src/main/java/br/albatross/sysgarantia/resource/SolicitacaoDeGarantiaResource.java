package br.albatross.sysgarantia.resource;

import br.albatross.sysgarantia.dto.garantia.DadosParaNovaSolicitacaoDeGarantia;

import jakarta.validation.Valid;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/solicitacaoGarantia")
public class SolicitacaoDeGarantiaResource {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response solicitar(@Valid DadosParaNovaSolicitacaoDeGarantia solicitacao) {
        return Response.ok(solicitacao).build();
    }

}
