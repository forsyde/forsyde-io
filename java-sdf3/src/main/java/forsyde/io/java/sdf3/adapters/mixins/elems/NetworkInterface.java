
package forsyde.io.java.sdf3.adapters.mixins.elems;

import java.math.BigDecimal;
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
 *         &lt;element ref="{}channel" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="nrConnections" type="{http://www.w3.org/2001/XMLSchema}decimal" /&gt;
 *       &lt;attribute name="inBandwidth" type="{http://www.w3.org/2001/XMLSchema}double" /&gt;
 *       &lt;attribute name="outBandwidth" type="{http://www.w3.org/2001/XMLSchema}double" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "channel"
})
@XmlRootElement(name = "networkInterface")
public class NetworkInterface {

    protected List<Channel> channel;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "nrConnections")
    protected BigDecimal nrConnections;
    @XmlAttribute(name = "inBandwidth")
    protected Double inBandwidth;
    @XmlAttribute(name = "outBandwidth")
    protected Double outBandwidth;

    /**
     * Gets the value of the channel property.
     * 
     * &lt; &gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a set&lt;/CODE&gt; method for the channel property.
     * 
     * &lt; &gt;
     * For example, to add a new item, do as follows:
     * &lt; &gt;
     *    getChannel().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt; &gt;
     * Objects of the following type(s) are allowed in the list
     * {@link Channel }
* @return Channel
     * 
     * 
     */
    public List<Channel> getChannel() {
        if (channel == null) {
            channel = new ArrayList<Channel>();
        }
        return this.channel;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the nrConnections property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNrConnections() {
        return nrConnections;
    }

    /**
     * Sets the value of the nrConnections property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNrConnections(BigDecimal value) {
        this.nrConnections = value;
    }

    /**
     * Gets the value of the inBandwidth property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getInBandwidth() {
        return inBandwidth;
    }

    /**
     * Sets the value of the inBandwidth property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setInBandwidth(Double value) {
        this.inBandwidth = value;
    }

    /**
     * Gets the value of the outBandwidth property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getOutBandwidth() {
        return outBandwidth;
    }

    /**
     * Sets the value of the outBandwidth property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setOutBandwidth(Double value) {
        this.outBandwidth = value;
    }

}
