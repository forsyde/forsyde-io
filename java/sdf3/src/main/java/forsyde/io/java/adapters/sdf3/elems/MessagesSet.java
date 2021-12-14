
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
 *         <element ref="{}messages" maxOccurs="unbounded"/>
 *         <element ref="{}switch" maxOccurs="unbounded" minOccurs="0"/>
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
    "messages",
    "_switch"
})
@XmlRootElement(name = "messagesSet")
public class MessagesSet {

    @XmlElement(required = true)
    protected List<Messages> messages;
    @XmlElement(name = "switch")
    protected List<Switch> _switch;

    /**
     * Gets the value of the messages property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the messages property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMessages().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Messages }
     * 
     * 
     */
    public List<Messages> getMessages() {
        if (messages == null) {
            messages = new ArrayList<Messages>();
        }
        return this.messages;
    }

    /**
     * Gets the value of the switch property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the switch property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSwitch().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Switch }
     * 
     * 
     */
    public List<Switch> getSwitch() {
        if (_switch == null) {
            _switch = new ArrayList<Switch>();
        }
        return this._switch;
    }

}
