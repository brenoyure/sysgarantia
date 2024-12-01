package br.albatross.sysgarantia.externos.otrs.entities.ticket.type;

import java.time.LocalDateTime;

import br.albatross.sysgarantia.externos.otrs.entities.valid.Valid;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

/**
 * Representa o tipo do Ticket/Serviço como Incidente, Solicitação de Serviço, Problema e etc.
 * 
 * @author breno.brito
 *
 */
@StaticMetamodel(value = TicketType.class)
public class TicketType_ {

	public static volatile SingularAttribute<TicketType, Integer> id;

	public static volatile SingularAttribute<TicketType, String> name;

	public static volatile SingularAttribute<TicketType, LocalDateTime> createTime;

	public static volatile SingularAttribute<TicketType, LocalDateTime> changeTime;

	public static volatile SingularAttribute<TicketType, Valid> valid;

}
