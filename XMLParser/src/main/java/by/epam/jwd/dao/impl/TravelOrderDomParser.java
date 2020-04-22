package by.epam.jwd.dao.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import by.epam.jwd.dao.TravelOrderXMLParserDAO;
import by.epam.jwd.dao.XMLParserDaoException;
import by.epam.jwd.entity.Order;
import by.epam.jwd.entity.Tourist;
import by.epam.jwd.entity.type.OrderTagName;
import by.epam.jwd.entity.type.TourType;
import by.epam.jwd.entity.type.TransportType;
import by.epam.jwd.entity.type.Meal;
import by.epam.jwd.entity.type.Country;

public class TravelOrderDomParser extends TravelOrderXMLParserDAO {
	private static final Logger logger = LogManager.getLogger();
	private DocumentBuilder documentBuilder;

	public TravelOrderDomParser() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			documentBuilder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			logger.error("Parser configuration error: " + e);
		}
	}

	private static String getElementTextContent(Element element, String elementName) {
		NodeList nodeList = element.getElementsByTagName(elementName);
		Node node = nodeList.item(0);
		return node.getTextContent();
	}

	@Override
	public void buildOrders(InputStream source) throws XMLParserDaoException{
		Document document;
		try {
			document = documentBuilder.parse(source);
			Element root = document.getDocumentElement();
			NodeList ordersList = root.getElementsByTagName(OrderTagName.ORDER.getName());

			for (int i = 0; i < ordersList.getLength(); i++) {
				Element orderElement = (Element) ordersList.item(i);
				Order order = buildOrder(orderElement);
				orders.add(order);
			}

		} catch (SAXException e) {
			logger.error("File error or I/O error: " + e);
			throw new XMLParserDaoException(e);

		} catch (IOException e) {
			logger.error("Parsing failure: " + e);
			throw new XMLParserDaoException(e);

		}
	}

	private Order buildOrder(Element orderElement) {

		Order order = new Order.OrderBuilder().setId(orderElement.getAttribute(OrderTagName.ID.getName()))
				.setTourType(
						TourType.valueOf(orderElement.getAttribute(OrderTagName.TOUR_TYPE.getName()).toUpperCase()))
				.setCountry(Country.valueOf(orderElement.getAttribute(OrderTagName.COUNTRY.getName()).toUpperCase()))
				.setCity(getElementTextContent(orderElement, OrderTagName.CITY.getName()))
				.setDate(getElementTextContent(orderElement, OrderTagName.STARTDATE.getName()))
				.setDuration(Integer.parseInt(getElementTextContent(orderElement, OrderTagName.DURATION.getName())))
				.setNumberOfTourists(
						Integer.parseInt(getElementTextContent(orderElement, OrderTagName.NUMBEROFTOURISTS.getName())))
				.setTourists(buildTouristsList(orderElement))
				.setTransport(TransportType
						.valueOf(getElementTextContent(orderElement, OrderTagName.TRANSPORT.getName()).toUpperCase()))
				.setMeal(Meal.valueOf(getElementTextContent(orderElement, OrderTagName.MEAL.getName()).toUpperCase()))
				.setPrice(Double.parseDouble(getElementTextContent(orderElement, OrderTagName.PRICE.getName())))
				.build();

		return order;
	}

	private List<Tourist> buildTouristsList(Element orderElement) {
		Tourist tourist = null;
		List<Tourist> tourists = new ArrayList<>();
		NodeList touristList = orderElement.getElementsByTagName("tourist");

		for (int i = 0; i < touristList.getLength(); i++) {
			Element touristElement = (Element) touristList.item(i);
			tourist = new Tourist();
			tourist.setName(getElementTextContent(touristElement, OrderTagName.NAME.getName()));
			tourist.setSurname(getElementTextContent(touristElement, OrderTagName.SURNAME.getName()));
			tourist.setPassport(getElementTextContent(touristElement, OrderTagName.PASSPORT.getName()));
			tourists.add(tourist);

		}
		return tourists;
	}
}
