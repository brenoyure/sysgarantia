package br.albatross.sysgarantia.dto.cliente;

import java.io.Serializable;

import br.albatross.sysgarantia.models.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
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

}
