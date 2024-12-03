package br.albatross.sysgarantia.services.clientes.validacoes;

import br.albatross.sysgarantia.dto.cliente.DadosAtualizacaoCliente;
import br.albatross.sysgarantia.dto.cliente.DadosParaCadastroDeCliente;
import jakarta.enterprise.context.RequestScoped;
import jakarta.validation.ValidationException;

/**
 * 
 * <p>Validação referente aos horários de almoço.</p>
 * 
 * <p>Caso for informado que o Cliente possui horário de almoço, e algum desses horários ou ambos não 
 * forem informados, a validação falhará.</p>
 * 
 * @author breno.brito
 * 
 */
@RequestScoped
public class ValidacaoHorariosDeAlmocoDoCliente implements ValidacaoCadastroNovoCliente, ValidacaoAtualizacaoCliente {

    private void validarHorarios(DadosParaCadastroDeCliente dadosCliente) {
        if (dadosCliente.getPossuiHorarioDeAlmoco() && (dadosCliente.getInicioDoHorarioDeAlmoco() == null || dadosCliente.getFimDoHorarioDeAlmoco() == null)) {
            throw new ValidationException("Foi informado que o cliente " + dadosCliente.getNome() + " possui horário de almoço, porém o(s) horário(s) de início ou fim não foram informados");
        }
    }

    @Override
    public void validar(DadosParaCadastroDeCliente dadosDoNovoCliente) {
        validarHorarios(dadosDoNovoCliente);
    }

    @Override
    public void validar(DadosAtualizacaoCliente dadosDoCliente) {
        validarHorarios(dadosDoCliente);
    }

}
