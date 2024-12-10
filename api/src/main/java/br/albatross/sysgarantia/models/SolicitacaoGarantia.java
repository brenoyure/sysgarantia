package br.albatross.sysgarantia.models;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "solicitacao_garantia")
public class SolicitacaoGarantia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_de_serie", nullable = false)
    private String numeroDeSerie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_fornecedor_id", nullable = false)
    private Fornecedor fornecedor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_descricao_problema_id", nullable = false)
    private DescricaoProblema descricaoProblema;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status { CRIADA, AGENDADA, ENVIADA }

    public SolicitacaoGarantia() {

    }

    public SolicitacaoGarantia(String numeroDeSerie, Cliente cliente, Fornecedor fornecedor, DescricaoProblema descricaoProblema, Status status) {
        this.numeroDeSerie = numeroDeSerie;
        this.cliente = cliente;
        this.fornecedor = fornecedor;
        this.descricaoProblema = descricaoProblema;
        this.status = status;
    }

    public void marcarAgendada() {
        this.status = Status.AGENDADA;
    }

    public void marcarEnviada() {
        this.status = Status.ENVIADA;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroDeSerie() {
        return numeroDeSerie;
    }

    public void setNumeroDeSerie(String numeroDeSerie) {
        this.numeroDeSerie = numeroDeSerie;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public DescricaoProblema getDescricaoProblema() {
        return descricaoProblema;
    }

    public void setDescricaoProblema(DescricaoProblema descricaoProblema) {
        this.descricaoProblema = descricaoProblema;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
        SolicitacaoGarantia other = (SolicitacaoGarantia) obj;
        return Objects.equals(id, other.id);
    }

}
