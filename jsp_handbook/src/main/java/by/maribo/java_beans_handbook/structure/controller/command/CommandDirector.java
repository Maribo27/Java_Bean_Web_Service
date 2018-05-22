package by.maribo.java_beans_handbook.structure.controller.command;

import by.maribo.java_beans_handbook.entity.command.*;
import by.maribo.java_beans_handbook.method.command.*;
import by.maribo.java_beans_handbook.structure.bean.User;
import by.maribo.java_beans_handbook.structure.controller.command.oauth.*;

import java.util.HashMap;
import java.util.Map;

public class CommandDirector {
	private Map<CommandType, CommandDescriptor> commands = new HashMap<>();
	private static CommandDirector instance = new CommandDirector();

	private CommandDirector() {
		commands.put(CommandType.GET_ENTITIES, new CommandDescriptor(RightType.COMMON, new AllEntities()));
		commands.put(CommandType.GET_ENTITY, new CommandDescriptor(RightType.COMMON, new EntityInformation()));
		commands.put(CommandType.GET_METHOD, new CommandDescriptor(RightType.COMMON, new MethodInformation()));
		commands.put(CommandType.GET_METHODS, new CommandDescriptor(RightType.COMMON, new AllMethods()));

		commands.put(CommandType.ADD_ENTITY, new CommandDescriptor(RightType.FOR_LOGGED, new AddingEntity()));
		commands.put(CommandType.ADD_METHOD, new CommandDescriptor(RightType.FOR_LOGGED, new AddingMethod()));
		commands.put(CommandType.DELETE_ENTITY, new CommandDescriptor(RightType.FOR_LOGGED, new DeletingEntity()));
		commands.put(CommandType.DELETE_METHOD, new CommandDescriptor(RightType.FOR_LOGGED, new DeletingMethod()));
		commands.put(CommandType.MODIFY_ENTITY, new CommandDescriptor(RightType.FOR_LOGGED, new ModifyingEntity()));
		commands.put(CommandType.MODIFY_METHOD, new CommandDescriptor(RightType.FOR_LOGGED, new ModifyingMethod()));
		commands.put(CommandType.MODIFY, new CommandDescriptor(RightType.FOR_LOGGED, new Modifying()));
		commands.put(CommandType.LOG_OUT, new CommandDescriptor(RightType.FOR_LOGGED, new LogOff()));
		commands.put(CommandType.GOOGLE_AUTH, new CommandDescriptor(RightType.AUTH, new GoogleAuth()));
		commands.put(CommandType.GOOGLE_TOKEN, new CommandDescriptor(RightType.AUTH, new GoogleToken()));
		commands.put(CommandType.VK_AUTH, new CommandDescriptor(RightType.AUTH, new VKAuth()));
		commands.put(CommandType.VK_TOKEN, new CommandDescriptor(RightType.AUTH, new VKToken()));
	}

	public Command getCommand(String name) {
		CommandType commandName = CommandType.valueOf(name.toUpperCase());
		return commands.get(commandName).getCommand();
	}

	public void checkAccess(String commandName, User user) throws AccessIsNotAllowedException, UnknownCommandException {
		boolean tryToLogin;
		boolean validSession;
		boolean common;
		if (isCommand(commandName)) {
			CommandType type = CommandType.valueOf(commandName.toUpperCase());
			CommandDescriptor command = commands.get(type);
			tryToLogin = checkRightsAndSession(command, RightType.AUTH, user == null);
			validSession = checkRightsAndSession(command, RightType.FOR_LOGGED, user != null);
			common = command.getType() == RightType.COMMON;
		} else {
			throw new UnknownCommandException("Access is not allowed: unknown command");
		}
		boolean accessDenied = !tryToLogin && !validSession && !common;
		if (accessDenied) {
			throw new AccessIsNotAllowedException("Access is not allowed");
		}
	}

	private boolean checkRightsAndSession(CommandDescriptor command, RightType auth, boolean loggedUser) {
		return command.getType() == auth && loggedUser;
	}

	private boolean isCommand(String name) {
		CommandType commandName = CommandType.valueOf(name.toUpperCase());
		return commands.containsKey(commandName);
	}

	public static CommandDirector getInstance() {
		return instance;
	}
}