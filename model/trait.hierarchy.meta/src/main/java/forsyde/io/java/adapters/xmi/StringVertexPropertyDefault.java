//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.12.09 at 03:43:10 PM CET 
//


package forsyde.io.java.adapters.xmi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt; &gt;Java class for StringVertexPropertyDefault complex type.
 * 
 * &lt; &gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt; &gt;
 * &lt;complexType name="StringVertexPropertyDefault"&gt;
 *   &lt;   &gt;
 *     &lt;extension base="{forsyde.io.trait.hierarchy}VertexPropertyDefault"&gt;
 *       &lt;attribute name="string" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StringVertexPropertyDefault")
public class StringVertexPropertyDefault
    extends VertexPropertyDefault
{

    @XmlAttribute(name = "string")
    protected String string;

    /**
     * Gets the value of the string property.
	 * @return string
     * 
     *     possible object is
     *     {@link String }
     *     
     */
    public String getString() {
        return string;
    }

    /**
     * Sets the value of the string property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setString(String value) {
        this.string = value;
    }

}