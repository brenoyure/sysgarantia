package br.albatross.sysgarantia.dto.problemas;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RegisterForReflection
public record AtualizacaoDescricaoProblemaDto(
        @NotNull @Positive Integer id, 
        @NotBlank          String descricaoResumida, 
        @NotBlank          String descricaoDetalhada, 
        @NotNull @Positive Short problemaId) {}
