
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
 *           <element ref="{}tile" maxOccurs="unbounded" minOccurs="0"/>
 *           <element ref="{}router" maxOccurs="unbounded" minOccurs="0"/>
 *           <element ref="{}link" maxOccurs="unbounded"/>
 *         </sequence>
 *         <sequence>
 *           <element ref="{}messages" maxOccurs="unbounded" minOccurs="0"/>
 *         </sequence>
 *       </choice>
 *       <attribute name="slotTableSize" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       <attribute name="packetHeaderSize" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       <attribute name="flitSize" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       <attribute name="reconfigurationTimeNI" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "tile",
    "router",
    "link",
    "messages"
})
@XmlRootElement(name = "network")
public class Network {

    protected List<Tile> tile;
    protected List<Router> router;
    protected List<Link> link;
    protected List<Messages> messages;
    @XmlAttribute(name = "slotTableSize")
    protected BigDecimal slotTableSize;
    @XmlAttribute(name = "packetHeaderSize")
    protected BigDecimal packetHeaderSize;
    @XmlAttribute(name = "flitSize")
    protected BigDecimal flitSize;
    @XmlAttribute(name = "reconfigurationTimeNI")
    protected BigDecimal reconfigurationTimeNI;

    /**
     * Gets the value of the tile property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tile property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTile().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Tile }
     * 
     * 
     */
    public List<Tile> getTile() {
        if (tile == null) {
            tile = new ArrayList<Tile>();
        }
        return this.tile;
    }

    /**
     * Gets the value of the router property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the router property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRouter().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Router }
     * 
     * 
     */
    public List<Router> getRouter() {
        if (router == null) {
            router = new ArrayList<Router>();
        }
        return this.router;
    }

    /**
     * Gets the value of the link property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the link property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLink().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Link }
     * 
     * 
     */
    public List<Link> getLink() {
        if (link == null) {
            link = new ArrayList<Link>();
        }
        return this.link;
    }

    /**
     * Gets the value of the messages property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the messages property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMessages().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Messages }
     * 
     * 
     */
    public List<Messages> getMessages() {
        if (messages == null) {
            messages = new ArrayList<Messages>();
        }
        return this.messages;
    }

    /**
     * Gets the value of the slotTableSize property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSlotTableSize() {
        return slotTableSize;
    }

    /**
     * Sets the value of the slotTableSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSlotTableSize(BigDecimal value) {
        this.slotTableSize = value;
    }

    /**
     * Gets the value of the packetHeaderSize property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPacketHeaderSize() {
        return packetHeaderSize;
    }

    /**
     * Sets the value of the packetHeaderSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPacketHeaderSize(BigDecimal value) {
        this.packetHeaderSize = value;
    }

    /**
     * Gets the value of the flitSize property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFlitSize() {
        return flitSize;
    }

    /**
     * Sets the value of the flitSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFlitSize(BigDecimal value) {
        this.flitSize = value;
    }

    /**
     * Gets the value of the reconfigurationTimeNI property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getReconfigurationTimeNI() {
        return reconfigurationTimeNI;
    }

    /**
     * Sets the value of the reconfigurationTimeNI property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setReconfigurationTimeNI(BigDecimal value) {
        this.reconfigurationTimeNI = value;
    }

}
