package by.epam.jwd.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.epam.jwd.dao.TravelOrderXMLParserDAO;
import by.epam.jwd.dao.XMLParserDaoException;
import by.epam.jwd.entity.Order;

public class TravelOrderSaxParser extends TravelOrderXMLParserDAO {
	private static final Logger logger = LogManager.getLogger();
	private static final String DEFAULT_PATH_FILE = "resources/orders.xml";
	private XMLReader reader;
	private TravelOrderSaxHandler handler;

	public TravelOrderSaxParser() {
		handler = new TravelOrderSaxHandler();
		try {
			reader = XMLReaderFactory.createXMLReader();
			reader.setContentHandler(handler);
		} catch (SAXException e) {
			logger.error("error initializing SaxParser" + e);
			
		}
	}

	@Override
	public void buildOrders(InputStream source) throws XMLParserDaoException {
		logger.debug("start SAX parser buildOrders method");

		try {
			if (source != null) {
				reader.parse(new InputSource(source));
			}
		    else reader.parse(DEFAULT_PATH_FILE);
		} catch (SAXException e) {
			logger.error("Error of SAX parser" + e);
			throw new XMLParserDaoException(e);
		} catch (IOException e) {
			logger.error("Error of I/O" + e);
			throw new XMLParserDaoException(e);

		}
	}

	@Override
	public Set<Order> getOrders() {
		return handler.getOrders();
	}

}
