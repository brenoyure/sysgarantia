package br.albatross.sysgarantia.security.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class DadosParaCadastroDeUsuarioDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotEmpty
    private List<Integer> rolesIds = new ArrayList<>();

    public DadosParaCadastroDeUsuarioDto() {

    }

    public DadosParaCadastroDeUsuarioDto(String username, String password, List<Integer> rolesIds) {
        this.username = username;
        this.password = password;
        this.rolesIds = rolesIds;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Integer> getRolesIds() {
        return rolesIds;
    }

    public void setRolesIds(List<Integer> rolesIds) {
        this.rolesIds = rolesIds;
    }

}