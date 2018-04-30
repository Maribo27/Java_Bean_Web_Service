package by.maribo.web_service.repository;

import by.maribo.web_service.repository.impl.*;

import java.util.HashMap;
import java.util.Map;

public class Director {
	private Map<EntityType, EntityRepository> repositories;
	private static Director instance = new Director();

	private Director() {
		repositories = new HashMap<>();
		repositories.put(EntityType.EJB_TYPE, Information.getInstance());
		repositories.put(EntityType.EJB_INFO, EJBType.getInstance());
		repositories.put(EntityType.PROPERTY, Property.getInstance());
		repositories.put(EntityType.ROLE, Role.getInstance());
		repositories.put(EntityType.RULE, Rule.getInstance());
		repositories.put(EntityType.TYPE, Type.getInstance());
	}

	public EntityRepository getRepository(String name) {
		EntityType commandName = EntityType.valueOf(name.toUpperCase());
		return repositories.get(commandName);
	}

	public static Director getInstance() {
		return instance;
	}
}