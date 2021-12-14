
package forsyde.io.java.adapters.sdf3.elems;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * <complexType>
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element ref="{}processor"/>
 *         <element ref="{}memory"/>
 *         <element ref="{}networkInterface"/>
 *       </sequence>
 *       <attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * </pre>
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
     * 
     * @return
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
     * 
     * @return
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
     * 
     * @return
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

}
