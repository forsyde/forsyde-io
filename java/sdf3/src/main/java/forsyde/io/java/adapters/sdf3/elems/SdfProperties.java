
package forsyde.io.java.adapters.sdf3.elems;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
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
 *         <element ref="{}actorProperties" maxOccurs="unbounded"/>
 *         <element ref="{}channelProperties" maxOccurs="unbounded" minOccurs="0"/>
 *         <element ref="{}graphProperties" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "actorProperties",
    "channelProperties",
    "graphProperties"
})
@XmlRootElement(name = "sdfProperties")
public class SdfProperties {

    @XmlElement(required = true)
    protected List<ActorProperties> actorProperties;
    protected List<ChannelProperties> channelProperties;
    protected GraphProperties graphProperties;

    /**
     * Gets the value of the actorProperties property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the actorProperties property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getActorProperties().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ActorProperties }
     * 
     * 
     */
    public List<ActorProperties> getActorProperties() {
        if (actorProperties == null) {
            actorProperties = new ArrayList<ActorProperties>();
        }
        return this.actorProperties;
    }

    /**
     * Gets the value of the channelProperties property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the channelProperties property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChannelProperties().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ChannelProperties }
     * 
     * 
     */
    public List<ChannelProperties> getChannelProperties() {
        if (channelProperties == null) {
            channelProperties = new ArrayList<ChannelProperties>();
        }
        return this.channelProperties;
    }

    /**
     * Gets the value of the graphProperties property.
     * 
     * @return
     *     possible object is
     *     {@link GraphProperties }
     *     
     */
    public GraphProperties getGraphProperties() {
        return graphProperties;
    }

    /**
     * Sets the value of the graphProperties property.
     * 
     * @param value
     *     allowed object is
     *     {@link GraphProperties }
     *     
     */
    public void setGraphProperties(GraphProperties value) {
        this.graphProperties = value;
    }

}
