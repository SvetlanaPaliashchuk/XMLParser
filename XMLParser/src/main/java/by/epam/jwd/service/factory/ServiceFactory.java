package by.epam.jwd.service.factory;

import by.epam.jwd.service.ServiceParser;
import by.epam.jwd.service.impl.*;

public class ServiceFactory {
	private static final ServiceFactory INSTANCE = new ServiceFactory();

	private static final ServiceParser SAX_SERVICE_INSTANCE = new SaxServiceParser();
	private static final ServiceParser STAX_SERVICE_INSTANCE = new StaxServiceParser();

	private static final ServiceParser DOM_SERVICE_INSTANCE = new DomServiceParser();

	private ServiceFactory() {
	}

	public static ServiceFactory getInstance() {
		return INSTANCE;
	}

	public ServiceParser getSaxServiceParser() {
		return SAX_SERVICE_INSTANCE;
	}
	public ServiceParser getStaxServiceParser() {
		return STAX_SERVICE_INSTANCE;
	}
	public ServiceParser getDomServiceParser() {
		return DOM_SERVICE_INSTANCE;
	}

}
