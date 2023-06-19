
package forsyde.io.bridge.sdf3.adapters.mixins.elems;

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
 *       &lt;attribute name="sz" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" /&gt;
 *       &lt;attribute name="src" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" /&gt;
 *       &lt;attribute name="dst" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" /&gt;
 *       &lt;attribute name="mem" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "bufferSize")
public class BufferSize {

    @XmlAttribute(name = "sz", required = true)
    protected BigDecimal sz;
    @XmlAttribute(name = "src", required = true)
    protected BigDecimal src;
    @XmlAttribute(name = "dst", required = true)
    protected BigDecimal dst;
    @XmlAttribute(name = "mem", required = true)
    protected BigDecimal mem;

    /**
     * Gets the value of the sz property.
	 * @return sz
     * 
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSz() {
        return sz;
    }

    /**
     * Sets the value of the sz property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSz(BigDecimal value) {
        this.sz = value;
    }

    /**
     * Gets the value of the src property.
	 * @return src
     * 
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSrc() {
        return src;
    }

    /**
     * Sets the value of the src property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSrc(BigDecimal value) {
        this.src = value;
    }

    /**
     * Gets the value of the dst property.
	 * @return dst
     * 
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDst() {
        return dst;
    }

    /**
     * Sets the value of the dst property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDst(BigDecimal value) {
        this.dst = value;
    }

    /**
     * Gets the value of the mem property.
	 * @return mem
     * 
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMem() {
        return mem;
    }

    /**
     * Sets the value of the mem property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMem(BigDecimal value) {
        this.mem = value;
    }

}
