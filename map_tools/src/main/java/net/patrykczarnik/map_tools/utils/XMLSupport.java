package net.patrykczarnik.map_tools.utils;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/**
 * @author Patryk Czarnik <patryk@patrykczarnik.net>
 *
 * A utility class with support for XML.
 */
public final class XMLSupport {
	/** This class is not to be instantiated. */
	private XMLSupport() {
	}

	/** Performs SAX parsing of the given file with the given content handler.
	 * @param aSrcFile the file to be read given as the file path/name
	 * @param aContentHandler SAX content handler to be used
	 * @throws IOException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 */
	public static void doSaxParsing(final String aSrcFile, final ContentHandler aContentHandler)
			throws IOException, SAXException, ParserConfigurationException {
		SAXParserFactory spf = SAXParserFactory.newInstance();
		spf.setNamespaceAware(true);
		SAXParser saxParser = spf.newSAXParser();
		XMLReader xmlReader = saxParser.getXMLReader();
		xmlReader.setContentHandler(aContentHandler);
		xmlReader.parse(new InputSource(aSrcFile));
	}
}
