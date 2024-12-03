package br.albatross.sysgarantia.security.resource;

import br.albatross.sysgarantia.security.persistence.repositories.RolesDao;
import br.albatross.sysgarantia.security.persistence.repositories.UsersDao;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UsersResource {

    @Inject
    UsersDao usersDao;

    @Inject
    RolesDao rolesDao;

    @GET
    public Response usuarios() {

        return Response.ok(usersDao.findAll()).build();

    }

    @GET
    @Path("/roles")
    public Response roles() {

        return Response.ok(rolesDao.getRoles()).build();

    }
    
}
