package br.albatross.sysgarantia.externos.otrs.entities.configitem;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Onde ficam armazenados os dados de um item de configuração como o cadastro de
 * uma máquina.
 * 
 * @author breno.brito
 *
 */
@Entity
@Table(name = "xml_storage")
public class XmlStorage {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    /**
     * Nome do atributo do XML que referencia a tabela/entidade ConfigItemVersion.
     */
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "xml_key")
    private ConfigItemVersion configItemVersion;

    @Column(length = 200, name = "xml_type", nullable = false)
    private String xmlType;

    /**
     * Atributo que representa um campo do XML.
     */
    @Lob
    @Column(length = 250, name = "xml_content_key", nullable = true)
    private String xmlContentKey;

    @Lob
    @Column(length = 200, name = "xml_content_value", nullable = true)
    private String xmlContentValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ConfigItemVersion getConfigItemVersion() {
        return configItemVersion;
    }

    public void setConfigItemVersion(ConfigItemVersion configItemVersion) {
        this.configItemVersion = configItemVersion;
    }

    public String getXmlType() {
        return xmlType;
    }

    public void setXmlType(String xmlType) {
        this.xmlType = xmlType;
    }

    public String getXmlContentKey() {
        return xmlContentKey;
    }

    public void setXmlContentKey(String xmlContentKey) {
        this.xmlContentKey = xmlContentKey;
    }

    public String getXmlContentValue() {
        return xmlContentValue;
    }

    public void setXmlContentValue(String xmlContentValue) {
        this.xmlContentValue = xmlContentValue;
    }

}
