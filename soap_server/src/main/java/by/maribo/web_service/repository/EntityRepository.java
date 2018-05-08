package by.maribo.web_service.repository;

import by.maribo.web_service.entity.Entity;

import java.util.List;

public interface EntityRepository {
	void add(Entity entity) throws CurrentEntityExistInRepository;
	void delete(Entity entity) throws EntityNotExistInRepository;
	void modify(int id, Entity entity) throws EntityNotExistInRepository;

	List<Entity> getRepository();
	void setRepository(List<Entity> entities);
}
