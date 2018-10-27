
package io.nambm.sachviet.crawler.rule;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for topicType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="topicType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="topicName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="topicCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="url" type="{http://nambm.io/crawling-rule}urlType"/>
 *         &lt;element name="fragmentXpath" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "topicType", propOrder = {

})
public class TopicType {

    @XmlElement(required = true)
    protected String topicName;
    @XmlElement(required = true)
    protected String topicCode;
    @XmlElement(required = true)
    protected UrlType url;
    @XmlElement(required = true)
    protected String fragmentXpath;

    /**
     * Gets the value of the topicName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTopicName() {
        return topicName;
    }

    /**
     * Sets the value of the topicName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTopicName(String value) {
        this.topicName = value;
    }

    /**
     * Gets the value of the topicCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTopicCode() {
        return topicCode;
    }

    /**
     * Sets the value of the topicCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTopicCode(String value) {
        this.topicCode = value;
    }

    /**
     * Gets the value of the url property.
     * 
     * @return
     *     possible object is
     *     {@link UrlType }
     *     
     */
    public UrlType getUrl() {
        return url;
    }

    /**
     * Sets the value of the url property.
     * 
     * @param value
     *     allowed object is
     *     {@link UrlType }
     *     
     */
    public void setUrl(UrlType value) {
        this.url = value;
    }

    /**
     * Gets the value of the fragmentXpath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFragmentXpath() {
        return fragmentXpath;
    }

    /**
     * Sets the value of the fragmentXpath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFragmentXpath(String value) {
        this.fragmentXpath = value;
    }

}
