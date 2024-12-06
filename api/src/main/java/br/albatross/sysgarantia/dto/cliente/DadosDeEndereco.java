package br.albatross.sysgarantia.dto.cliente;

import java.io.Serializable;

import br.albatross.sysgarantia.models.Cliente;

public class DadosDeEndereco implements Serializable {

    private static final long serialVersionUID = 1L;

    private String logradouro;
    private String numero;
    private String bairro;
    private String estado;
    private String cidade;
    private String cep;

    public DadosDeEndereco(Cliente cliente) {
        this.logradouro = cliente.getLogradouro();
        this.numero = cliente.getNumero();
        this.bairro = cliente.getBairro();
        this.estado = cliente.getEstado();
        this.cidade = cliente.getCidade();
        this.cep = cliente.getCep();
    }

    public DadosDeEndereco(String logradouro, String numero, String bairro, String estado, String cidade, String cep) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.estado = estado;
        this.cidade = cidade;
        this.cep = cep;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public String getBairro() {
        return bairro;
    }

    public String getEstado() {
        return estado;
    }

    public String getCidade() {
        return cidade;
    }

    public String getCep() {
        return cep;
    }
    
}
