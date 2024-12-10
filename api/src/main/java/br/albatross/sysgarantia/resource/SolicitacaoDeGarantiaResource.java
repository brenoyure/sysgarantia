package br.albatross.sysgarantia.resource;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.albatross.sysgarantia.dto.garantia.DadosParaNovaSolicitacaoDeGarantia;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/solicitacaoGarantia")
public class SolicitacaoDeGarantiaResource {

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response solicitar(@Valid @MultipartForm DadosParaNovaSolicitacaoDeGarantia solicitacao) {
        System.out.println(solicitacao.getNumeroDeSerie());
        System.out.println(solicitacao.getClienteId());
        System.out.println(solicitacao.getFornecedorId());
        System.out.println(solicitacao.getDescricaoProblemaId());
        return Response.ok(solicitacao).build();
    }

}
