package br.albatross.sysgarantia.externos.otrs.entities.ticket;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

import java.time.LocalDateTime;

import br.albatross.sysgarantia.externos.otrs.entities.queue.Queue;
import br.albatross.sysgarantia.externos.otrs.entities.service.Service;
import br.albatross.sysgarantia.externos.otrs.entities.ticket.state.TicketState;
import br.albatross.sysgarantia.externos.otrs.entities.ticket.type.TicketType;
import br.albatross.sysgarantia.externos.otrs.entities.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    /**
     * Representa o nº do ticket, geralmente no padrão de<br/>
     * começando com a da data ao contrário, por exemplo 2023060182000486.
     */
    @Column(name = "tn", length = 50, unique = true, nullable = false)
    private String ticketNumber;

    /**
     * Representa o título/assunto do Ticket.
     */
    @Column(length = 255)
    private String title;

    /**
     * Representa a Fila do Ticket/Chamado, como: Nível 1, Nível 2, Lixo e etc.
     */
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "queue_id", nullable = false)
    private Queue queue;

    /**
     * Representa o tipo do Ticket: como Incidente, Solicitação de Serviço, Problema
     * e etc.
     */
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "type_id")
    private TicketType type;

    /**
     * Representa o tipo do Serviço (do ticket) como: Estação de Trabalho, <br/>
     * Instalação de Softwares, Impressoras e Scanners, Garantia de Fabricante e
     * etc.
     */
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "service_id", nullable = true)
    private Service service;

    /**
     * <p>
     * Representa o "usuário" que <strong>criou</strong> o ticket (chamado).
     * </p>
     * Por exemplo no caso do chamado ter chegado por e-mail, o user_id será sempre
     * 1, o <em>root</em> do OTRS, ou seja o próprio OTRS).
     */
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * <p>
     * Representa o usuário que está como responsável pelo ticket/chamado.
     * </p>
     * <p>
     * No caso de chamados abertos via e-mail ou que na abertura manual,<br/>
     * na opção de <em>Novo Chamado via Fone</em>, por exemplo, em que o responsável
     * não é especificado, o responsável atribuído será o de id 1, ou seja o próprio
     * <em>root</em> do OTRS.
     * </p>
     */
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "responsible_user_id", nullable = false)
    private User responsibleUser;

    /**
     * <p>
     * Representa o estado atual, de maneira detalhada, do ticket.
     * </p>
     * <p>
     * Por exemplo: <em>novo, aberto, fechado com/sem êxito, aguardando
     * usuário/fornecedor.</em>
     * </p>
     */
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "ticket_state_id", nullable = false)
    private TicketState ticketState;

    /**
     * Representa o login, geralmente no padrão de <em>nome.sobrenome</em>, o
     * usuário do ticket/chamado.
     */
    @Column(name = "customer_user_id", length = 250)
    private String customerUserId;

    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    @Column(name = "change_time", nullable = false)
    private LocalDateTime changeTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Queue getQueue() {
        return queue;
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) {
        this.type = type;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getResponsibleUser() {
        return responsibleUser;
    }

    public void setResponsibleUser(User responsibleUser) {
        this.responsibleUser = responsibleUser;
    }

    public TicketState getTicketState() {
        return ticketState;
    }

    public void setTicketState(TicketState ticketState) {
        this.ticketState = ticketState;
    }

    public String getCustomerUserId() {
        return customerUserId;
    }

    public void setCustomerUserId(String customerUserId) {
        this.customerUserId = customerUserId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(LocalDateTime changeTime) {
        this.changeTime = changeTime;
    }

}
