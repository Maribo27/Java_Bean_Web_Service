package by.maribo.web_service.dao;

public class Exception extends RuntimeException {
	public Exception(Throwable cause) {
		super(cause);
	}

	public Exception(String message) {
		super(message);
	}
}