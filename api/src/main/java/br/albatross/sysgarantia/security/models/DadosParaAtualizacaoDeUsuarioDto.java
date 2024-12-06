package br.albatross.sysgarantia.security.models;

import static java.util.stream.Collectors.toList;

import br.albatross.sysgarantia.security.persistence.entities.Role;
import br.albatross.sysgarantia.security.persistence.entities.User;
import jakarta.validation.constraints.Positive;

public class DadosParaAtualizacaoDeUsuarioDto extends DadosParaCadastroDeUsuarioDto {

	@Positive
	private int id;

	public DadosParaAtualizacaoDeUsuarioDto(User userEntity) {
		super(userEntity.getUsername(), null, userEntity.getRoles().stream().map(Role::getId).collect(toList()));
		this.id = userEntity.getId();
	}

	public DadosParaAtualizacaoDeUsuarioDto(DadosParaListagemDoUsuarioDto dtoListagem) {
		super(dtoListagem.getUsername(), null, dtoListagem.getRoles().stream().map(RoleDto::getId).collect(toList()));
		this.id = dtoListagem.getId();
	}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
	
}
