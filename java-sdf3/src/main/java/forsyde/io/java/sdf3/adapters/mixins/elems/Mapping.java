
package forsyde.io.java.sdf3.adapters.mixins.elems;

import java.util.ArrayList;
import java.util.List;
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
 *       &lt;       &gt;
 *         &lt;element ref="{}tile" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{}connection" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{}network" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="appGraph" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="archGraph" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
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
    "connection",
    "network"
})
@XmlRootElement(name = "mapping")
public class Mapping {

    protected List<Tile> tile;
    protected List<Connection> connection;
    protected Network network;
    @XmlAttribute(name = "appGraph", required = true)
    protected String appGraph;
    @XmlAttribute(name = "archGraph", required = true)
    protected String archGraph;

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
     * Gets the value of the connection property.
     * 
     * &lt; &gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a set&lt;/CODE&gt; method for the connection property.
     * 
     * &lt; &gt;
     * For example, to add a new item, do as follows:
     * &lt; &gt;
     *    getConnection().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt; &gt;
     * Objects of the following type(s) are allowed in the list
     * {@link Connection }
* @return Connection
     * 
     * 
     */
    public List<Connection> getConnection() {
        if (connection == null) {
            connection = new ArrayList<Connection>();
        }
        return this.connection;
    }

    /**
     * Gets the value of the network property.
     * 
     * @return
     *     possible object is
     *     {@link Network }
     *     
     */
    public Network getNetwork() {
        return network;
    }

    /**
     * Sets the value of the network property.
     * 
     * @param value
     *     allowed object is
     *     {@link Network }
     *     
     */
    public void setNetwork(Network value) {
        this.network = value;
    }

    /**
     * Gets the value of the appGraph property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAppGraph() {
        return appGraph;
    }

    /**
     * Sets the value of the appGraph property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAppGraph(String value) {
        this.appGraph = value;
    }

    /**
     * Gets the value of the archGraph property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArchGraph() {
        return archGraph;
    }

    /**
     * Sets the value of the archGraph property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArchGraph(String value) {
        this.archGraph = value;
    }

}
