package br.albatross.sysgarantia.dto.fornecedor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import br.albatross.sysgarantia.models.Fornecedor;

public class DadosDoFornecedor implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String nome;
    private String emails;
    private Set<Integer> idsDosServicosDoFornecedorNoSistemaDeChamados = new HashSet<>();

    public DadosDoFornecedor(Fornecedor fornecedor) {
        this.id = fornecedor.getId();
        this.nome = fornecedor.getNome();
        this.emails = fornecedor.getEmails();
        idsDosServicosDoFornecedorNoSistemaDeChamados
                .addAll(fornecedor.getIdsDosServicosDoFornecedorNoSistemaDeChamados());
    }

    public DadosDoFornecedor(int id, String nome, String emails,
            Set<Integer> idsDosServicosDoFornecedorNoSistemaDeChamados) {
        this.id = id;
        this.nome = nome;
        this.emails = emails;
        this.idsDosServicosDoFornecedorNoSistemaDeChamados = idsDosServicosDoFornecedorNoSistemaDeChamados;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DadosDoFornecedor other = (DadosDoFornecedor) obj;
        return id == other.id;
    }

}
