package br.albatross.sysgarantia.models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "fornecedores")
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false, unique = true)
    private String emails;

    @ElementCollection(targetClass = Integer.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "servicos_do_fornecedor", joinColumns = @JoinColumn(name = "fk_fornecedor_id"))
    @Column(name = "id_do_servico_no_sistema_de_chamados", nullable = false)
    private Set<Integer> idsDosServicosDoFornecedorNoSistemaDeChamados = new HashSet<>();

    public Fornecedor() {
    }

    public Fornecedor(String nome, String emails, Set<Integer> idsDosServicosDoFornecedorNoSistemaDeChamados) {
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
        Fornecedor other = (Fornecedor) obj;
        return id == other.id;
    }

}
