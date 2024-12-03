package br.albatross.sysgarantia.dto.cliente;

import java.io.Serializable;

import br.albatross.sysgarantia.models.Cliente;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor @AllArgsConstructor
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
    
}
