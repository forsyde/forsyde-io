
package forsyde.io.java.adapters.sdf3.elems;

import java.math.BigDecimal;
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
 *       &lt;attribute name="nr" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" /&gt;
 *       &lt;attribute name="src" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="dst" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="channel" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="seqNr" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" /&gt;
 *       &lt;attribute name="startTime" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" /&gt;
 *       &lt;attribute name="duration" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" /&gt;
 *       &lt;attribute name="size" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "message")
public class Message {

    @XmlAttribute(name = "nr", required = true)
    protected BigDecimal nr;
    @XmlAttribute(name = "src", required = true)
    protected String src;
    @XmlAttribute(name = "dst", required = true)
    protected String dst;
    @XmlAttribute(name = "channel", required = true)
    protected String channel;
    @XmlAttribute(name = "seqNr", required = true)
    protected BigDecimal seqNr;
    @XmlAttribute(name = "startTime", required = true)
    protected BigDecimal startTime;
    @XmlAttribute(name = "duration", required = true)
    protected BigDecimal duration;
    @XmlAttribute(name = "size", required = true)
    protected BigDecimal size;

    /**
     * Gets the value of the nr property.
	 * @return nr
     * 
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNr() {
        return nr;
    }

    /**
     * Sets the value of the nr property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNr(BigDecimal value) {
        this.nr = value;
    }

    /**
     * Gets the value of the src property.
	 * @return src
     * 
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSrc() {
        return src;
    }

    /**
     * Sets the value of the src property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSrc(String value) {
        this.src = value;
    }

    /**
     * Gets the value of the dst property.
	 * @return dst
     * 
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDst() {
        return dst;
    }

    /**
     * Sets the value of the dst property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDst(String value) {
        this.dst = value;
    }

    /**
     * Gets the value of the channel property.
	 * @return channel
     * 
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChannel() {
        return channel;
    }

    /**
     * Sets the value of the channel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChannel(String value) {
        this.channel = value;
    }

    /**
     * Gets the value of the seqNr property.
	 * @return seqNr
     * 
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSeqNr() {
        return seqNr;
    }

    /**
     * Sets the value of the seqNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSeqNr(BigDecimal value) {
        this.seqNr = value;
    }

    /**
     * Gets the value of the startTime property.
	 * @return startTime
     * 
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getStartTime() {
        return startTime;
    }

    /**
     * Sets the value of the startTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setStartTime(BigDecimal value) {
        this.startTime = value;
    }

    /**
     * Gets the value of the duration property.
	 * @return duration
     * 
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDuration() {
        return duration;
    }

    /**
     * Sets the value of the duration property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDuration(BigDecimal value) {
        this.duration = value;
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

}
