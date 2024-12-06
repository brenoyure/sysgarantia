package br.albatross.sysgarantia.externos.otrs.entities.configitem;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "configitem")
public class ConfigItem {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "configitem_number", length = 100, unique = true, nullable = false)
    private String configItemNumber;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "last_version_id")
    private ConfigItemVersion lastVersion;

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

    public String getConfigItemNumber() {
        return configItemNumber;
    }

    public void setConfigItemNumber(String configItemNumber) {
        this.configItemNumber = configItemNumber;
    }

    public ConfigItemVersion getLastVersion() {
        return lastVersion;
    }

    public void setLastVersion(ConfigItemVersion lastVersion) {
        this.lastVersion = lastVersion;
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
