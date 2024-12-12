package br.albatross.sysgarantia.resource;

import java.net.URI;

import br.albatross.sysgarantia.dto.fornecedor.DadosParaAtualizacaoDeFornecedor;
import br.albatross.sysgarantia.dto.fornecedor.DadosParaCadastroDeNovoFornecedor;
import br.albatross.sysgarantia.models.Fornecedor;
import br.albatross.sysgarantia.repositories.FornecedorRepository;
import br.albatross.sysgarantia.services.fornecedores.FornecedoresService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.ws.rs.Consumes;
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

@Path("/fornecedores")
@Produces(MediaType.APPLICATION_JSON)
public class FornecedoresResource {

    @Inject
    FornecedoresService fornecedoresService;

    @Inject
    FornecedorRepository fornecedorRepository;

    @Inject
    UriInfo uriInfo;

    @GET
    public Response fornecedores() {
        return Response.ok(fornecedorRepository.findAll()).build();
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarNovo(@Valid DadosParaCadastroDeNovoFornecedor novoFornecedor) {
        Fornecedor fornecedor = 
                fornecedoresService.cadastrarNovoFornecedor(novoFornecedor);

       URI novoFornecedorURI = 
               uriInfo.getRequestUriBuilder().path("/{id}").build(fornecedor.getId());

       return Response.created(novoFornecedorURI).entity(fornecedor).build();

    }

    @PUT
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizar(@Valid DadosParaAtualizacaoDeFornecedor dadosAtualizados) {
        fornecedoresService.atualizarFornecedor(dadosAtualizados);
        return Response.noContent().build();
    }

    @GET
    @Path("/{id}")
    public Response peloId(@PathParam("id") @Positive int fornecedorId) {
        return fornecedorRepository
                .findById(fornecedorId)
                .map(fornecedor -> Response.ok(fornecedor).build())
                .orElse(Response.status(Status.NOT_FOUND).build());
    }

}
