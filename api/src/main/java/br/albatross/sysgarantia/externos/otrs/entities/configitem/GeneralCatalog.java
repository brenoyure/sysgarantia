package br.albatross.sysgarantia.externos.otrs.entities.configitem;

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

/**
 * 
 * Representa o catalogo do CMDB de itens de configuração, como Computer,
 * Hardware e etc.
 * 
 * 
 * @author breno.brito
 *
 */
@Entity
@Table(name = "general_catalog")
public class GeneralCatalog {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String generalCatalogClass;

    @Column(length = 100, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "valid_id")
    private Valid valid;

    @Column(length = 200, nullable = true)
    private String comments;

    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    @Column(name = "change_time", nullable = false)
    private LocalDateTime changeTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGeneralCatalogClass() {
        return generalCatalogClass;
    }

    public void setGeneralCatalogClass(String generalCatalogClass) {
        this.generalCatalogClass = generalCatalogClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Valid getValid() {
        return valid;
    }

    public void setValid(Valid valid) {
        this.valid = valid;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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
