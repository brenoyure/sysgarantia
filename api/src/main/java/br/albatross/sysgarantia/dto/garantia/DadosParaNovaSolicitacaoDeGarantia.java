package br.albatross.sysgarantia.dto.garantia;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record DadosParaNovaSolicitacaoDeGarantia (

        @Positive(message = "Id do Cliente é obrigatório e deve ser um número inteiro positivo")
        @JsonbProperty("cliente_id")
        int clienteId,

        @Positive(message = "Id do Fornecedor é obrigatório e deve ser um número inteiro positivo")
        @JsonbProperty("fornecedor_id")
        int fornecedorId,

        @Positive(message = "Id da Descrição do Problema é obrigatório e deve ser um número inteiro positivo")
        @JsonbProperty("descricao_problema_id")
        int descricaoProblemaId,

        @Positive(message = "Id do Chamado não é obrigatório, mas deve ser um número inteiro positivo")
        @JsonbProperty("chamado_id")
        Integer chamadoId,

        @NotBlank(message = "Número de Série do Equipamento é Obrigatório")
        @JsonbProperty("numero_de_serie")
        String numeroDeSerie) 
    {
}
