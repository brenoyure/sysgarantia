package br.albatross.sysgarantia.externos.resource;

import java.util.List;

import br.albatross.sysgarantia.externos.SistemaDeChamados;

import jakarta.inject.Inject;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/sistemaDeChamados")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SistemaDeChamadosResource {

    @Inject
    SistemaDeChamados sistemaDeChamados;

    @POST
    @Path("/chamados")
    public Response listarChamadosPelosServicosDoFornecedor(@NotEmpty List<Integer> ids) {
        return Response.ok(sistemaDeChamados.findByService(ids)).build();
    }

    @GET
    @Path("/chamados/servicos")
    public Response listarServicosDosChamados() {
        return Response.ok(sistemaDeChamados.findAllTicketsServices()).build();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/inventario/{identificador}")
    public Response buscarNumeroDeSeriePeloIdentificador(@NotBlank @PathParam("identificador") String identificador) {
        return sistemaDeChamados
                .findSerialNumberByIdentifier(identificador)
                .map(numeroDeSerie -> Response.ok(numeroDeSerie).build())
                .orElse(Response.status(Status.NOT_FOUND).build());
    }

}

//return Response.ok(List.of(new Chamado(486L, "2025010682000486", "Monitor com Tela Piscando", 100, "Monitor DATEN", "usuario@example.com"))).build();
//return Response.ok(sistemaDeChamados.findByService(ids)).build();
