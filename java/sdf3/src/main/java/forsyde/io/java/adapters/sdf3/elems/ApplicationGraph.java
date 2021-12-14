
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
 *         <element ref="{}sdf"/>
 *         <element ref="{}sdfProperties" minOccurs="0"/>
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
    "sdf",
    "sdfProperties"
})
@XmlRootElement(name = "applicationGraph")
public class ApplicationGraph {

    @XmlElement(required = true)
    protected Sdf sdf;
    protected SdfProperties sdfProperties;
    @XmlAttribute(name = "name", required = true)
    protected String name;

    /**
     * Gets the value of the sdf property.
     * 
     * @return
     *     possible object is
     *     {@link Sdf }
     *     
     */
    public Sdf getSdf() {
        return sdf;
    }

    /**
     * Sets the value of the sdf property.
     * 
     * @param value
     *     allowed object is
     *     {@link Sdf }
     *     
     */
    public void setSdf(Sdf value) {
        this.sdf = value;
    }

    /**
     * Gets the value of the sdfProperties property.
     * 
     * @return
     *     possible object is
     *     {@link SdfProperties }
     *     
     */
    public SdfProperties getSdfProperties() {
        return sdfProperties;
    }

    /**
     * Sets the value of the sdfProperties property.
     * 
     * @param value
     *     allowed object is
     *     {@link SdfProperties }
     *     
     */
    public void setSdfProperties(SdfProperties value) {
        this.sdfProperties = value;
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
