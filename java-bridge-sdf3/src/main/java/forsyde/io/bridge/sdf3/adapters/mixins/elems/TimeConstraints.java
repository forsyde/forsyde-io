
package forsyde.io.bridge.sdf3.adapters.mixins.elems;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

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
 *       &lt;       &gt;
 *         &lt;element ref="{}throughput" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "throughput"
})
@XmlRootElement(name = "timeConstraints")
public class TimeConstraints {

    protected BigDecimal throughput;

    /**
     * Gets the value of the throughput property.
	 * @return throughput
     * 
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getThroughput() {
        return throughput;
    }

    /**
     * Sets the value of the throughput property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setThroughput(BigDecimal value) {
        this.throughput = value;
    }

}
