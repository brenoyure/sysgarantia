package br.albatross.sysgarantia.externos.dto;

public record Chamado (
        Long id, 
        String numero, 
        String titulo, 
        int servicoId,
        String servico, 
        String usuario) {
}
