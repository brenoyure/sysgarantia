package br.albatross.sysgarantia.dto.garantia;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DadosParaNovaSolicitacaoDeGarantia {

    @Positive
    @JsonbProperty("cliente_id")
    private int clienteId;

    @Positive
    @JsonbProperty("fornecedor_id")
    private int fornecedorId;

    @Positive
    @JsonbProperty("descricao_problema_id")
    private int descricaoProblemaId;

    @Positive
    @JsonbProperty("chamado_id")
    private int chamadoId;

    @NotBlank
    @JsonbProperty("numero_de_serie")
    private String numeroDeSerie;

}
