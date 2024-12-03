package br.albatross.sysgarantia.resource;

import br.albatross.sysgarantia.dto.garantia.DadosParaNovaSolicitacaoDeGarantia;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/solicitacaoGarantia")
public class SolicitacaoDeGarantiaResource {

    public Response solicitar(DadosParaNovaSolicitacaoDeGarantia solicitacao) {
        return Response.ok(solicitacao).build();
    }

}
