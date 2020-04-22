package by.epam.jwd.dao.factory;

import by.epam.jwd.dao.TravelOrderXMLParserDAO;
import by.epam.jwd.dao.impl.TravelOrderDomParser;
import by.epam.jwd.dao.impl.TravelOrderSaxParser;
import by.epam.jwd.dao.impl.TravelOrderStaxParser;

public class XMLParserDAOFactory {

	private static final XMLParserDAOFactory INSTANCE = new XMLParserDAOFactory();

	private static final TravelOrderXMLParserDAO DOM_PARSER_INSTANCE = new TravelOrderDomParser();
	private static final TravelOrderXMLParserDAO SAX_PARSER_INSTANCE = new TravelOrderSaxParser();
	private static final TravelOrderXMLParserDAO STAX_PARSER_INSTANCE = new TravelOrderStaxParser();


	private XMLParserDAOFactory() {
	}

	public static XMLParserDAOFactory getInstance() {
		return INSTANCE;
	}

	public TravelOrderXMLParserDAO getDomParser() {
		return DOM_PARSER_INSTANCE;
	}
	
	public TravelOrderXMLParserDAO getSaxParser() {
		return SAX_PARSER_INSTANCE;
	}
	
	public TravelOrderXMLParserDAO getStaxParser() {
		return STAX_PARSER_INSTANCE;
	}

}