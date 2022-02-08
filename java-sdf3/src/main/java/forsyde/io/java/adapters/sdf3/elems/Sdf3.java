
package forsyde.io.java.adapters.sdf3.elems;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * &lt; &gt;Java class for anonymous complex type.
 * 
 * &lt; &gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt; &gt;
 * &lt; &gt;
 *   &lt;   &gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;       &gt;
 *         &lt;element ref="{}applicationGraph" minOccurs="0"/&gt;
 *         &lt;element ref="{}architectureGraph" minOccurs="0"/&gt;
 *         &lt;element ref="{}mapping" minOccurs="0"/&gt;
 *         &lt;element ref="{}systemUsage" minOccurs="0"/&gt;
 *         &lt;element ref="{}storageThroughputTradeOffs" minOccurs="0"/&gt;
 *         &lt;element ref="{}messagesSet" minOccurs="0"/&gt;
 *         &lt;element ref="{}settings" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="type" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "applicationGraph",
    "architectureGraph",
    "mapping",
    "systemUsage",
    "storageThroughputTradeOffs",
    "messagesSet",
    "settings"
})
@XmlRootElement(name = "sdf3")
public class Sdf3 {

    protected ApplicationGraph applicationGraph;
    protected ArchitectureGraph architectureGraph;
    protected Mapping mapping;
    protected SystemUsage systemUsage;
    protected StorageThroughputTradeOffs storageThroughputTradeOffs;
    protected MessagesSet messagesSet;
    protected List<Settings> settings;
    @XmlAttribute(name = "type", required = true)
    protected String type;
    @XmlAttribute(name = "version", required = true)
    protected String version;

    /**
     * Gets the value of the applicationGraph property.
     * 
     * @return
     *     possible object is
     *     {@link ApplicationGraph }
     *     
     */
    public ApplicationGraph getApplicationGraph() {
        return applicationGraph;
    }

    /**
     * Sets the value of the applicationGraph property.
     * 
     * @param value
     *     allowed object is
     *     {@link ApplicationGraph }
     *     
     */
    public void setApplicationGraph(ApplicationGraph value) {
        this.applicationGraph = value;
    }

    /**
     * Gets the value of the architectureGraph property.
     * 
     * @return
     *     possible object is
     *     {@link ArchitectureGraph }
     *     
     */
    public ArchitectureGraph getArchitectureGraph() {
        return architectureGraph;
    }

    /**
     * Sets the value of the architectureGraph property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArchitectureGraph }
     *     
     */
    public void setArchitectureGraph(ArchitectureGraph value) {
        this.architectureGraph = value;
    }

    /**
     * Gets the value of the mapping property.
     * 
     * @return
     *     possible object is
     *     {@link Mapping }
     *     
     */
    public Mapping getMapping() {
        return mapping;
    }

    /**
     * Sets the value of the mapping property.
     * 
     * @param value
     *     allowed object is
     *     {@link Mapping }
     *     
     */
    public void setMapping(Mapping value) {
        this.mapping = value;
    }

    /**
     * Gets the value of the systemUsage property.
     * 
     * @return
     *     possible object is
     *     {@link SystemUsage }
     *     
     */
    public SystemUsage getSystemUsage() {
        return systemUsage;
    }

    /**
     * Sets the value of the systemUsage property.
     * 
     * @param value
     *     allowed object is
     *     {@link SystemUsage }
     *     
     */
    public void setSystemUsage(SystemUsage value) {
        this.systemUsage = value;
    }

    /**
     * Gets the value of the storageThroughputTradeOffs property.
     * 
     * @return
     *     possible object is
     *     {@link StorageThroughputTradeOffs }
     *     
     */
    public StorageThroughputTradeOffs getStorageThroughputTradeOffs() {
        return storageThroughputTradeOffs;
    }

    /**
     * Sets the value of the storageThroughputTradeOffs property.
     * 
     * @param value
     *     allowed object is
     *     {@link StorageThroughputTradeOffs }
     *     
     */
    public void setStorageThroughputTradeOffs(StorageThroughputTradeOffs value) {
        this.storageThroughputTradeOffs = value;
    }

    /**
     * Gets the value of the messagesSet property.
     * 
     * @return
     *     possible object is
     *     {@link MessagesSet }
     *     
     */
    public MessagesSet getMessagesSet() {
        return messagesSet;
    }

    /**
     * Sets the value of the messagesSet property.
     * 
     * @param value
     *     allowed object is
     *     {@link MessagesSet }
     *     
     */
    public void setMessagesSet(MessagesSet value) {
        this.messagesSet = value;
    }

    /**
     * Gets the value of the settings property.
     * 
     * &lt; &gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a set&lt;/CODE&gt; method for the settings property.
     * 
     * &lt; &gt;
     * For example, to add a new item, do as follows:
     * &lt; &gt;
     *    getSettings().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt; &gt;
     * Objects of the following type(s) are allowed in the list
     * {@link Settings }
* @return Settings
     * 
     * 
     */
    public List<Settings> getSettings() {
        if (settings == null) {
            settings = new ArrayList<Settings>();
        }
        return this.settings;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

}
