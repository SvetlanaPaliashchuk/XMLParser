package by.epam.jwd.dao;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jwd.entity.Order;

public abstract class TravelOrderXMLParserDAO {
	protected Set<Order> orders;
	private static final Logger logger = LogManager.getLogger();
	private static final String FILE_NAME = "uploadedOrders.xml";
	private static final String FILE = "file";

	public TravelOrderXMLParserDAO() {
		orders = new HashSet<>();
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public abstract void buildOrders(InputStream source) throws XMLParserDaoException;

	public String saveFile(HttpServletRequest request) throws IOException, ServletException {
		logger.debug("start saveFile");
		String uploadPath = request.getServletContext().getRealPath("");

		Part filePart = request.getPart(FILE);
		String filePath = uploadPath + File.separator + FILE_NAME;
		filePart.write(filePath);
		return filePath;
	}
}
