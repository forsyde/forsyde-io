
package forsyde.io.java.adapters.sdf3.elems;

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
 *         <element ref="{}bufferSize" minOccurs="0"/>
 *         <element ref="{}tokenSize" maxOccurs="unbounded" minOccurs="0"/>
 *         <element ref="{}bandwidth" minOccurs="0"/>
 *         <element ref="{}latency" minOccurs="0"/>
 *       </sequence>
 *       <attribute name="channel" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "bufferSize",
    "tokenSize",
    "bandwidth",
    "latency"
})
@XmlRootElement(name = "channelProperties")
public class ChannelProperties {

    protected BufferSize bufferSize;
    protected List<TokenSize> tokenSize;
    protected Bandwidth bandwidth;
    protected Latency latency;
    @XmlAttribute(name = "channel", required = true)
    protected String channel;

    /**
     * Gets the value of the bufferSize property.
     * 
     * @return
     *     possible object is
     *     {@link BufferSize }
     *     
     */
    public BufferSize getBufferSize() {
        return bufferSize;
    }

    /**
     * Sets the value of the bufferSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link BufferSize }
     *     
     */
    public void setBufferSize(BufferSize value) {
        this.bufferSize = value;
    }

    /**
     * Gets the value of the tokenSize property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tokenSize property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTokenSize().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TokenSize }
     * 
     * 
     */
    public List<TokenSize> getTokenSize() {
        if (tokenSize == null) {
            tokenSize = new ArrayList<TokenSize>();
        }
        return this.tokenSize;
    }

    /**
     * Gets the value of the bandwidth property.
     * 
     * @return
     *     possible object is
     *     {@link Bandwidth }
     *     
     */
    public Bandwidth getBandwidth() {
        return bandwidth;
    }

    /**
     * Sets the value of the bandwidth property.
     * 
     * @param value
     *     allowed object is
     *     {@link Bandwidth }
     *     
     */
    public void setBandwidth(Bandwidth value) {
        this.bandwidth = value;
    }

    /**
     * Gets the value of the latency property.
     * 
     * @return
     *     possible object is
     *     {@link Latency }
     *     
     */
    public Latency getLatency() {
        return latency;
    }

    /**
     * Sets the value of the latency property.
     * 
     * @param value
     *     allowed object is
     *     {@link Latency }
     *     
     */
    public void setLatency(Latency value) {
        this.latency = value;
    }

    /**
     * Gets the value of the channel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChannel() {
        return channel;
    }

    /**
     * Sets the value of the channel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChannel(String value) {
        this.channel = value;
    }

}
