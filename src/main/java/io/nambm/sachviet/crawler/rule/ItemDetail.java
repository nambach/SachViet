
package io.nambm.sachviet.crawler.rule;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>Java class for itemDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="itemDetail">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="detailName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="prefix" type="{http://www.w3.org/2001/XMLSchema}string" default="" />
 *       &lt;attribute name="postfix" type="{http://www.w3.org/2001/XMLSchema}string" default="" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "itemDetail", propOrder = {
    "value"
})
public class ItemDetail {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "detailName")
    protected String detailName;
    @XmlAttribute(name = "prefix")
    protected String prefix;
    @XmlAttribute(name = "postfix")
    protected String postfix;

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
     * Gets the value of the detailName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDetailName() {
        return detailName;
    }

    /**
     * Sets the value of the detailName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDetailName(String value) {
        this.detailName = value;
    }

    /**
     * Gets the value of the prefix property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrefix() {
        if (prefix == null) {
            return "";
        } else {
            return prefix;
        }
    }

    /**
     * Sets the value of the prefix property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrefix(String value) {
        this.prefix = value;
    }

    /**
     * Gets the value of the postfix property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostfix() {
        if (postfix == null) {
            return "";
        } else {
            return postfix;
        }
    }

    /**
     * Sets the value of the postfix property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostfix(String value) {
        this.postfix = value;
    }

}
