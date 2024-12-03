package br.albatross.sysgarantia.dto.cliente;

import java.io.Serializable;
import java.time.LocalTime;

import br.albatross.sysgarantia.models.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class HorariosDoCliente implements Serializable {

    private static final long serialVersionUID = 1L;

    private LocalTime horarioInicioDoExpediente;
    private LocalTime horarioFimDoExpediente;

    private boolean possuiHorarioDeAlmoco;

    private LocalTime inicioDoHorarioDeAlmoco;
    private LocalTime fimDoHorarioDeAlmoco;

    public HorariosDoCliente(Cliente cliente) {
        this.horarioInicioDoExpediente = cliente.getHorarioInicioDoExpediente();
        this.horarioFimDoExpediente = cliente.getHorarioFimDoExpediente();
        this.possuiHorarioDeAlmoco = cliente.isPossuiHorarioDeAlmoco();
        this.inicioDoHorarioDeAlmoco = cliente.getInicioDoHorarioDeAlmoco();
        this.fimDoHorarioDeAlmoco = cliente.getFimDoHorarioDeAlmoco();
    }

    public boolean possuiHorarioDeAlmoco() {
        return possuiHorarioDeAlmoco;
    }    

}
