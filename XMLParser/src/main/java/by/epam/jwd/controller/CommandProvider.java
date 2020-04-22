package by.epam.jwd.controller;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.controller.command.CommandName;
import by.epam.jwd.controller.command.impl.DOM;
import by.epam.jwd.controller.command.impl.Home;
import by.epam.jwd.controller.command.impl.SAX;
import by.epam.jwd.controller.command.impl.STAX;

import java.util.HashMap;
import java.util.Map;

final class CommandProvider {
	private final Map<CommandName, Command> repository = new HashMap<>();

	CommandProvider() {
		repository.put(CommandName.DOM, new DOM());
		repository.put(CommandName.SAX, new SAX());
		repository.put(CommandName.STAX, new STAX());
		repository.put(CommandName.HOME, new Home());

		
	}

	Command getCommand(String name) {
		CommandName commandName;
		Command command;
		commandName = CommandName.valueOf(name.toUpperCase());
//		System.out.println(commandName);
		command = repository.get(commandName);
		return command;
	}
}
