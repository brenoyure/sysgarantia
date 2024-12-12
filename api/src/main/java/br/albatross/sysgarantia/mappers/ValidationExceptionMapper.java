package br.albatross.sysgarantia.mappers;

import jakarta.json.Json;

import jakarta.validation.ValidationException;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public Response toResponse(ValidationException exception) {
        return Response
                .status(Status.BAD_REQUEST)
                .entity(Json.createObjectBuilder().add("error", exception.getMessage()).build())
                .build();
    }

}
