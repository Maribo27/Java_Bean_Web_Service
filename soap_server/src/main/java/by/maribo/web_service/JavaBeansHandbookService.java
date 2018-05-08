package by.maribo.web_service;

import by.maribo.web_service.entity.Entity;
import by.maribo.web_service.entity.Method;
import by.maribo.web_service.repository.Director;
import by.maribo.web_service.repository.MethodRepository;

import java.util.List;

public class JavaBeansHandbookService implements IJavaBeansHandbookService{
	private Director director = Director.getInstance();
	private MethodRepository methodRepository = MethodRepository.getInstance();

	@Override
	public List<Method> getAllMethods() {
		return methodRepository.getRepository();
	}

	@Override
	public void addMethod(Method method) {
		methodRepository.add(method);
	}

	@Override
	public void deleteMethod(Method method) {
		methodRepository.delete(method);
	}

	@Override
	public void modifyMethod(int id, Method method) {
		methodRepository.modify(id, method);
	}

	@Override
	public List<Entity> getAllEntities(String entityType) {
		return director.getRepository(entityType).getRepository();
	}

	@Override
	public void addEntity(Entity entity, String entityType) {
		director.getRepository(entityType).add(entity);
	}

	@Override
	public void deleteEntity(Entity entity, String entityType) {
		director.getRepository(entityType).delete(entity);
	}

	@Override
	public void modifyEntity(int id, Entity entity, String entityType) {
		director.getRepository(entityType).modify(id, entity);
	}
}