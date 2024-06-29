package ru.julia.xml.sax;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

public class XMLReader {
    private static final Logger logger = LoggerFactory.getLogger(XMLReader.class);

    public static void main(String[] args)
            throws ParserConfigurationException, SAXException, IOException {

        var file = new File(ClassLoader.getSystemResource("shares.xml").getFile());
        List<Share> shareList = new XMLReader().parse(file);
        logger.info("shareList:{}", shareList);
    }

    private List<Share> parse(File file) throws ParserConfigurationException, SAXException, IOException {
        var factory = SAXParserFactory.newInstance();
        factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
        factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        var saxParser = factory.newSAXParser();

        var handler = new XMLHandler();
        saxParser.parse(file, handler);
        return handler.getResult();
    }
}