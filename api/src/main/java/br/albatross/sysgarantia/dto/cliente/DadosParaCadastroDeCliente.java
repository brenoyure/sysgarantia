package br.albatross.sysgarantia.dto.cliente;

import java.time.LocalTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class DadosParaCadastroDeCliente {

    @NotBlank(message="Nome do Cliente Obrigatório")
    @Size(min = 1, max = 100)
    private String nome;

    @NotBlank(message="Descrição Obrigatória, podendo ser o nome completo do órgão ou empresa, ou sede")
    @Size(min = 1, max = 255)
    private String descricao;

    @NotBlank(message="Número(s) para contato com o Cliente Obrigatório(s)")
    @Size(max = 255)
    private String numerosParaContato;

    @NotBlank(message="E-mail(s) para contato com o Cliente Obrigatório(s)")
    private String emailsParaContato;

    @NotBlank(message="Endereço: Logradouro Obrigatório")
    @Size(max = 100)
    private String logradouro;

    @NotBlank(message="Endereço: Número Obrigatório")
    @Size(max = 55)
    private String numero;

    @NotBlank(message="Endereço: Bairro Obrigatório")
    @Size(max = 55)
    private String bairro;

    @NotBlank(message="Endereço: Estado Obrigatório")
    @Size(max = 55)
    private String estado;

    @NotBlank(message="Endereço: Cidade Obrigatória")
    @Size(max = 55)
    private String cidade;

    @NotBlank(message="Endereço: CEP Obrigatório")
    @Size(max = 13)
    private String cep;

    @NotNull(message="Horário: Início do Expediente Obrigatório")
    private LocalTime horarioInicioDoExpediente;

    @NotNull(message="Horário: Fim do Expediente Obrigatório")
    private LocalTime horarioFimDoExpediente;

    @NotNull(message="Horário: Obrigatório informar se há ou não horário de almoço")
    private Boolean possuiHorarioDeAlmoco;

    private LocalTime inicioDoHorarioDeAlmoco;

    private LocalTime fimDoHorarioDeAlmoco;

    public DadosParaCadastroDeCliente() {

    }

    public DadosParaCadastroDeCliente(String nome, String descricao, String numerosParaContato,
            String emailsParaContato, String logradouro, String numero, String bairro, String estado, String cidade,
            String cep, LocalTime horarioInicioDoExpediente, LocalTime horarioFimDoExpediente,
            Boolean possuiHorarioDeAlmoco, LocalTime inicioDoHorarioDeAlmoco, LocalTime fimDoHorarioDeAlmoco) {
        this.nome = nome;
        this.descricao = descricao;
        this.numerosParaContato = numerosParaContato;
        this.emailsParaContato = emailsParaContato;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.estado = estado;
        this.cidade = cidade;
        this.cep = cep;
        this.horarioInicioDoExpediente = horarioInicioDoExpediente;
        this.horarioFimDoExpediente = horarioFimDoExpediente;
        this.possuiHorarioDeAlmoco = possuiHorarioDeAlmoco;
        this.inicioDoHorarioDeAlmoco = inicioDoHorarioDeAlmoco;
        this.fimDoHorarioDeAlmoco = fimDoHorarioDeAlmoco;
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

    public Boolean getPossuiHorarioDeAlmoco() {
        return possuiHorarioDeAlmoco;
    }

    public void setPossuiHorarioDeAlmoco(Boolean possuiHorarioDeAlmoco) {
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

}
