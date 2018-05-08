package by.maribo.web_service.repository;

import by.maribo.web_service.entity.Method;

import java.util.ArrayList;
import java.util.List;

public class MethodRepository {
	private static final MethodRepository instance = new MethodRepository();
	private List<Method> repository;
	private int id;

	private MethodRepository() {
		repository = new ArrayList<>();
		id = 1;

		Method method = new Method(id++, "public boolean equals(Object obj)",
				"Метод сравнивает строку с указанным объектом. Результатом является значение true или false",
				"обязателен");
		repository.add(method);

		method = new Method(id++, "public int hashCode()",
				"Метод используется для получения уникального целого номера для данного объекта.\n"
				+ "Когда необходимо сохранить объект как структуру данных в некой хэш - таблице, этот номер используется для определения его местонахождения в этой таблице."
				+ "По умолчанию, метод hashCode()для объекта возвращает номер ячейки памяти, где объект сохраняется.",
				"обязателен");
		repository.add(method);

		method = new Method(id++, "public String toString()",
				"Метод преобразует и возвращает строку.",
				"обязателен");
		repository.add(method);

		method = new Method(id++, "public <PropertyType> getPropertyName()",
				"Метод возвращает значение выбранного свойства.",
				"если присутствует соответствующее поле");
		repository.add(method);

		method = new Method(id++, "public void setPropertyName (<PropertyType> value)",
				"Метод изменяет значение выбранного свойства.",
				"если присутствует соответствующее поле");
		repository.add(method);

		method = new Method(id++, "public boolean isPropertyName()",
				"Метод возвращает значение выбранного логического свойства.",
				"если присутствует соответствующее поле");
		repository.add(method);

		method = new Method(id++, "public <PropertyType> getPropertyName(int index)",
				"Метод возвращает значение выбранного индексированного свойства.",
				"если присутствует соответствующее индексированное поле");
		repository.add(method);

		method = new Method(id++, "public void setPropertyName(int index, <PropertyType> value)",
				"Метод изменяет значение выбранного индексированного свойства.",
				"если присутствует соответствующее индексированное поле");
		repository.add(method);

		method = new Method(id++, "public <PropertyType>[] getPropertyName()",
				"Метод возвращает все значения выбранного массива свойств.",
				"если присутствует соответствующее индексированное поле");
		repository.add(method);

		method = new Method(id++, "public void setPropertyName(<PropertyType>[] value)",
				"Метод изменяет все значения выбранного массива свойств.",
				"если присутствует соответствующее индексированное поле");
		repository.add(method);
	}

	public void add(Method method) {
		method.setId(id++);
		repository.add(method);
	}

	public void delete(Method method) {
		repository.remove(method);
	}

	public void modify(int id, Method method) {
		for (Method currentMethod : repository) {
			if (currentMethod.getId() == id) {
				currentMethod.setName(method.getName());
				currentMethod.setDescription(method.getDescription());
				currentMethod.setNecessity(method.getNecessity());
			}
		}
	}

	public List<Method> getRepository() {
		return repository;
	}

	public void setRepository(List<Method> methods) {
		repository = methods;
	}

	public static MethodRepository getInstance() {
		return instance;
	}
}