package br.albatross.sysgarantia.models;

import java.util.Objects;

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
@Table(name = "descricao_problema")
public class DescricaoProblema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Mouse - Problema no Scroll, Monitor - Tela Piscando.
     */
    @Column(name = "descricao_resumida", length = 55, unique = true, nullable = false)
    private String descricaoResumida;

    /**
     * Mouse com problema no Scroll, Monitor com tela piscando...
     */
    @Column(name = "descricao_detalhada", length = 255, unique = true, nullable = false)
    private String descricaoDetalhada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_problema_id")
    private Problema problema;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricaoResumida() {
        return descricaoResumida;
    }

    public void setDescricaoResumida(String descricaoResumida) {
        this.descricaoResumida = descricaoResumida;
    }

    public String getDescricaoDetalhada() {
        return descricaoDetalhada;
    }

    public void setDescricaoDetalhada(String descricaoDetalhada) {
        this.descricaoDetalhada = descricaoDetalhada;
    }

    public Problema getProblema() {
        return problema;
    }

    public void setProblema(Problema problema) {
        this.problema = problema;
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
        DescricaoProblema other = (DescricaoProblema) obj;
        return Objects.equals(id, other.id);
    }

}
