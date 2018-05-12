package by.maribo.web_service.dao;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class DAOException extends RuntimeException implements ExceptionMapper<Throwable> {
    public DAOException(String exceptionMessage){
        super(exceptionMessage);
    }

    @Override
    public Response toResponse(Throwable throwable) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(throwable)
                .type(MediaType.APPLICATION_XML)
                .build();
    }
}
