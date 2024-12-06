package br.albatross.sysgarantia.security.models;

import java.util.Objects;

import br.albatross.sysgarantia.security.persistence.entities.Role;

public class RoleDto {

    private final int id;
    private final String name;

    public RoleDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public RoleDto(Role roleEntity) {
        this.id = roleEntity.getId();
        this.name = roleEntity.getName();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
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
        RoleDto other = (RoleDto) obj;
        return id == other.id;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
