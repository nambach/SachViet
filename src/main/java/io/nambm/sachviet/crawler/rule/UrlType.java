
package io.nambm.sachviet.crawler.rule;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>Java class for urlType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="urlType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="incrementParam" type="{http://www.w3.org/2001/XMLSchema}string" default="" />
 *       &lt;attribute name="from" type="{http://www.w3.org/2001/XMLSchema}string" default="1" />
 *       &lt;attribute name="to" type="{http://www.w3.org/2001/XMLSchema}string" default="1" />
 *       &lt;attribute name="step" type="{http://www.w3.org/2001/XMLSchema}string" default="1" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "urlType", propOrder = {
    "value"
})
public class UrlType {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "incrementParam")
    protected String incrementParam;
    @XmlAttribute(name = "from")
    protected String from;
    @XmlAttribute(name = "to")
    protected String to;
    @XmlAttribute(name = "step")
    protected String step;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the incrementParam property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncrementParam() {
        if (incrementParam == null) {
            return "";
        } else {
            return incrementParam;
        }
    }

    /**
     * Sets the value of the incrementParam property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncrementParam(String value) {
        this.incrementParam = value;
    }

    /**
     * Gets the value of the from property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFrom() {
        if (from == null) {
            return "1";
        } else {
            return from;
        }
    }

    /**
     * Sets the value of the from property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFrom(String value) {
        this.from = value;
    }

    /**
     * Gets the value of the to property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTo() {
        if (to == null) {
            return "1";
        } else {
            return to;
        }
    }

    /**
     * Sets the value of the to property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTo(String value) {
        this.to = value;
    }

    /**
     * Gets the value of the step property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStep() {
        if (step == null) {
            return "1";
        } else {
            return step;
        }
    }

    /**
     * Sets the value of the step property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStep(String value) {
        this.step = value;
    }

}
