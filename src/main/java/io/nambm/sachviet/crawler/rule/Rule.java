
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
 *       &lt;sequence>
 *         &lt;element name="siteName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="basedUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="incrementParam" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="incrementFrom" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="incrementTo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fragmentJsQuerySelector" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fragmentXpath" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="collectionXpath" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="item" type="{http://nambm.io/crawling-rule}item"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rule", propOrder = {
    "siteName",
    "name",
    "url",
    "basedUrl",
    "incrementParam",
    "incrementFrom",
    "incrementTo",
    "fragmentJsQuerySelector",
    "fragmentXpath",
    "collectionXpath",
    "item"
})
public class Rule {

    @XmlElement(required = true)
    protected String siteName;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String url;
    @XmlElement(required = true)
    protected String basedUrl;
    @XmlElement(required = true)
    protected String incrementParam;
    @XmlElement(required = true)
    protected String incrementFrom;
    @XmlElement(required = true)
    protected String incrementTo;
    @XmlElement(required = true)
    protected String fragmentJsQuerySelector;
    @XmlElement(required = true)
    protected String fragmentXpath;
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
     * Gets the value of the url property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the value of the url property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrl(String value) {
        this.url = value;
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
     * Gets the value of the incrementParam property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncrementParam() {
        return incrementParam;
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
     * Gets the value of the incrementFrom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncrementFrom() {
        return incrementFrom;
    }

    /**
     * Sets the value of the incrementFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncrementFrom(String value) {
        this.incrementFrom = value;
    }

    /**
     * Gets the value of the incrementTo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncrementTo() {
        return incrementTo;
    }

    /**
     * Sets the value of the incrementTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncrementTo(String value) {
        this.incrementTo = value;
    }

    /**
     * Gets the value of the fragmentJsQuerySelector property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFragmentJsQuerySelector() {
        return fragmentJsQuerySelector;
    }

    /**
     * Sets the value of the fragmentJsQuerySelector property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFragmentJsQuerySelector(String value) {
        this.fragmentJsQuerySelector = value;
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
