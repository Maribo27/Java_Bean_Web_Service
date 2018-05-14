package by.maribo.java_beans_handbook.entity.validation;

import by.maribo.java_beans_handbook.entity.Entity;

public class EntityValidator {

	public static void isType(String type) {
		for (Entity.Type entityType : Entity.Type.values()) {
			String typeName = entityType.name();
			if (type.equalsIgnoreCase(typeName)){
				return;
			}
		}
		throw new InvalidEntityTypeException("Couldn't find table : " + type);
	}
}