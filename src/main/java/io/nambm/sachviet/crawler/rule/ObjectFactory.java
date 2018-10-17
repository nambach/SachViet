
package io.nambm.sachviet.crawler.rule;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the io.nambm.sachviet.crawler.rule package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Rules_QNAME = new QName("http://nambm.io/crawling-rule", "rules");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: io.nambm.sachviet.crawler.rule
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Rules }
     * 
     */
    public Rules createRules() {
        return new Rules();
    }

    /**
     * Create an instance of {@link Item }
     * 
     */
    public Item createItem() {
        return new Item();
    }

    /**
     * Create an instance of {@link Rule }
     * 
     */
    public Rule createRule() {
        return new Rule();
    }

    /**
     * Create an instance of {@link ItemDetail }
     * 
     */
    public ItemDetail createItemDetail() {
        return new ItemDetail();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Rules }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nambm.io/crawling-rule", name = "rules")
    public JAXBElement<Rules> createRules(Rules value) {
        return new JAXBElement<Rules>(_Rules_QNAME, Rules.class, null, value);
    }

}
