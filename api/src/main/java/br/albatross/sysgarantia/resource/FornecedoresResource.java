package br.albatross.sysgarantia.resource;

import java.net.URI;

import br.albatross.sysgarantia.domain.models.fornecedor.DadosParaAtualizacaoDeFornecedor;
import br.albatross.sysgarantia.domain.models.fornecedor.DadosParaCadastroDeNovoFornecedor;
import br.albatross.sysgarantia.domain.models.garantia.apis.fornecedores.DadosDoFornecedor;
import br.albatross.sysgarantia.domain.services.fornecedores.FornecedoresService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Positive;
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
    UriInfo uriInfo;

    @GET
    public Response fornecedores() {
        return Response.ok(fornecedoresService.listarFornecedoresDisponiveis()).build();
    }

    @POST
    @Transactional
    public Response cadastrarNovo(DadosParaCadastroDeNovoFornecedor novoFornecedor) {
        DadosDoFornecedor fornecedor = 
                fornecedoresService.cadastrarNovoFornecedor(novoFornecedor);

       URI novoFornecedorURI = 
               uriInfo.getRequestUriBuilder().path(String.valueOf(fornecedor.getId())).build(fornecedor);

       return Response.created(novoFornecedorURI).entity(fornecedor).build();

    }

    @PUT
    @Transactional
    public Response atualizar(DadosParaAtualizacaoDeFornecedor dadosAtualizados) {
        fornecedoresService.atualizarFornecedor(dadosAtualizados);
        return Response.noContent().build();
    }

    @GET
    @Path("/{id}")
    public Response peloId(@Positive @PathParam("id") int fornecedorId) {
        return fornecedoresService
                .buscarPorId(fornecedorId)
                .map(fornecedor -> Response.ok(fornecedor).build())
                .orElse(Response.status(Status.NOT_FOUND).build());
    }

}














