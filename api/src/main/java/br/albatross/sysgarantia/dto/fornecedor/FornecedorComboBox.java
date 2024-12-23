package br.albatross.sysgarantia.dto.fornecedor;

import java.util.Objects;

import io.quarkus.runtime.annotations.RegisterForReflection;

/**
 * 
 * DTO que representa os dados do fornecedor, suficientes para serem exibidos em
 * uma combobox
 * 
 * @author breno.brito
 */
@RegisterForReflection
public class FornecedorComboBox {

    private final int id;
    private final String nome;
    private final String emails;

    public FornecedorComboBox(int id, String nome, String emails) {
        this.id = id;
        this.nome = nome;
        this.emails = emails;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmails() {
        return emails;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FornecedorComboBox other = (FornecedorComboBox) obj;
        return id == other.id && Objects.equals(nome, other.nome);
    }

}
