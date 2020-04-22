package by.epam.jwd.controller.command.impl;
import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jwd.controller.PageContainer;
import by.epam.jwd.controller.command.Command;
import by.epam.jwd.entity.Order;
import by.epam.jwd.service.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;
import by.epam.jwd.service.ServiceParser;

public class SAX implements Command {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		logger.debug("start SAXCommand");
		ServiceFactory factory = ServiceFactory.getInstance();
		ServiceParser serviceParser = factory.getSaxServiceParser();
		try {
			Set<Order> orders = serviceParser.parseXML(request);
			if (orders != null) {
				request.setAttribute("orderSet", orders);
				request.getRequestDispatcher(PageContainer.RESULT_TABLE_PAGE).forward(request, response);
				
			 } else {
	            request.getRequestDispatcher(PageContainer.INDEX_PAGE).forward(request, response);
	         }
				logger.debug("end DOMCommand");
				
			} catch (ServiceException | ServletException | IOException e) {
				logger.error(e);
				response.sendRedirect(PageContainer.ERROR_PAGE);
			}
		}
}
