
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
 *       <choice>
 *         <sequence>
 *           <element ref="{}executionTime"/>
 *           <element ref="{}memory" minOccurs="0"/>
 *         </sequence>
 *         <sequence>
 *           <element ref="{}arbitration"/>
 *         </sequence>
 *         <sequence>
 *           <element ref="{}actor" maxOccurs="unbounded" minOccurs="0"/>
 *           <element ref="{}schedule" minOccurs="0"/>
 *         </sequence>
 *       </choice>
 *       <attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="default" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="timeslice" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       <attribute name="timeSlice" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * </pre>
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
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the actor property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getActor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Actor }
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
