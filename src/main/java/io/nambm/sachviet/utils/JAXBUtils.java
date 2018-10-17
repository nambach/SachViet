package io.nambm.sachviet.utils;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;

public class JAXBUtils {
    public static <T> T unmarshalling(String xmlFilePath, String xsdFilePath, Class<T> clazz) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);

            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            if (xsdFilePath != null) {
                unmarshaller.setSchema(getSchema(xsdFilePath));
            }

            File xmlFile = new File(xmlFilePath);

            return (T) unmarshaller.unmarshal(xmlFile);
        } catch (JAXBException e) {
            e.printStackTrace();
            System.out.println("JAXB error: " + e);
            return null;
        } catch (SAXException e) {
            e.printStackTrace();
            System.out.println("SAX error: " + e);
            return null;
        }
    }

    @SafeVarargs
    public static <G, T> void marshalling(String xmlResult, G sourceObject, T... objects) {
        try {
            Class[] classes = new Class[objects.length + 1];
            classes[0] = sourceObject.getClass();

            for (int i = 0; i < objects.length; i++) {
                classes[i + 1] = objects[i].getClass();
            }

            JAXBContext jaxbContext = JAXBContext.newInstance(classes);
            Marshaller marshaller = jaxbContext.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            marshaller.marshal(sourceObject, new File("src/main/resources/xml/result/" + xmlResult));
        } catch (JAXBException e) {
            System.out.println("JAXB error: " + e);
        }
    }

    @SafeVarargs
    public static <G, T> void marshalling(String xmlResult, G sourceObject, Class<T>... subClasses) {
        try {
            Class[] classes = new Class[subClasses.length + 1];
            classes[0] = sourceObject.getClass();

            for (int i = 0; i < subClasses.length; i++) {
                classes[i + 1] = subClasses[i];
            }

            JAXBContext jaxbContext = JAXBContext.newInstance(classes);
            Marshaller marshaller = jaxbContext.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            marshaller.marshal(sourceObject, new File(xmlResult));
        } catch (JAXBException e) {
            System.out.println("JAXB error: " + e);
        }
    }

    private static Schema getSchema(String xsdFilePath) throws SAXException {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        return factory.newSchema(new File(xsdFilePath));
    }
}
