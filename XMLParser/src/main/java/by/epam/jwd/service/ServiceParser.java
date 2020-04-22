package by.epam.jwd.service;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import by.epam.jwd.entity.Order;

public interface ServiceParser {

	public Set<Order> parseXML(HttpServletRequest request) throws ServiceException;
}