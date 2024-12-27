package br.albatross.sysgarantia.dto.problemas;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RegisterForReflection
public record NovaDescricaoProblemaDto(@NotBlank(message="Descrição Resumida do Problema Obrigatória") String descricaoResumida, @NotBlank(message="Descrição Detalhada do Problema Obrigatória") String descricaoDetalhada, @NotNull(message="Categoria do Texto Pronto Obrigatória") @Positive Short problemaId) {

}
