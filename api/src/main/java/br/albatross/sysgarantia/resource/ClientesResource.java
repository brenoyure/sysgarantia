package br.albatross.sysgarantia.resource;

import br.albatross.sysgarantia.persistence.repositories.cliente.ClienteRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
public class ClientesResource {

    @Inject
    ClienteRepository clienteRepository;

    @GET
    public Response buscarTodos() {

        return Response.ok(clienteRepository.findAll()).build();

    }

}
