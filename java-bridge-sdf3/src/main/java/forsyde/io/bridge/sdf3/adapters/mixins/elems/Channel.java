
package forsyde.io.bridge.sdf3.adapters.mixins.elems;

import jakarta.xml.bind.annotation.*;

import java.math.BigDecimal;


/**
 * &lt; &gt;Java class for anonymous complex type.
 * 
 * &lt; &gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt; &gt;
 * &lt; &gt;
 *   &lt;   &gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="srcActor" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="srcPort" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="dstActor" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="dstPort" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="initialTokens" type="{http://www.w3.org/2001/XMLSchema}decimal" /&gt;
 *       &lt;attribute name="size" type="{http://www.w3.org/2001/XMLSchema}decimal" /&gt;
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
@XmlType(name = "")
@XmlRootElement(name = "channel")
public class Channel {

    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "srcActor")
    protected String srcActor;
    @XmlAttribute(name = "srcPort")
    protected String srcPort;
    @XmlAttribute(name = "dstActor")
    protected String dstActor;
    @XmlAttribute(name = "dstPort")
    protected String dstPort;
    @XmlAttribute(name = "initialTokens")
    protected BigDecimal initialTokens;
    @XmlAttribute(name = "size")
    protected BigDecimal size;
    @XmlAttribute(name = "nrConnections")
    protected BigDecimal nrConnections;
    @XmlAttribute(name = "inBandwidth")
    protected Double inBandwidth;
    @XmlAttribute(name = "outBandwidth")
    protected Double outBandwidth;

    /**
     * Gets the value of the name property.
	 * @return name
     * 
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
     * Gets the value of the srcActor property.
	 * @return srcActor
     * 
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSrcActor() {
        return srcActor;
    }

    /**
     * Sets the value of the srcActor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSrcActor(String value) {
        this.srcActor = value;
    }

    /**
     * Gets the value of the srcPort property.
	 * @return srcPort
     * 
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSrcPort() {
        return srcPort;
    }

    /**
     * Sets the value of the srcPort property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSrcPort(String value) {
        this.srcPort = value;
    }

    /**
     * Gets the value of the dstActor property.
	 * @return dstActor
     * 
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDstActor() {
        return dstActor;
    }

    /**
     * Sets the value of the dstActor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDstActor(String value) {
        this.dstActor = value;
    }

    /**
     * Gets the value of the dstPort property.
	 * @return dstPort
     * 
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDstPort() {
        return dstPort;
    }

    /**
     * Sets the value of the dstPort property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDstPort(String value) {
        this.dstPort = value;
    }

    /**
     * Gets the value of the initialTokens property.
	 * @return initialTokens
     * 
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getInitialTokens() {
        return initialTokens;
    }

    /**
     * Sets the value of the initialTokens property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setInitialTokens(BigDecimal value) {
        this.initialTokens = value;
    }

    /**
     * Gets the value of the size property.
	 * @return size
     * 
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSize() {
        return size;
    }

    /**
     * Sets the value of the size property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSize(BigDecimal value) {
        this.size = value;
    }

    /**
     * Gets the value of the nrConnections property.
	 * @return nrConnections
     * 
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
	 * @return inBandwidth
     * 
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
	 * @return outBandwidth
     * 
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
