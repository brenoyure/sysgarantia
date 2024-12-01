package br.albatross.sysgarantia.domain.models.garantia.apis.chamado;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DadosDoUsuarioCliente implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nomeDoUsuarioCliente;

}
