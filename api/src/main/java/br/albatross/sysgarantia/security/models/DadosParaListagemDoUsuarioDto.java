package br.albatross.sysgarantia.security.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

import br.albatross.sysgarantia.security.persistence.entities.User;
public final class DadosParaListagemDoUsuarioDto {

    private final int id;
    private final String username;
    private final Collection<RoleDto> roles;

    public DadosParaListagemDoUsuarioDto(User userEntity) {

        this.id = userEntity.getId();
        this.username = userEntity.getUsername();

        this.roles = new HashSet<>(userEntity.getRoles().size());

        userEntity.getRoles().stream().map(RoleDto::new).forEach(roles::add);

    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Collection<RoleDto> getRoles() {
        return roles;
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
        DadosParaListagemDoUsuarioDto other = (DadosParaListagemDoUsuarioDto) obj;
        return id == other.id;
    }

}
