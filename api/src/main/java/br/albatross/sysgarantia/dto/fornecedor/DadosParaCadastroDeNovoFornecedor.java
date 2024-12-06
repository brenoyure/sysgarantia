package br.albatross.sysgarantia.dto.fornecedor;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

/**
 * 
 * Representam os dados básicos necessários para cadastrar um novo fornecedor no Sistema de Garantia.
 * 
 * @author breno.brito
 */
public class DadosParaCadastroDeNovoFornecedor {

    @NotBlank
    private String nome;

    @NotBlank
    private String emails;

    @NotEmpty
    private Set<Integer> idsDosServicosDoFornecedorNoSistemaDeChamados = new HashSet<>();

    public DadosParaCadastroDeNovoFornecedor() {

    }

    public DadosParaCadastroDeNovoFornecedor(String nome, String emails, Set<Integer> idsDosServicosDoFornecedorNoSistemaDeChamados) {
        this.nome = nome;
        this.emails = emails;
        this.idsDosServicosDoFornecedorNoSistemaDeChamados = idsDosServicosDoFornecedorNoSistemaDeChamados;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public Set<Integer> getIdsDosServicosDoFornecedorNoSistemaDeChamados() {
        return idsDosServicosDoFornecedorNoSistemaDeChamados;
    }

    public void setIdsDosServicosDoFornecedorNoSistemaDeChamados(
            Set<Integer> idsDosServicosDoFornecedorNoSistemaDeChamados) {
        this.idsDosServicosDoFornecedorNoSistemaDeChamados = idsDosServicosDoFornecedorNoSistemaDeChamados;
    }

}
