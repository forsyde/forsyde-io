
package forsyde.io.bridge.sdf3.adapters.mixins.elems;

import jakarta.xml.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


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
 *         &lt;         &gt;
 *           &lt;element ref="{}tile" maxOccurs="unbounded" minOccurs="0"/&gt;
 *           &lt;element ref="{}router" maxOccurs="unbounded" minOccurs="0"/&gt;
 *           &lt;element ref="{}link" maxOccurs="unbounded"/&gt;
 *         &lt;/sequence&gt;
 *         &lt;         &gt;
 *           &lt;element ref="{}messages" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;/sequence&gt;
 *       &lt;/choice&gt;
 *       &lt;attribute name="slotTableSize" type="{http://www.w3.org/2001/XMLSchema}decimal" /&gt;
 *       &lt;attribute name="packetHeaderSize" type="{http://www.w3.org/2001/XMLSchema}decimal" /&gt;
 *       &lt;attribute name="flitSize" type="{http://www.w3.org/2001/XMLSchema}decimal" /&gt;
 *       &lt;attribute name="reconfigurationTimeNI" type="{http://www.w3.org/2001/XMLSchema}decimal" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * &lt;/pre&gt;
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
     * &lt; &gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a set&lt;/CODE&gt; method for the tile property.
     * 
     * &lt; &gt;
     * For example, to add a new item, do as follows:
     * &lt; &gt;
     *    getTile().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt; &gt;
     * Objects of the following type(s) are allowed in the list
     * {@link Tile }
* @return Tile
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
     * &lt; &gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a set&lt;/CODE&gt; method for the router property.
     * 
     * &lt; &gt;
     * For example, to add a new item, do as follows:
     * &lt; &gt;
     *    getRouter().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt; &gt;
     * Objects of the following type(s) are allowed in the list
     * {@link Router }
* @return Router
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
     * &lt; &gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a set&lt;/CODE&gt; method for the link property.
     * 
     * &lt; &gt;
     * For example, to add a new item, do as follows:
     * &lt; &gt;
     *    getLink().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt; &gt;
     * Objects of the following type(s) are allowed in the list
     * {@link Link }
* @return Link
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
     * &lt; &gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a set&lt;/CODE&gt; method for the messages property.
     * 
     * &lt; &gt;
     * For example, to add a new item, do as follows:
     * &lt; &gt;
     *    getMessages().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt; &gt;
     * Objects of the following type(s) are allowed in the list
     * {@link Messages }
* @return Messages
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
