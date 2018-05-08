package by.maribo.web_service.repository;

public class CurrentEntityExistInRepository extends RuntimeException {
	public CurrentEntityExistInRepository(String message) {
		super(message);
	}
}
