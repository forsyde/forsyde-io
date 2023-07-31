
package forsyde.io.bridge.sdf3.adapters.mixins.elems;

import java.math.BigDecimal;
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
 *         &lt;element ref="{}channel" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="srcTile" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="dstTile" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="delay" type="{http://www.w3.org/2001/XMLSchema}decimal" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "channel"
})
@XmlRootElement(name = "connection")
public class Connection {

    protected List<Channel> channel;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "srcTile")
    protected String srcTile;
    @XmlAttribute(name = "dstTile")
    protected String dstTile;
    @XmlAttribute(name = "delay")
    protected BigDecimal delay;

    /**
     * Gets the value of the channel property.
	 * @return channel
     * 
     * &lt; &gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a set&lt;/CODE&gt; method for the channel property.
     * 
     * &lt; &gt;
     * For example, to add a new item, do as follows:
     * &lt; &gt;
     *    getChannel().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt; &gt;
     * Objects of the following type(s) are allowed in the list
     * {@link Channel }
	 * @return Channel
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

    /**
     * Gets the value of the srcTile property.
	 * @return srcTile
     * 
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSrcTile() {
        return srcTile;
    }

    /**
     * Sets the value of the srcTile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSrcTile(String value) {
        this.srcTile = value;
    }

    /**
     * Gets the value of the dstTile property.
	 * @return dstTile
     * 
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDstTile() {
        return dstTile;
    }

    /**
     * Sets the value of the dstTile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDstTile(String value) {
        this.dstTile = value;
    }

    /**
     * Gets the value of the delay property.
	 * @return delay
     * 
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDelay() {
        return delay;
    }

    /**
     * Sets the value of the delay property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDelay(BigDecimal value) {
        this.delay = value;
    }

}
