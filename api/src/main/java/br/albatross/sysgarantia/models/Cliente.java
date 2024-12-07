package br.albatross.sysgarantia.models;

import java.time.LocalTime;
import java.util.Objects;

import br.albatross.sysgarantia.dto.cliente.DadosParaCadastroDeCliente;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100, unique = true, nullable = false)
    private String nome;

    @Column(length = 255, unique = true, nullable = false)
    private String descricao;

    @Column(name = "numeros_para_contato", length = 255, unique = false, nullable = false)
    private String numerosParaContato;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "emails_para_contato", unique = false, nullable = false)
    private String emailsParaContato;

    @Column(length = 100, unique = false, nullable = false)
    private String logradouro;

    @Column(length = 55, unique = false, nullable = false)
    private String numero;

    @Column(length = 55, unique = false, nullable = false)
    private String bairro;

    @Column(length = 55, unique = false, nullable = false)
    private String estado;

    @Column(length = 55, unique = false, nullable = false)
    private String cidade;

    @Column(length = 13, unique = false, nullable = false)
    private String cep;

    @Column(name = "horario_inicio_do_expediente", nullable = false)
    private LocalTime horarioInicioDoExpediente;

    @Column(name = "horario_fim_do_expediente", nullable = false)
    private LocalTime horarioFimDoExpediente;

    @Column(name = "possui_horario_de_almoco", nullable = false)
    private boolean possuiHorarioDeAlmoco;

    @Column(name = "inicio_do_horario_de_almoco", nullable = true)
    private LocalTime inicioDoHorarioDeAlmoco;

    @Column(name = "fim_do_horario_de_almoco", nullable = true)
    private LocalTime fimDoHorarioDeAlmoco;

    public Cliente() {

    }

    public Cliente(DadosParaCadastroDeCliente novosDados) {

        this.nome = novosDados.getNome();
        this.descricao = novosDados.getDescricao();
        this.numerosParaContato = novosDados.getNumerosParaContato();
        this.emailsParaContato = novosDados.getEmailsParaContato();

        this.logradouro = novosDados.getLogradouro();
        this.numero = novosDados.getNumero();
        this.bairro = novosDados.getBairro();
        this.estado = novosDados.getEstado();
        this.cidade = novosDados.getCidade();
        this.cep = novosDados.getCep();

        this.horarioInicioDoExpediente = novosDados.getHorarioInicioDoExpediente();
        this.horarioFimDoExpediente = novosDados.getHorarioFimDoExpediente();

        this.possuiHorarioDeAlmoco = novosDados.getPossuiHorarioDeAlmoco();

        if (possuiHorarioDeAlmoco) {
            this.inicioDoHorarioDeAlmoco = novosDados.getInicioDoHorarioDeAlmoco();
            this.fimDoHorarioDeAlmoco = novosDados.getFimDoHorarioDeAlmoco();
        }

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNumerosParaContato() {
        return numerosParaContato;
    }

    public void setNumerosParaContato(String numerosParaContato) {
        this.numerosParaContato = numerosParaContato;
    }

    public String getEmailsParaContato() {
        return emailsParaContato;
    }

    public void setEmailsParaContato(String emailsParaContato) {
        this.emailsParaContato = emailsParaContato;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public LocalTime getHorarioInicioDoExpediente() {
        return horarioInicioDoExpediente;
    }

    public void setHorarioInicioDoExpediente(LocalTime horarioInicioDoExpediente) {
        this.horarioInicioDoExpediente = horarioInicioDoExpediente;
    }

    public LocalTime getHorarioFimDoExpediente() {
        return horarioFimDoExpediente;
    }

    public void setHorarioFimDoExpediente(LocalTime horarioFimDoExpediente) {
        this.horarioFimDoExpediente = horarioFimDoExpediente;
    }

    public boolean isPossuiHorarioDeAlmoco() {
        return possuiHorarioDeAlmoco;
    }

    public void setPossuiHorarioDeAlmoco(boolean possuiHorarioDeAlmoco) {
        this.possuiHorarioDeAlmoco = possuiHorarioDeAlmoco;
    }

    public LocalTime getInicioDoHorarioDeAlmoco() {
        return inicioDoHorarioDeAlmoco;
    }

    public void setInicioDoHorarioDeAlmoco(LocalTime inicioDoHorarioDeAlmoco) {
        this.inicioDoHorarioDeAlmoco = inicioDoHorarioDeAlmoco;
    }

    public LocalTime getFimDoHorarioDeAlmoco() {
        return fimDoHorarioDeAlmoco;
    }

    public void setFimDoHorarioDeAlmoco(LocalTime fimDoHorarioDeAlmoco) {
        this.fimDoHorarioDeAlmoco = fimDoHorarioDeAlmoco;
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
        Cliente other = (Cliente) obj;
        return id == other.id;
    }

}
