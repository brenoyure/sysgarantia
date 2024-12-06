package br.albatross.sysgarantia.externos.dto;

public record Chamado (
        Long id, 
        String numero, 
        String titulo, 
        Servico servico, 
        String usuario) {
}
