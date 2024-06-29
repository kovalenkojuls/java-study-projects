package ru.julia.xml.dom;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLReader {
    private static final Logger logger = LoggerFactory.getLogger(XMLReader.class);
    private static final String TICKER = "ticker";

    public static void main(String[] args)
            throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {

        var file = new File(ClassLoader.getSystemResource("shares.xml").getFile());

        var dbFactory = DocumentBuilderFactory.newInstance();
        dbFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);
        dbFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        var dBuilder = dbFactory.newDocumentBuilder();
        var xmlDocument = dBuilder.parse(file);

        xmlDocument.getDocumentElement().normalize();

        List<Share> shareList = new ArrayList<>();
        logger.info("Root element:{}", xmlDocument.getDocumentElement().getNodeName());

        NodeList nList = xmlDocument.getElementsByTagName("share");
        for (var idx = 0; idx < nList.getLength(); idx++) {
            var nNode = nList.item(idx);
            logger.info("Element:{}", nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                var eElement = (Element) nNode;

                var ticker = eElement.getElementsByTagName(TICKER).item(0).getTextContent();
                var last = Double.parseDouble(eElement.getElementsByTagName("last").item(0).getTextContent());
                var date = eElement.getElementsByTagName("date").item(0).getTextContent();

                logger.info("ticker:{}", ticker);
                logger.info("last:{}", last);
                logger.info("date:{} ", date);

                shareList.add(new Share(ticker, last, date));
            }
        }
        logger.info("shareList:{}", shareList);

        logger.info("-------------------XPath-------------------");

        // XPath
        var xPath = XPathFactory.newInstance().newXPath();
        var expression = "/shares/share[@id='1']";
        var nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);

        for (var idx = 0; idx < nodeList.getLength(); idx++) {
            var nNode = nodeList.item(idx);
            logger.info("XPath Element:{}", nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                var eElement = (Element) nNode;

                var ticker = eElement.getElementsByTagName(TICKER).item(0).getTextContent();
                var last = Double.parseDouble(eElement.getElementsByTagName("last").item(0).getTextContent());
                var date = eElement.getElementsByTagName("date").item(0).getTextContent();

                logger.info("ticker:{}", ticker);
                logger.info("last:{}", last);
                logger.info("date:{} ", date);
            }
        }
    }
}