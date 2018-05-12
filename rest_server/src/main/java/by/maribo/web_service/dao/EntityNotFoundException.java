package by.maribo.web_service.dao;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class EntityNotFoundException extends RuntimeException implements ExceptionMapper<Throwable> {
	public EntityNotFoundException(String message) {
		super(message);
	}

	@Override
	public Response toResponse(Throwable throwable) {
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity(throwable)
				.type(MediaType.APPLICATION_XML)
				.build();
	}
}
