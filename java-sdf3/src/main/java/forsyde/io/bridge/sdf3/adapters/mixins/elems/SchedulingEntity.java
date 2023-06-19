
package forsyde.io.bridge.sdf3.adapters.mixins.elems;

import java.math.BigDecimal;
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
 *       &lt;attribute name="msg" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="startTime" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" /&gt;
 *       &lt;attribute name="duration" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" /&gt;
 *       &lt;attribute name="route" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="slots" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "schedulingEntity")
public class SchedulingEntity {

    @XmlAttribute(name = "msg", required = true)
    protected String msg;
    @XmlAttribute(name = "startTime", required = true)
    protected BigDecimal startTime;
    @XmlAttribute(name = "duration", required = true)
    protected BigDecimal duration;
    @XmlAttribute(name = "route", required = true)
    protected String route;
    @XmlAttribute(name = "slots", required = true)
    protected String slots;

    /**
     * Gets the value of the msg property.
	 * @return msg
     * 
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsg() {
        return msg;
    }

    /**
     * Sets the value of the msg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsg(String value) {
        this.msg = value;
    }

    /**
     * Gets the value of the startTime property.
	 * @return startTime
     * 
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getStartTime() {
        return startTime;
    }

    /**
     * Sets the value of the startTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setStartTime(BigDecimal value) {
        this.startTime = value;
    }

    /**
     * Gets the value of the duration property.
	 * @return duration
     * 
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDuration() {
        return duration;
    }

    /**
     * Sets the value of the duration property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDuration(BigDecimal value) {
        this.duration = value;
    }

    /**
     * Gets the value of the route property.
	 * @return route
     * 
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoute() {
        return route;
    }

    /**
     * Sets the value of the route property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoute(String value) {
        this.route = value;
    }

    /**
     * Gets the value of the slots property.
	 * @return slots
     * 
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSlots() {
        return slots;
    }

    /**
     * Sets the value of the slots property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSlots(String value) {
        this.slots = value;
    }

}
