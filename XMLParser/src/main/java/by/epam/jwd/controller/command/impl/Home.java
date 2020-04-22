package by.epam.jwd.controller.command.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.jwd.controller.command.Command;

public class Home implements Command{

    public static final String HOME = "home";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String homePage = request.getParameter(HOME);
        request.setAttribute(HOME, homePage);
			response.sendRedirect(request.getContextPath() + "/");
		
	}
}
