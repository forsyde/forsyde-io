
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
 *           &lt;element ref="{}executionTime"/&gt;
 *           &lt;element ref="{}memory" minOccurs="0"/&gt;
 *         &lt;/sequence&gt;
 *         &lt;         &gt;
 *           &lt;element ref="{}arbitration"/&gt;
 *         &lt;/sequence&gt;
 *         &lt;         &gt;
 *           &lt;element ref="{}actor" maxOccurs="unbounded" minOccurs="0"/&gt;
 *           &lt;element ref="{}schedule" minOccurs="0"/&gt;
 *         &lt;/sequence&gt;
 *       &lt;/choice&gt;
 *       &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="default" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="timeslice" type="{http://www.w3.org/2001/XMLSchema}decimal" /&gt;
 *       &lt;attribute name="timeSlice" type="{http://www.w3.org/2001/XMLSchema}decimal" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "executionTime",
    "memory",
    "arbitration",
    "actor",
    "schedule"
})
@XmlRootElement(name = "processor")
public class Processor {

    protected ExecutionTime executionTime;
    protected Memory memory;
    protected Arbitration arbitration;
    protected List<Actor> actor;
    protected Schedule schedule;
    @XmlAttribute(name = "type")
    protected String type;
    @XmlAttribute(name = "default")
    protected String _default;
    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "timeslice")
    protected BigDecimal timeslice;
    @XmlAttribute(name = "timeSlice")
    protected BigDecimal timeSlice;

    /**
     * Gets the value of the executionTime property.
     * 
     * @return
     *     possible object is
     *     {@link ExecutionTime }
     *     
     */
    public ExecutionTime getExecutionTime() {
        return executionTime;
    }

    /**
     * Sets the value of the executionTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExecutionTime }
     *     
     */
    public void setExecutionTime(ExecutionTime value) {
        this.executionTime = value;
    }

    /**
     * Gets the value of the memory property.
     * 
     * @return
     *     possible object is
     *     {@link Memory }
     *     
     */
    public Memory getMemory() {
        return memory;
    }

    /**
     * Sets the value of the memory property.
     * 
     * @param value
     *     allowed object is
     *     {@link Memory }
     *     
     */
    public void setMemory(Memory value) {
        this.memory = value;
    }

    /**
     * Gets the value of the arbitration property.
     * 
     * @return
     *     possible object is
     *     {@link Arbitration }
     *     
     */
    public Arbitration getArbitration() {
        return arbitration;
    }

    /**
     * Sets the value of the arbitration property.
     * 
     * @param value
     *     allowed object is
     *     {@link Arbitration }
     *     
     */
    public void setArbitration(Arbitration value) {
        this.arbitration = value;
    }

    /**
     * Gets the value of the actor property.
     * 
     * &lt; &gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a set&lt;/CODE&gt; method for the actor property.
     * 
     * &lt; &gt;
     * For example, to add a new item, do as follows:
     * &lt; &gt;
     *    getActor().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt; &gt;
     * Objects of the following type(s) are allowed in the list
     * {@link Actor }
* @return Actor
     * 
     * 
     */
    public List<Actor> getActor() {
        if (actor == null) {
            actor = new ArrayList<Actor>();
        }
        return this.actor;
    }

    /**
     * Gets the value of the schedule property.
     * 
     * @return
     *     possible object is
     *     {@link Schedule }
     *     
     */
    public Schedule getSchedule() {
        return schedule;
    }

    /**
     * Sets the value of the schedule property.
     * 
     * @param value
     *     allowed object is
     *     {@link Schedule }
     *     
     */
    public void setSchedule(Schedule value) {
        this.schedule = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the default property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefault() {
        return _default;
    }

    /**
     * Sets the value of the default property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefault(String value) {
        this._default = value;
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
     * Gets the value of the timeslice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTimeslice() {
        return timeslice;
    }

    /**
     * Sets the value of the timeslice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTimeslice(BigDecimal value) {
        this.timeslice = value;
    }

    /**
     * Gets the value of the timeSlice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTimeSlice() {
        return timeSlice;
    }

    /**
     * Sets the value of the timeSlice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTimeSlice(BigDecimal value) {
        this.timeSlice = value;
    }

}
