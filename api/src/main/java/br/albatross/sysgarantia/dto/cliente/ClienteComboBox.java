package br.albatross.sysgarantia.dto.cliente;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public record ClienteComboBox(
        int id, 
        String nome, 
        String descricao) 
    {
}
