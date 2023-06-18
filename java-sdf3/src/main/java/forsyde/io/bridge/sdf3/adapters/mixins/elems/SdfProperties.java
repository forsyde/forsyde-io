
package forsyde.io.bridge.sdf3.adapters.mixins.elems;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
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
 *         &lt;element ref="{}actorProperties" maxOccurs="unbounded"/&gt;
 *         &lt;element ref="{}channelProperties" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{}graphProperties" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * &lt;/pre&gt;
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
     * &lt; &gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a set&lt;/CODE&gt; method for the actorProperties property.
     * 
     * &lt; &gt;
     * For example, to add a new item, do as follows:
     * &lt; &gt;
     *    getActorProperties().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt; &gt;
     * Objects of the following type(s) are allowed in the list
     * {@link ActorProperties }
* @return ActorProperties
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
     * &lt; &gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a set&lt;/CODE&gt; method for the channelProperties property.
     * 
     * &lt; &gt;
     * For example, to add a new item, do as follows:
     * &lt; &gt;
     *    getChannelProperties().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt; &gt;
     * Objects of the following type(s) are allowed in the list
     * {@link ChannelProperties }
* @return ChannelProperties
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
