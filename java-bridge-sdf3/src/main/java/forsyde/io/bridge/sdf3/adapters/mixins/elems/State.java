
package forsyde.io.bridge.sdf3.adapters.mixins.elems;

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
 *       &lt;attribute name="actor" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="startOfPeriodicRegime" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "state")
public class State {

    @XmlAttribute(name = "actor", required = true)
    protected String actor;
    @XmlAttribute(name = "startOfPeriodicRegime")
    protected Boolean startOfPeriodicRegime;

    /**
     * Gets the value of the actor property.
	 * @return actor
     * 
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActor() {
        return actor;
    }

    /**
     * Sets the value of the actor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActor(String value) {
        this.actor = value;
    }

    /**
     * Gets the value of the startOfPeriodicRegime property.
	 * @return startOfPeriodicRegime
     * 
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isStartOfPeriodicRegime() {
        return startOfPeriodicRegime;
    }

    /**
     * Sets the value of the startOfPeriodicRegime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setStartOfPeriodicRegime(Boolean value) {
        this.startOfPeriodicRegime = value;
    }

}
