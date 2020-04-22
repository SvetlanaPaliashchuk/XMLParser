package by.epam.jwd.dao.impl;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import by.epam.jwd.entity.Order;
import by.epam.jwd.entity.Tourist;
import by.epam.jwd.entity.type.*;

public class TravelOrderSaxHandler extends DefaultHandler {
	private static final Logger logger = LogManager.getLogger();
	private Set<Order> orders;
	private Order order;
	private OrderTagName currentEnum = null;
	private Order.OrderBuilder builder;
	private EnumSet<OrderTagName> withText;
	private List<Tourist> tourists;
	private Tourist tourist;

	public TravelOrderSaxHandler() {
		logger.debug("init TravelOrderSaxHandler");

		orders = new HashSet<Order>();
		withText = EnumSet.range(OrderTagName.COUNTRY, OrderTagName.PRICE);
	}

	public Set<Order> getOrders() {
		return orders;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attr) throws SAXException {
		if (OrderTagName.ORDER.getName().equals(localName)) {
			builder = new Order.OrderBuilder();
			builder.setTourType(TourType.valueOf(attr.getValue(0).toUpperCase().trim()));
			builder.setCountry(Country.valueOf(attr.getValue(1).toUpperCase().trim()));
			builder.setId(attr.getValue(2));
		}
		else if(OrderTagName.TOURISTS.getName().equals(localName)) {
			tourists = new ArrayList<Tourist>();
		}
		else if(OrderTagName.TOURIST.getName().equals(localName)) {
			tourist = new Tourist();
		}
		else {
			OrderTagName temp = OrderTagName.valueOf(localName.toUpperCase().trim());
			if (withText.contains(temp)) {
				currentEnum = temp;
			}
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (OrderTagName.ORDER.getName().equals(localName)) {
			order = builder.build();
			orders.add(order);
		}
		else if (OrderTagName.TOURISTS.getName().equals(localName)) {
			builder.setTourists(tourists);
		}
		else if (OrderTagName.TOURIST.getName().equals(localName)) {
			tourists.add(tourist);
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String s = new String(ch, start, length).trim();
		if (currentEnum != null) {
			switch (currentEnum) {
			case CITY:
				builder.setCity(s);
				break;
			case STARTDATE:
				builder.setDate(s);
				break;
			case DURATION:
				builder.setDuration(Integer.parseInt(s));
				break;
			case NUMBEROFTOURISTS:
				builder.setNumberOfTourists(Integer.parseInt(s));
				break;
			case SURNAME:
				tourist.setSurname(s);
				break;
			case NAME:
				tourist.setName(s);
				break;
			case PASSPORT:
				tourist.setPassport(s);
				break;
			case TRANSPORT:
				builder.setTransport(TransportType.valueOf(s.toUpperCase().trim()));
				break;
			case MEAL:
				builder.setMeal(Meal.valueOf(s.toUpperCase().trim()));
				break;
			case PRICE:
				builder.setPrice(Double.parseDouble(s));
				break;
			default:
                break;
			}
		}
		currentEnum = null;
	}
}
