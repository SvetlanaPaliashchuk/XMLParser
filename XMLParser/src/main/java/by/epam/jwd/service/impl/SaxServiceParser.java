package by.epam.jwd.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jwd.dao.TravelOrderXMLParserDAO;
import by.epam.jwd.dao.XMLParserDaoException;
import by.epam.jwd.dao.factory.XMLParserDAOFactory;
import by.epam.jwd.entity.Order;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.ServiceParser;
import by.epam.jwd.service.validator.XMLValidator;
import by.epam.jwd.service.validator.XMLValidatorException;

public class SaxServiceParser implements ServiceParser{
	private static final Logger logger = LogManager.getLogger();
	private static final String FILE = "file";


	
	private XMLParserDAOFactory daoFactory = XMLParserDAOFactory.getInstance();
	private TravelOrderXMLParserDAO parser = daoFactory.getDomParser();
	
	public Set<Order> parseXML(HttpServletRequest request) throws ServiceException {
		try {
			logger.debug("start parseXML");
			Part filePart = request.getPart(FILE);
			InputStream fileContent = filePart.getInputStream();
			XMLValidator validator = new XMLValidator();
			String xmlFilePath = parser.saveFile(request);
			logger.debug("file saved");
			validator.validateXML(xmlFilePath);

			parser.buildOrders(fileContent);
		} catch (IOException | ServletException | XMLParserDaoException e) {
			logger.error(e);
			throw new ServiceException(e);
		} catch (XMLValidatorException e) {
			logger.error("invalid xml", e);
			throw new ServiceException(e);
		}
		return parser.getOrders();
	}
}