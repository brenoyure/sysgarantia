package br.albatross.sysgarantia.models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "email")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String remetente;

    @Column(nullable = false)
    private String destinatario;

    @Column(name = "copia_para", nullable = false)
    private String copiaPara;

    @Column(name = "copia_oculta", nullable = false)
    private String copiaOculta;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "email")
    private Set<Anexo> anexos = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_solicitacao_garantia_id", nullable = false)
    private SolicitacaoGarantia solicitacaoGarantia;

    public Email() {

    }

    public Email(String remetente, String destinatario, String copiaPara, String copiaOculta, SolicitacaoGarantia solicitacaoGarantia) {
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.copiaPara = copiaPara;
        this.copiaOculta = copiaOculta;
        this.solicitacaoGarantia = solicitacaoGarantia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getCopiaPara() {
        return copiaPara;
    }

    public void setCopiaPara(String copiaPara) {
        this.copiaPara = copiaPara;
    }

    public String getCopiaOculta() {
        return copiaOculta;
    }

    public void setCopiaOculta(String copiaOculta) {
        this.copiaOculta = copiaOculta;
    }

    public SolicitacaoGarantia getSolicitacaoGarantia() {
        return solicitacaoGarantia;
    }

    public void setSolicitacaoGarantia(SolicitacaoGarantia solicitacaoGarantia) {
        this.solicitacaoGarantia = solicitacaoGarantia;
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
        Email other = (Email) obj;
        return Objects.equals(id, other.id);
    }

}
