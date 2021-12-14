
package forsyde.io.java.adapters.sdf3.elems;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
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
 *       <choice>
 *         <sequence>
 *           <element ref="{}stateSize"/>
 *         </sequence>
 *         <sequence>
 *           <element ref="{}actor" maxOccurs="unbounded" minOccurs="0"/>
 *           <element ref="{}channel" maxOccurs="unbounded" minOccurs="0"/>
 *         </sequence>
 *       </choice>
 *       <attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="size" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "stateSize",
    "actor",
    "channel"
})
@XmlRootElement(name = "memory")
public class Memory {

    protected StateSize stateSize;
    protected List<Actor> actor;
    protected List<Channel> channel;
    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "size")
    protected BigDecimal size;

    /**
     * Gets the value of the stateSize property.
     * 
     * @return
     *     possible object is
     *     {@link StateSize }
     *     
     */
    public StateSize getStateSize() {
        return stateSize;
    }

    /**
     * Sets the value of the stateSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link StateSize }
     *     
     */
    public void setStateSize(StateSize value) {
        this.stateSize = value;
    }

    /**
     * Gets the value of the actor property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the actor property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getActor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Actor }
     * 
     * 
     */
    public List<Actor> getActor() {
        if (actor == null) {
            actor = new ArrayList<Actor>();
        }
        return this.actor;
    }

    /**
     * Gets the value of the channel property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the channel property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChannel().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Channel }
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
     * Gets the value of the size property.
     * 
     * @return
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
