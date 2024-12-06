package br.albatross.sysgarantia.externos.otrs.entities.ticket.state;

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

@Entity
@Table(name = "ticket_state")
public class TicketState {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Byte id;

    @Column(length = 200, nullable = false, unique = true)
    private String name;

    @Column(length = 250, nullable = true)
    private String comments;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "type_id", nullable = false)
    private TicketStateType ticketStateType;

    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    @Column(name = "change_time", nullable = false)
    private LocalDateTime changeTime;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "valid_id", nullable = false)
    private Valid valid;

    public Byte getId() {
        return id;
    }

    public void setId(Byte id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public TicketStateType getTicketStateType() {
        return ticketStateType;
    }

    public void setTicketStateType(TicketStateType ticketStateType) {
        this.ticketStateType = ticketStateType;
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

    public Valid getValid() {
        return valid;
    }

    public void setValid(Valid valid) {
        this.valid = valid;
    }

}
