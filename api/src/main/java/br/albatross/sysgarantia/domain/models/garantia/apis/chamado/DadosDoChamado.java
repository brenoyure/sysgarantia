package br.albatross.sysgarantia.domain.models.garantia.apis.chamado;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor @AllArgsConstructor
public class DadosDoChamado implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String numeroDoChamado;

	private String titulo;

	private Integer idDoServico;

	private String nomeDoServico;

	private String nomeDoUsuario;

//	public DadosDoServico getDadosDoServico() {
//		return new DadosDoServico() {
//
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			public String getNomeDoServico() {
//				return nomeDoServico;
//			}
//
//			@Override
//			public Integer getIdDoServico() {
//				return idDoServico;
//			}
//		};
//	}
//
//	public DadosDoUsuarioCliente getDadosDoUsuarioCliente() {
//		DadosDoUsuarioCliente usuarioCliente = new DadosDoUsuarioCliente();
//		usuarioCliente.setNomeDoUsuarioCliente(nomeDoUsuario);
//        return usuarioCliente;
//	}

}
