package br.albatross.sysgarantia.models;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "problema")
public class Problema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    /**
     * Mouse, Teclado, Gabinete, Monitor
     */
    @Column(length = 20, unique = true, nullable = false)
    private String tipo;

    public Problema() {

    }

    public Problema(String tipo) {
        this.tipo = tipo;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
        Problema other = (Problema) obj;
        return Objects.equals(id, other.id);
    }

}
