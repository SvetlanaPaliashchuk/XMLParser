package by.epam.jwd.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jwd.dao.TravelOrderXMLParserDAO;
import by.epam.jwd.dao.XMLParserDaoException;
import by.epam.jwd.entity.Order;
import by.epam.jwd.entity.Tourist;
import by.epam.jwd.entity.type.OrderTagName;
import by.epam.jwd.entity.type.TourType;
import by.epam.jwd.entity.type.TransportType;
import by.epam.jwd.entity.type.Meal;

import by.epam.jwd.entity.type.Country;

public class TravelOrderStaxParser extends TravelOrderXMLParserDAO {
	private static final Logger logger = LogManager.getLogger();
	private static final int ATTR_VALUE_0 = 0;
	private static final int ATTR_VALUE_1 = 1;
	private static final int ATTR_VALUE_2 = 2;

	private XMLInputFactory inputFactory;
	private Tourist tourist;
	List<Tourist> tourists;

	public TravelOrderStaxParser() {
		inputFactory = XMLInputFactory.newInstance();
		orders = new HashSet<Order>();
	}

	@Override
	public void buildOrders(InputStream source) throws XMLParserDaoException{
		logger.debug("start STAX buildOrders");

		XMLStreamReader reader;
		String name;
		try {
			reader = inputFactory.createXMLStreamReader(source);
			while (reader.hasNext()) {
				int type = reader.next();
				if (type == XMLStreamConstants.START_ELEMENT) {
					name = reader.getLocalName();
					if (OrderTagName.valueOf(name.toUpperCase()) == OrderTagName.ORDER) {
						Order order = buildOrder(reader);
						orders.add(order);
					}
				}
			}
		} catch (XMLStreamException | ParseException e) {
			logger.error("StAX parsing error! " + e.getMessage());
			throw new XMLParserDaoException(e);

		} finally {
			try {
				if (source != null) {
					source.close();
				}
			} catch (IOException e) {
				logger.error("Impossible to close file " + source + " : " + e);
				throw new XMLParserDaoException(e);

			}
		}
	}

	private Order buildOrder(XMLStreamReader reader) throws XMLStreamException, ParseException {
		Order order = new Order();
		Order.OrderBuilder builder = new Order.OrderBuilder();
		builder.setTourType(TourType.valueOf(reader.getAttributeValue(ATTR_VALUE_0).toUpperCase().trim()));
		builder.setCountry(Country.valueOf(reader.getAttributeValue(ATTR_VALUE_1).toUpperCase().trim()));
		builder.setId(reader.getAttributeValue(ATTR_VALUE_2));
		String name;

		while (reader.hasNext()) {
			int type = reader.next();
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				name = reader.getLocalName();
				switch (OrderTagName.valueOf(name.toUpperCase().trim())) {
				case CITY:
					builder.setCity(getXMLText(reader));
					break;
				case STARTDATE:
					builder.setDate(getXMLText(reader));
					break;
				case DURATION:
					builder.setDuration(Integer.parseInt(getXMLText(reader)));
					break;
				case NUMBEROFTOURISTS:
					builder.setNumberOfTourists(Integer.parseInt(getXMLText(reader)));
					break;
				case TOURISTS:
					tourists = new ArrayList<Tourist>();
					break;
				case TOURIST:
					tourist = new Tourist();
					break;
				case SURNAME:
					tourist.setSurname(getXMLText(reader));
					break;
				case NAME:
					tourist.setName(getXMLText(reader));
					break;
				case PASSPORT:
					tourist.setPassport(getXMLText(reader));
					break;
				case TRANSPORT:
					builder.setTransport(TransportType.valueOf(getXMLText(reader).toUpperCase()));
					break;
				case MEAL:
					builder.setMeal(Meal.valueOf(getXMLText(reader).toUpperCase()));
					break;
				case PRICE:
					builder.setPrice(Double.parseDouble(getXMLText(reader)));
					break;
				default:
					break;
				}
				break;

			case XMLStreamConstants.END_ELEMENT:
				name = reader.getLocalName();
				if (OrderTagName.valueOf(name.toUpperCase()) == OrderTagName.TOURIST) {
					tourists.add(tourist);
				} else if (OrderTagName.valueOf(name.toUpperCase()) == OrderTagName.TOURISTS) {
					builder.setTourists(tourists);
				} else if (OrderTagName.valueOf(name.toUpperCase()) == OrderTagName.ORDER) {
					order = builder.build();
					System.out.println(order);
					return order;
				}
				break;

			}
		}
		
		throw new XMLStreamException("Unknown element");
	}


	private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
		String text = null;
		if (reader.hasNext()) {
			reader.next();
			text = reader.getText();
		}
		if (text == null) {
			throw new XMLStreamException();
		} else {
			return text;
		}
	}
}
