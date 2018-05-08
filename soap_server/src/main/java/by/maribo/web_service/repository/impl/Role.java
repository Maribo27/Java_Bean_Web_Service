package by.maribo.web_service.repository.impl;

import by.maribo.web_service.entity.Entity;
import by.maribo.web_service.repository.EntityRepository;

import java.util.ArrayList;
import java.util.List;

public class Role implements EntityRepository {
	private static final Role instance = new Role();
	private List<Entity> repository;
	private int id;

	private Role() {
		repository = new ArrayList<>();
		id = 1;

		Entity entity = new Entity(id++, "Application Assembler", "Iспользует различные развернутые компоненты в специфической среде для создания полного приложения.");
		repository.add(entity);

		entity = new Entity(id++, "Deployer", "Для EJB, развертывание состоит в процессе установки одного или нескольких EJB компонентов в специфическом EJB Контейнере. Deployer является экспертом в специфической рабочей среде и отвечает за связывание EJB со всеми ресурсами, которые ему нужны для работы (соединение с базой данных, таблицы, другие EJB и тому подобное).");
		repository.add(entity);

		entity = new Entity(id++, "Enterprise Bean Provider", "Реализует EJB компонент и оформляет его для распространения. Знает о прикладной области, но может не знать об операционной среде, в которой компонент будет использоваться.");
		repository.add(entity);

		entity = new Entity(id++, "System Administrator", "Он отвечает за создание и поддержку пользователей, баз данных и за общую инфраструктуру ресурсов, необходимых для специфической рабочей среды.");
		repository.add(entity);
	}

	@Override
	public void add(Entity entity) {
		entity.setId(id++);
		repository.add(entity);
	}

	@Override
	public void delete(Entity entity) {
		repository.remove(entity);
	}

	@Override
	public void modify(int id, Entity entity) {
		for (Entity currentEntity : repository) {
			if (currentEntity.getId() == id) {
				currentEntity.setName(entity.getName());
				currentEntity.setDescription(entity.getDescription());
			}
		}
	}

	@Override
	public List<Entity> getRepository() {
		return repository;
	}

	@Override
	public void setRepository(List<Entity> entities) {
		repository = entities;
	}

	public static Role getInstance() {
		return instance;
	}
}
