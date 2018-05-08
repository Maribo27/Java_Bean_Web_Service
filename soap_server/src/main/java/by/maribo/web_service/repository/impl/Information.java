package by.maribo.web_service.repository.impl;

import by.maribo.web_service.entity.Entity;
import by.maribo.web_service.repository.EntityRepository;

import java.util.ArrayList;
import java.util.List;

public class Information implements EntityRepository {
	private static final Information instance = new Information();
	private List<Entity> repository;
	private int id;

	private Information() {
		repository = new ArrayList<>();
		id = 1;

		Entity entity = new Entity(id++, "Версии",
				"\t* EJB 3.2, final release (2013-05-28)\n"
						+ "\t* EJB 3.1, final release (2009-12-10)\n"
						+ "\t* EJB 3.0, final release (2006-05-11)\n"
						+ "\t* EJB 2.1, final release (2003-11-24)\n"
						+ "\t* EJB 2.0, final release (2001-08-22)\n"
						+ "\t* EJB 1.1, final release (1999-12-17)\n"
						+ "\t* EJB 1.0 (1998-03-24)");
		repository.add(entity);

		entity = new Entity(id++, "Общие обязанности",
				"\t* Обработка транзакции\n"
						+ "\t* Iнтеграция с услугами сохранения, предлагаемыми API Java Persistence (JPA)\n"
						+ "\t* Контроль параллелизма\n"
						+ "\t* Управляемое событиями программирование с использованием службы сообщений Java и архитектуры Java EE Connector\n"
						+ "\t* Асинхронный вызов метода\n"
						+ "\t* Расписание работы\n"
						+ "\t* Iмена и службы каталогов (JNDI)\n"
						+ "\t* Межпроцессное взаимодействие с использованием RMI-IIOP и веб-служб\n"
						+ "\t* Безопасность (JCE и JAAS)\n"
						+ "\t* Развертывание программных компонентов на сервере приложений");
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

	public static Information getInstance() {
		return instance;
	}
}
