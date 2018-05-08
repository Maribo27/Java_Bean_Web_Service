package by.maribo.web_service.repository.impl;

import by.maribo.web_service.entity.Entity;
import by.maribo.web_service.repository.EntityRepository;

import java.util.ArrayList;
import java.util.List;

public class Type implements EntityRepository {
	private static final Type instance = new Type();
	private List<Entity> repository;
	private int id;

	private Type() {
		repository = new ArrayList<>();
		id = 1;
		Entity entity = new Entity(id++, "JavaBeans",
				"Они используются для объединения нескольких объектов в один для удобной передачи данных.\n"
				+ "Спецификация Sun Microsystems определяет JavaBeans как повторно используемые программные компоненты, которыми можно управлять, используя графические конструкторы и средства IDE.\n"
				+ "JavaBeans обеспечивают основу для многократно используемых, встраиваемых и модульных компонентов ПО.\n"
				+ "Компоненты JavaBeans могут принимать различные формы, но наиболее широко они применяются в элементах графического пользовательского интерфейса.\n"
				+ "Одна из целей создания JavaBeans — взаимодействие с похожими компонентными структурами. Например, Windows-программа, при наличии соответствующего моста или объекта-обёртки, может использовать компонент JavaBeans так, будто бы он является компонентом COM или ActiveX.\n"
				+ "Так как требования в основном изложены в виде соглашения, а не интерфейса, некоторые разработчики рассматривают JavaBeans, как Plain Old Java Objects, которые следуют определённым правилам именования.");
		repository.add(entity);

		entity = new Entity(id++, "Enterprise JavaBeans",
				"Enterprise JavaBeans (EJB) является одним из нескольких API Java для модульной разработки корпоративного программного обеспечения. EJB является программным компонентом на стороне сервера, который инкапсулирует бизнес-логику приложения. Веб-контейнер EJB обеспечивает среду выполнения для веб-программного обеспечения, включая компьютерную безопасность, сервлет Java, управление жизненным циклом, обработку транзакций и другие веб-службы. Спецификация EJB является подмножеством спецификации Java EE.");
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

	public static Type getInstance() {
		return instance;
	}
}
