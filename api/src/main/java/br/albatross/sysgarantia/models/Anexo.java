package br.albatross.sysgarantia.models;

import java.sql.Blob;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "email_anexos")
public class Anexo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    @JsonbTransient
    @JsonIgnore
    private Blob arquivo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_email_id", nullable = false)
    @JsonbTransient
    @JsonIgnore
    private Email email;

    public Anexo() {

    }

    public Anexo(String nome, Blob arquivo, Email email) {
        this.nome = nome;
        this.arquivo = arquivo;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Blob getArquivo() {
        return arquivo;
    }

    public void setArquivo(Blob arquivo) {
        this.arquivo = arquivo;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

}
