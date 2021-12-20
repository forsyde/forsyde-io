
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
 *       &lt;attribute name="from" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="to" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="overlap" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "switch")
public class Switch {

    @XmlAttribute(name = "from", required = true)
    protected String from;
    @XmlAttribute(name = "to", required = true)
    protected String to;
    @XmlAttribute(name = "overlap", required = true)
    protected BigDecimal overlap;

    /**
     * Gets the value of the from property.
	 * @return from
     * 
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFrom() {
        return from;
    }

    /**
     * Sets the value of the from property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFrom(String value) {
        this.from = value;
    }

    /**
     * Gets the value of the to property.
	 * @return to
     * 
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTo() {
        return to;
    }

    /**
     * Sets the value of the to property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTo(String value) {
        this.to = value;
    }

    /**
     * Gets the value of the overlap property.
	 * @return overlap
     * 
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOverlap() {
        return overlap;
    }

    /**
     * Sets the value of the overlap property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOverlap(BigDecimal value) {
        this.overlap = value;
    }

}
