package br.albatross.sysgarantia.models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
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

    @Column(nullable = false)
    private String assunto;

    @Lob
    @Column(nullable = false)
    private String corpo;

    @Column(name = "copia_para")
    private String copiaPara;

    @Column(name = "copia_oculta")
    private String copiaOculta;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "email", cascade = CascadeType.ALL)
    private Set<Anexo> anexos = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_solicitacao_garantia_id", nullable = false)
    private SolicitacaoGarantia solicitacaoGarantia;

    public Email() {

    }

    public Email(String remetente, String destinatario, String assunto, String corpo, String copiaPara, String copiaOculta, SolicitacaoGarantia solicitacaoGarantia) {
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.assunto = assunto;
        this.corpo = corpo;
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

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
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

    public Set<Anexo> getAnexos() {
        return anexos;
    }

    public void setAnexos(Set<Anexo> anexos) {
        this.anexos = anexos;
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
