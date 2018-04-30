package by.maribo.web_service.repository;

import by.maribo.web_service.entity.Entity;

import java.util.List;

public interface EntityRepository {
	void add(Entity entity);
	void delete(Entity entity);
	void modify(int id, Entity entity);

	List<Entity> getRepository();
	void setRepository(List<Entity> entities);
}
