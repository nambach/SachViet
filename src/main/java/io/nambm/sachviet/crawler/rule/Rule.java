
package io.nambm.sachviet.crawler.rule;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for rule complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rule">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="siteName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="basedUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="topics" type="{http://nambm.io/crawling-rule}topicList"/>
 *         &lt;element name="collectionXpath" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="item" type="{http://nambm.io/crawling-rule}item"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rule", propOrder = {

})
public class Rule {

    @XmlElement(required = true)
    protected String siteName;
    @XmlElement(required = true)
    protected String basedUrl;
    @XmlElement(required = true)
    protected TopicList topics;
    @XmlElement(required = true)
    protected String collectionXpath;
    @XmlElement(required = true)
    protected Item item;

    /**
     * Gets the value of the siteName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSiteName() {
        return siteName;
    }

    /**
     * Sets the value of the siteName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSiteName(String value) {
        this.siteName = value;
    }

    /**
     * Gets the value of the basedUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBasedUrl() {
        return basedUrl;
    }

    /**
     * Sets the value of the basedUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBasedUrl(String value) {
        this.basedUrl = value;
    }

    /**
     * Gets the value of the topics property.
     * 
     * @return
     *     possible object is
     *     {@link TopicList }
     *     
     */
    public TopicList getTopics() {
        return topics;
    }

    /**
     * Sets the value of the topics property.
     * 
     * @param value
     *     allowed object is
     *     {@link TopicList }
     *     
     */
    public void setTopics(TopicList value) {
        this.topics = value;
    }

    /**
     * Gets the value of the collectionXpath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCollectionXpath() {
        return collectionXpath;
    }

    /**
     * Sets the value of the collectionXpath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCollectionXpath(String value) {
        this.collectionXpath = value;
    }

    /**
     * Gets the value of the item property.
     * 
     * @return
     *     possible object is
     *     {@link Item }
     *     
     */
    public Item getItem() {
        return item;
    }

    /**
     * Sets the value of the item property.
     * 
     * @param value
     *     allowed object is
     *     {@link Item }
     *     
     */
    public void setItem(Item value) {
        this.item = value;
    }

}
