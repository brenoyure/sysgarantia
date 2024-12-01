package br.albatross.sysgarantia.externos.otrs.entities.ticket.state;

import java.time.LocalDateTime;

import br.albatross.sysgarantia.externos.otrs.entities.valid.Valid;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(value = TicketState.class)
public abstract class TicketState_ {

	public static volatile SingularAttribute<TicketState, Byte> id;

	public static volatile SingularAttribute<TicketState, String> name;

	public static volatile SingularAttribute<TicketState, String> comments;

	public static volatile SingularAttribute<TicketState, TicketStateType> ticketStateType;

	public static volatile SingularAttribute<TicketState, LocalDateTime> createTime;

	public static volatile SingularAttribute<TicketState, LocalDateTime> changeTime;

	public static volatile SingularAttribute<TicketState, Valid> valid;

}
