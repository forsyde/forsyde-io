
package forsyde.io.java.adapters.sdf3.elems;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element ref="{}timeConstraints" minOccurs="0"/&gt;
 *         &lt;element ref="{}maxplusSchedules" minOccurs="0"/&gt;
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
    "timeConstraints",
    "maxplusSchedules"
})
@XmlRootElement(name = "graphProperties")
public class GraphProperties {

    protected TimeConstraints timeConstraints;
    protected MaxplusSchedules maxplusSchedules;

    /**
     * Gets the value of the timeConstraints property.
	 * @return timeConstraints
     * 
     *     possible object is
     *     {@link TimeConstraints }
     *     
     */
    public TimeConstraints getTimeConstraints() {
        return timeConstraints;
    }

    /**
     * Sets the value of the timeConstraints property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeConstraints }
     *     
     */
    public void setTimeConstraints(TimeConstraints value) {
        this.timeConstraints = value;
    }

    /**
     * Gets the value of the maxplusSchedules property.
	 * @return maxplusSchedules
     * 
     *     possible object is
     *     {@link MaxplusSchedules }
     *     
     */
    public MaxplusSchedules getMaxplusSchedules() {
        return maxplusSchedules;
    }

    /**
     * Sets the value of the maxplusSchedules property.
     * 
     * @param value
     *     allowed object is
     *     {@link MaxplusSchedules }
     *     
     */
    public void setMaxplusSchedules(MaxplusSchedules value) {
        this.maxplusSchedules = value;
    }

}
