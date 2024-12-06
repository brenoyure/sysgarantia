package br.albatross.sysgarantia.dto.cliente;

import java.io.Serializable;

import br.albatross.sysgarantia.models.Cliente;

public class DadosDoCliente implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;

    private String nome;

    private String descricao;

    private String numerosParaContato;

    private String emails;

    private DadosDeEndereco dadosDeEndereco;

    private HorariosDoCliente horarios;

    public DadosDoCliente(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.descricao = cliente.getDescricao();
        this.numerosParaContato = cliente.getNumerosParaContato();
        this.emails = cliente.getEmailsParaContato();
        this.dadosDeEndereco = new DadosDeEndereco(cliente);
        this.horarios = new HorariosDoCliente(cliente);
    }

    public DadosDoCliente(int id, String nome, String descricao, String numerosParaContato, String emails,
            DadosDeEndereco dadosDeEndereco, HorariosDoCliente horarios) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.numerosParaContato = numerosParaContato;
        this.emails = emails;
        this.dadosDeEndereco = dadosDeEndereco;
        this.horarios = horarios;
    }

    public DadosDoCliente() {
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

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public DadosDeEndereco getDadosDeEndereco() {
        return dadosDeEndereco;
    }

    public void setDadosDeEndereco(DadosDeEndereco dadosDeEndereco) {
        this.dadosDeEndereco = dadosDeEndereco;
    }

    public HorariosDoCliente getHorarios() {
        return horarios;
    }

    public void setHorarios(HorariosDoCliente horarios) {
        this.horarios = horarios;
    }

}
