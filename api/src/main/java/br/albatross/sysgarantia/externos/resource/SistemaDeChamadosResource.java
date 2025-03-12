package br.albatross.sysgarantia.externos.resource;

import java.util.List;
// import java.util.ArrayList;

import br.albatross.sysgarantia.externos.SistemaDeChamados;
// import br.albatross.sysgarantia.externos.dto.*;

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
        // List<Chamado> chamados = new ArrayList<>();
        // chamados.add( new Chamado(486L, "2025010682000486", "Monitor com Tela Piscando", 100, "Monitor DATEN", "usuario@example.com") );
        // chamados.add( new Chamado(487L, "2025010682000487", "PC Não Liga", 100, "Gabinete DATEN", "usuario@example.com") );
        // chamados.add( new Chamado(488L, "2025010682000488", "Tecla ENTER sem funcionar", 100, "Teclado DATEN", "usuario@example.com") );
        // chamados.add( new Chamado(489L, "2025010682000489", "Mouse com clique duplo", 100, "Mouse DATEN", "usuario@example.com") );

        // return Response.ok(chamados).build();
        return Response.ok(sistemaDeChamados.findByService(ids)).build();
    }

    @GET
    @Path("/chamados/servicos")
    public Response listarServicosDosChamados() {
        // List<Servico> servicos = new ArrayList<>();
        // servicos.add(new Servico(98, "Garantia DATEN::Gabinete DATEN"));
        // servicos.add(new Servico(98, "Garantia DATEN::Mouse DATEN"));
        // servicos.add(new Servico(98, "Garantia DATEN::Monitor DATEN"));
        // servicos.add(new Servico(98, "Garantia DATEN::Teclado DATEN"));

        // return Response.ok(servicos).build();
        return Response.ok(sistemaDeChamados.findAllTicketsServices()).build();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/inventario/{identificador}")
    public Response buscarNumeroDeSeriePeloIdentificador(@NotBlank @PathParam("identificador") String identificador) {
        // if (identificador.equals("204703")) {
        //     return Response.ok("AVCLX486").build();
        // }
        // return Response.status(Status.NOT_FOUND).build();
           return sistemaDeChamados
                    .findSerialNumberByIdentifier(identificador)
                    .map(numeroDeSerie -> Response.ok(numeroDeSerie).build())
                    .orElse(Response.status(Status.NOT_FOUND).build());
    }

}

//return Response.ok(List.of(new Chamado(486L, "2025010682000486", "Monitor com Tela Piscando", 100, "Monitor DATEN", "usuario@example.com"))).build();
//return Response.ok(sistemaDeChamados.findByService(ids)).build();
