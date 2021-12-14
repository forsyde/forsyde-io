
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
 *       <sequence>
 *         <element ref="{}channel" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *       <attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="srcTile" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="dstTile" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="delay" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * </pre>
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
     * Gets the value of the srcTile property.
     * 
     * @return
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
     * 
     * @return
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
     * 
     * @return
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
