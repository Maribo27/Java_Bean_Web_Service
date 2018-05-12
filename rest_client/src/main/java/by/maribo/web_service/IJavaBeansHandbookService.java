package by.maribo.web_service;

import by.maribo.web_service.entity.Entity;
import by.maribo.web_service.entity.Method;

import java.util.List;

public interface IJavaBeansHandbookService {
	List<Method> getAllMethods();
	void addMethod(Method method);
	void deleteMethod(Method method);
	void modifyMethod(int id, Method method);
	List<Entity> getAllEntities(String entityType);
	void addEntity(Entity entity, String entityType);
	void deleteEntity(Entity entity, String entityType);
	void modifyEntity(int id, Entity entity, String entityType);
}
