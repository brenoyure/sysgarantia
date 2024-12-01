package br.albatross.sysgarantia.resource;

import java.net.URI;

import br.albatross.sysgarantia.domain.models.cliente.DadosAtualizacaoCliente;
import br.albatross.sysgarantia.domain.models.cliente.DadosParaCadastroDeCliente;
import br.albatross.sysgarantia.domain.models.garantia.apis.cliente.DadosDoCliente;
import br.albatross.sysgarantia.domain.services.clientes.ClientesService;
import br.albatross.sysgarantia.persistence.repositories.cliente.ClienteRepository;

import jakarta.inject.Inject;

import jakarta.transaction.Transactional;

import jakarta.validation.Valid;
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

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
public class ClientesResource {

    @Inject
    ClienteRepository clienteRepository;

    @Inject
    ClientesService clienteService;

    @Inject
    UriInfo uriInfo;

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarNovo(@Valid DadosParaCadastroDeCliente novoCliente) {
        DadosDoCliente dadosNovoCliente = 
                clienteService.cadastrarNovoCliente(novoCliente);
        URI novoClienteURI = 
                uriInfo.getRequestUriBuilder().path(String.valueOf(dadosNovoCliente.getId())).build();

        return Response.created(novoClienteURI).location(novoClienteURI).build();

    }

    @PUT
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarCadastro(@Valid DadosAtualizacaoCliente dadosAtualizados) {
        clienteService.atualizarCadastroDeCliente(dadosAtualizados);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") @Positive int id) {
        return clienteService
                .buscarPorId(id)
                .map(cliente -> Response.ok(cliente).build())
                .orElse(Response.status(Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response excluir(@PathParam("id") @Positive int id) {
        return clienteRepository.deleteById(id) ? 
                Response.noContent().build() : 
                Response.status(Status.NOT_FOUND).build();
    }

    @GET
    public Response buscarTodos() {
        return Response.ok(clienteRepository.findAllAsComboboxOrderByNome()).build();
    }

}
