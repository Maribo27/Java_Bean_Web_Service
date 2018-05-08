package by.maribo.web_service.repository.impl;

import by.maribo.web_service.entity.Entity;
import by.maribo.web_service.repository.EntityRepository;

import java.util.ArrayList;
import java.util.List;

public class Rule implements EntityRepository {
	private static final Rule instance = new Rule();
	private List<Entity> repository;
	private int id;

	private Rule() {
		repository = new ArrayList<>();
		id = 1;

		Entity entity = new Entity(id++, "Конструктор", "Класс должен иметь конструктор без параметров, с модификатором доступа public. Такой конструктор позволяет инструментам создать объект без дополнительных сложностей с параметрами.");
		repository.add(entity);

		entity = new Entity(id++, "Переопределённые методы", "Класс должен иметь переопределенные методы equals(), hashCode() и toString().");
		repository.add(entity);

		entity = new Entity(id++, "Ограниченные свойства", "Ограниченные свойства введены для того, чтобы была возможность запретить изменение свойства бина, если это необходимо. Т.е. бин будет как-бы спрашивать разрешение у зарегистрированных слушателей на изменение данного свойства. В случае, если слушатель не разрешает ему менять свойство, он генерирует исключение PropertyVetoException. Соответственно set-метод для ограниченного свойства должен иметь в своем описании throws PropertyVetoException. Это заставляет перехватывать это исключение в точке вызова этого set-метода. В результате прикладная программа, использующая этот бин, будет извещена, что ограниченное свойство не было изменено.");
		repository.add(entity);

		entity = new Entity(id++, "Связанные свойства", "Важным аспектом технологии JavaBeans является возможность бинов взаимодействовать с другими объектами, в частности, с другими бинами. JavaBeans реализует такое взаимодействие путем генерации (firing) событий и прослушивания (listening) событий.\n" +
				"В приложении к бинам взаимодействие объектов с бином через событийную модель выглядит так. Объект, который интересуется тем, что может произойти во внешнем, по отношению к нему, бине, может зарегистрировать себя как слушателя (listener) этого бина. В результате, при возникновении соответствующего события в бине будет вызван определенный метод данного объекта, которому в качестве параметра будет передан объект-событие (event). Причем, если зарегистрировалось несколько слушателей, то эти методы будут последовательно вызваны для каждого слушателя.");
		repository.add(entity);

		entity = new Entity(id++, "Сохраняемость", "Класс должен быть сериализуем. Это даёт возможность надёжно сохранять, хранить и восстанавливать состояние bean независимым от платформы и виртуальной машины способом.");
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

	public static Rule getInstance() {
		return instance;
	}
}