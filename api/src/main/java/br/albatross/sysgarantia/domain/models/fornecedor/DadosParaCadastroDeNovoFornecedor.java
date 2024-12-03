package br.albatross.sysgarantia.domain.models.fornecedor;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * Representam os dados básicos necessários para cadastrar um novo fornecedor no Sistema de Garantia.
 * 
 * @author breno.brito
 */
@Getter @Setter
public class DadosParaCadastroDeNovoFornecedor {

    @NotBlank
    private String nome;

    @NotBlank
    private String emails;

    private Set<Integer> idsDosServicosDoFornecedorNoSistemaDeChamados = new HashSet<>();

}
