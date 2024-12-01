package br.albatross.sysgarantia.security.services;

import java.util.List;
import java.util.Optional;

import br.albatross.sysgarantia.security.models.DadosParaAtualizacaoDeUsuarioDto;
import br.albatross.sysgarantia.security.models.DadosParaCadastroDeUsuarioDto;
import br.albatross.sysgarantia.security.models.DadosParaListagemDoUsuarioDto;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RequestScoped
public class UsuarioService {
	
	@Inject
	private CadastroUsuarioService cadastroService;
	
	@Inject
	private UsuarioListagemService listagemService;

	public DadosParaListagemDoUsuarioDto cadastrarNovoUsuario(@Valid DadosParaCadastroDeUsuarioDto novosDados) {
		return cadastroService
				.cadastrarNovoUsuario(novosDados);

	}

	public DadosParaListagemDoUsuarioDto atualizarCadastro(@Valid DadosParaAtualizacaoDeUsuarioDto dadosAtualizados) {
		return cadastroService
				.atualizarCadastro(dadosAtualizados);
	}

	public List<DadosParaListagemDoUsuarioDto> getUsuarios() {
		return listagemService
				.listar();
	}

	public Optional<DadosParaListagemDoUsuarioDto> carregarPorId(@Positive int id) {
		return listagemService
				.buscarPorId(id);
	}

	public void excluir(int id) {
		cadastroService.excluirPorId(id);
	}

}
