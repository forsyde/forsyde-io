
package forsyde.io.java.adapters.sdf3.elems;

import java.math.BigDecimal;
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
 *         &lt;element ref="{}mpschedule_steadystate" minOccurs="0"/&gt;
 *         &lt;element ref="{}mpschedule_initial" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{}mpperiod" minOccurs="0"/&gt;
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
    "mpscheduleSteadystate",
    "mpscheduleInitial",
    "mpperiod"
})
@XmlRootElement(name = "maxplusSchedules")
public class MaxplusSchedules {

    @XmlElement(name = "mpschedule_steadystate")
    protected MpscheduleSteadystate mpscheduleSteadystate;
    @XmlElement(name = "mpschedule_initial")
    protected List<MpscheduleInitial> mpscheduleInitial;
    protected BigDecimal mpperiod;

    /**
     * Gets the value of the mpscheduleSteadystate property.
     * 
     * @return
     *     possible object is
     *     {@link MpscheduleSteadystate }
     *     
     */
    public MpscheduleSteadystate getMpscheduleSteadystate() {
        return mpscheduleSteadystate;
    }

    /**
     * Sets the value of the mpscheduleSteadystate property.
     * 
     * @param value
     *     allowed object is
     *     {@link MpscheduleSteadystate }
     *     
     */
    public void setMpscheduleSteadystate(MpscheduleSteadystate value) {
        this.mpscheduleSteadystate = value;
    }

    /**
     * Gets the value of the mpscheduleInitial property.
     * 
     * &lt; &gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a set&lt;/CODE&gt; method for the mpscheduleInitial property.
     * 
     * &lt; &gt;
     * For example, to add a new item, do as follows:
     * &lt; &gt;
     *    getMpscheduleInitial().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt; &gt;
     * Objects of the following type(s) are allowed in the list
     * {@link MpscheduleInitial }
* @return MpscheduleInitial
     * 
     * 
     */
    public List<MpscheduleInitial> getMpscheduleInitial() {
        if (mpscheduleInitial == null) {
            mpscheduleInitial = new ArrayList<MpscheduleInitial>();
        }
        return this.mpscheduleInitial;
    }

    /**
     * Gets the value of the mpperiod property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMpperiod() {
        return mpperiod;
    }

    /**
     * Sets the value of the mpperiod property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMpperiod(BigDecimal value) {
        this.mpperiod = value;
    }

}
