package br.albatross.sysgarantia.externos.otrs.entities.ticket.state;

import java.time.LocalDateTime;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

/**
 * Representa os tipos de estados dos Tickets/Serviços como 'Novo', 'Aberto', 'Fechado' e etc.
 * 
 * @author breno.brito
 *
 */
@StaticMetamodel(value = TicketStateType.class)
public abstract class TicketStateType_ {

	public static volatile SingularAttribute<TicketStateType, Integer> id;

	public static volatile SingularAttribute<TicketStateType, String> name;

	public static volatile SingularAttribute<TicketStateType, String> comments;

	public static volatile SingularAttribute<TicketStateType, LocalDateTime> createTime;

	public static volatile SingularAttribute<TicketStateType, LocalDateTime> changeTime;

}
