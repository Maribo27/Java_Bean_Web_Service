package by.maribo.web_service.repository.impl;

import by.maribo.web_service.entity.Entity;
import by.maribo.web_service.repository.CurrentEntityExistInRepository;
import by.maribo.web_service.repository.EntityNotExistInRepository;
import by.maribo.web_service.repository.EntityRepository;

import java.util.ArrayList;
import java.util.List;

import static by.maribo.web_service.repository.Message.EXIST_IN_REPOSITORY;
import static by.maribo.web_service.repository.Message.IS_NOT_EXIST_IN_REPOSITORY;

public class EJBType implements EntityRepository {
	private static final EJBType instance = new EJBType();
	private List<Entity> repository;
	private int id;

	private EJBType() {
		repository = new ArrayList<>();
		id = 1;

		Entity entity = new Entity(id++, "Entity Bean",
				"Entity bean представляет собой компоненту, работающую с постоянной (persistent) информацией, хранящейся, например, в базе данных. Entity beans ассоциируются с элементами баз данных и могут быть доступны одновременно нескольким пользователям. Так как информация в базе данных является постоянной, то и entity beans живут постоянно, выживая, тем самым, после сбоев сервера (когда сервер восстанавливается после сбоя, он может восстановить бин из базы данных).");
		repository.add(entity);

		entity = new Entity(id++, "Message-Driven Bean",
				"Message-Driven Beans (MDB) работает в кооперации с системой сообщений JAVA [Java Messaging System](JMS), которая является абстрактным API для системы Message-Oriented Middleware (MOM), более-менее похожую на то, как JDBC является абстрактным API поверх SQL базы данных.\n"
						+ "Коротко, система MOM предоставляет модель сообщений с публичной подпиской, основанной на асинхронной, распределенной очереди сообщений.\n"
						+ "MDB обычно реализуются для выполнения некоторых действий при получении сообщений и выступают в роли объектно-ориентированных точек соединения между подсистемами, взаимодействующих посредством JMS.\n"
						+ "Отличие MDB от session beans и entity beans состоит в том, что они не предоставляют никаких удаленных или локальных представлений. Другими словами, клиентский код не может получить доступ к MDB, но MDB может использовать другие EJB и другие службы.");
		repository.add(entity);

		entity = new Entity(id++, "Session Bean",
				"Session bean представляет собой EJB-компоненту, связанную с одним клиентом. Бины этого типа, как правило, имеют ограниченный срок жизни (хотя это и не обязательно), и редко участвуют в транзакциях. В частности, они обычно не восстанавливаются после сбоя сервера. В качестве примера session bean можно взять бин, который живет в веб-сервере и динамически создает HTML-страницы клиенту, при этом следя за тем, какая именно страница загружена у клиента. Когда же пользователь покидает вэб-узел, или по истечении некоторого времени, session bean уничтожается. Несмотря на то, что в процессе своей работы, session bean мог сохранять некоторую информацию в базе данных, его предназачение заключается все-таки не в отображении состояния или в работе с вечными объектами, а просто в выполнении некоторых функций на стороне сервера от имени одного клиента.\n"
						+ "Session beans получаются в двух разных вариантах:\n"
						+ "\t* session beans не имеющий состояний(Stateless session beans - SLSB);\n"
						+ "\t* session beans поддерживающие состояния (Stateful session beans -SFSB).");
		repository.add(entity);
	}

	@Override
	public void add(Entity entity) throws CurrentEntityExistInRepository {
		if (repository.contains(entity)) {
			throw new CurrentEntityExistInRepository(entity.toString() + " " + EXIST_IN_REPOSITORY);
		}
		entity.setId(id++);
		repository.add(entity);
	}

	@Override
	public void delete(Entity entity) throws EntityNotExistInRepository {
		if (!repository.contains(entity)) {
			throw new EntityNotExistInRepository(entity.toString() + " " + IS_NOT_EXIST_IN_REPOSITORY);
		}
		repository.remove(entity);
	}

	@Override
	public void modify(int id, Entity entity) throws EntityNotExistInRepository {
		for (Entity currentEntity : repository) {
			if (currentEntity.getId() == id) {
				currentEntity.setName(entity.getName());
				currentEntity.setDescription(entity.getDescription());
				return;
			}
		}
		throw new EntityNotExistInRepository(entity.toString() + " " + IS_NOT_EXIST_IN_REPOSITORY);
	}

	@Override
	public List<Entity> getRepository() {
		return repository;
	}

	@Override
	public void setRepository(List<Entity> entities) {
		repository = entities;
	}

	public static EJBType getInstance() {
		return instance;
	}
}