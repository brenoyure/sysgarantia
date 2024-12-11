package br.albatross.sysgarantia.resource;

import java.net.URI;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.albatross.sysgarantia.dto.garantia.DadosParaNovaSolicitacaoDeGarantia;
import br.albatross.sysgarantia.models.SolicitacaoGarantia;
import br.albatross.sysgarantia.services.garantia.SolicitacaoGarantiaService;

import jakarta.inject.Inject;

import jakarta.validation.Valid;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("/solicitacaoGarantia")
public class SolicitacaoDeGarantiaResource {

    @Inject
    SolicitacaoGarantiaService solicitacaoGarantiaService;

    @Inject
    UriInfo uriInfo;    

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response solicitar(@Valid @MultipartForm DadosParaNovaSolicitacaoDeGarantia dadosSolicitacao) {
        SolicitacaoGarantia solicitacaoGarantia = 
                solicitacaoGarantiaService.solicitarGarantia(dadosSolicitacao);
        URI novaSolicitacaoURI =
                uriInfo.getRequestUriBuilder().path("/{id}").build(solicitacaoGarantia.getId());
        return Response.created(novaSolicitacaoURI).entity(solicitacaoGarantia).build();
    }

}