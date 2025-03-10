package br.albatross.sysgarantia.services.clientes.validacoes;

import br.albatross.sysgarantia.dto.cliente.DadosAtualizacaoCliente;
import br.albatross.sysgarantia.repositories.ClienteRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.ValidationException;

/**
 * 
 * Valida se existe o cliente, que será atualizado, com o ID informado no DTO.
 * 
 * @author breno.brito
 */
@RequestScoped
public class ValidacaoAtualizacaoClienteIdExiste implements ValidacaoAtualizacaoCliente {

    @Inject
    private ClienteRepository repository;

    @Override
    public void validar(DadosAtualizacaoCliente dadosDoCliente) {

        if (!repository.existsById(dadosDoCliente.getId())) {
            throw new ValidationException("Cliente com o ID informado não encontrado");
        }

    }

}
