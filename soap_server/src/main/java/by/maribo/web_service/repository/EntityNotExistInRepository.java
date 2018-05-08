package by.maribo.web_service.repository;

public class EntityNotExistInRepository extends RuntimeException {
	public EntityNotExistInRepository(String message) {
		super(message);
	}
}
