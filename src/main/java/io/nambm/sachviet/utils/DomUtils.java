/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.nambm.sachviet.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

/**
 *
 * @author NAMBACH
 */
public class DomUtils {

    public static Document parseFileToDom(String filePath) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            return builder.parse(filePath);
        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
            System.out.println("ParserConfiguration Error: " + e);
        } catch (IOException e) {
//            e.printStackTrace();
            System.out.println("IO Error: " + e);
        } catch (SAXException e) {
//            e.printStackTrace();
            System.out.println("SAX Error: " + e);
        }

        return null;
    }

    public static Document parseStringIntoDOM(String src) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            InputSource inputSource = new InputSource();
            inputSource.setCharacterStream(new StringReader(src));

            return builder.parse(inputSource);
        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
            System.out.println("ParserConfiguration Error: " + e);
        } catch (IOException e) {
//            e.printStackTrace();
            System.out.println("IO Error: " + e);
        } catch (SAXException e) {
//            e.printStackTrace();
            System.out.println("SAX Error: " + e);
        }

        return null;
    }

    public static XPath getXPath() {
        XPathFactory factory = XPathFactory.newInstance();
        XPath xPath = factory.newXPath();
        return xPath;
    }

    public static void transferDOMToFile(Node node, String resultPath) throws TransformerException {
        Source src = new DOMSource(node);

        File file = new File(resultPath);
        Result result = new StreamResult(file);

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();

        transformer.transform(src, result);
    }

    public static <T> T evaluateNode(Node node, String xPathExpression, Class<T> clazz) {
        try {
            if (clazz.equals(String.class)) {
                String value = (String) getXPath().evaluate(xPathExpression, node, XPathConstants.STRING);
                return (T) value.trim();
            } else if (clazz.equals(NodeList.class)) {
                return (T) getXPath().evaluate(xPathExpression, node, XPathConstants.NODESET);
            } else if (clazz.equals(Node.class)) {
                return (T) getXPath().evaluate(xPathExpression, node, XPathConstants.NODE);
            }
            return null;
        } catch (XPathExpressionException e) {
            return null;
        }
    }
    
    public static Element createTextElement(Document document, String name, String value, Map<String, String> attributes, Node parent) {
        Element element = document.createElement(name);
        
        if (value != null) {
            element.setTextContent(value);
        }
        
        if (attributes != null) {
            attributes.forEach(element::setAttribute);
        }
        
        if (parent != null) {
            parent.appendChild(element);
        }
        
        return element;
    }
}
