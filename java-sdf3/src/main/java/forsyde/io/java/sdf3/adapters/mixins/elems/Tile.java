
package forsyde.io.java.sdf3.adapters.mixins.elems;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
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
 *         &lt;element ref="{}processor"/&gt;
 *         &lt;element ref="{}memory"/&gt;
 *         &lt;element ref="{}networkInterface"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "processor",
    "memory",
    "networkInterface"
})
@XmlRootElement(name = "tile")
public class Tile {

    @XmlElement(required = true)
    protected Processor processor;
    @XmlElement(required = true)
    protected Memory memory;
    @XmlElement(required = true)
    protected NetworkInterface networkInterface;
    @XmlAttribute(name = "name", required = true)
    protected String name;

    /**
     * Gets the value of the processor property.
	 * @return processor
     * 
     *     possible object is
     *     {@link Processor }
     *     
     */
    public Processor getProcessor() {
        return processor;
    }

    /**
     * Sets the value of the processor property.
     * 
     * @param value
     *     allowed object is
     *     {@link Processor }
     *     
     */
    public void setProcessor(Processor value) {
        this.processor = value;
    }

    /**
     * Gets the value of the memory property.
	 * @return memory
     * 
     *     possible object is
     *     {@link Memory }
     *     
     */
    public Memory getMemory() {
        return memory;
    }

    /**
     * Sets the value of the memory property.
     * 
     * @param value
     *     allowed object is
     *     {@link Memory }
     *     
     */
    public void setMemory(Memory value) {
        this.memory = value;
    }

    /**
     * Gets the value of the networkInterface property.
	 * @return networkInterface
     * 
     *     possible object is
     *     {@link NetworkInterface }
     *     
     */
    public NetworkInterface getNetworkInterface() {
        return networkInterface;
    }

    /**
     * Sets the value of the networkInterface property.
     * 
     * @param value
     *     allowed object is
     *     {@link NetworkInterface }
     *     
     */
    public void setNetworkInterface(NetworkInterface value) {
        this.networkInterface = value;
    }

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

}
