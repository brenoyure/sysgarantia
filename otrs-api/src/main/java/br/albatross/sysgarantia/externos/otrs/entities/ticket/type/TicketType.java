package br.albatross.sysgarantia.externos.otrs.entities.ticket.type;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

import java.time.LocalDateTime;

import br.albatross.sysgarantia.externos.otrs.entities.valid.Valid;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa o tipo do Ticket/Serviço como Incidente, Solicitação de Serviço, Problema e etc.
 * 
 * @author breno.brito
 *
 */
@Entity @Table(name = "ticket_type")
@EqualsAndHashCode(of = "id")
@Getter @Setter
public class TicketType {

	@Id @GeneratedValue(strategy = IDENTITY)
	private Integer id;

	@Column(length = 200, nullable = false, unique = true)
	private String name;

	@Column(name = "create_time", nullable = false)
	private LocalDateTime createTime;

	@Column(name = "change_time", nullable = false)
	private LocalDateTime changeTime;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "valid_id", nullable = false)
	private Valid valid;

}
