package by.maribo.java_beans_handbook.structure.controller.command;

import by.maribo.java_beans_handbook.entity.command.*;
import by.maribo.java_beans_handbook.method.command.*;

import java.util.HashMap;
import java.util.Map;

public class CommandDirector {
	private Map<CommandType, Command> commandMap = new HashMap<>();
	private static CommandDirector instance = new CommandDirector();

	private CommandDirector() {
		commandMap.put(CommandType.ADD_ENTITY, new AddingEntity());
		commandMap.put(CommandType.ADD_METHOD, new AddingMethod());
		commandMap.put(CommandType.GET_ENTITIES, new AllEntities());
		commandMap.put(CommandType.GET_ENTITY, new EntityInformation());
		commandMap.put(CommandType.GET_METHOD, new MethodInformation());
		commandMap.put(CommandType.GET_METHODS, new AllMethods());
		commandMap.put(CommandType.DELETE_ENTITY, new DeletingEntity());
		commandMap.put(CommandType.DELETE_METHOD, new DeletingMethod());
		commandMap.put(CommandType.MODIFY_ENTITY, new ModifyingEntity());
		commandMap.put(CommandType.MODIFY_METHOD, new ModifyingMethod());
		commandMap.put(CommandType.MODIFY, new Modifying());
	}

	public Command getCommand(String name) {
		CommandType commandName = CommandType.valueOf(name.toUpperCase());
		return commandMap.get(commandName);
	}

	public static CommandDirector getInstance() {
		return instance;
	}
}