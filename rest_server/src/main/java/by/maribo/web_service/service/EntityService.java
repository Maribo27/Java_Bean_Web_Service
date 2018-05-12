package by.maribo.web_service.service;

import by.maribo.web_service.dao.DAOException;
import by.maribo.web_service.dao.DAOFactory;
import by.maribo.web_service.dao.EntityDAO;
import by.maribo.web_service.entity.Entity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/entity")
public class EntityService {
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_XML)
	public Response getAllEntities(String entityType) {
		EntityDAO dao = DAOFactory.getInstance().getEntityDAO();
		try {
			List<Entity> entities = dao.getAllEntities(entityType);
			return Response
					.status(Response.Status.ACCEPTED)
					.entity(entities)
					.build();
		} catch (DAOException e) {
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e.getCause())
					.build();
		}
	}

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response addEntity(Entity entity, String entityType) {
		EntityDAO dao = DAOFactory.getInstance().getEntityDAO();
		try {
			dao.addEntity(entity, entityType);
			return Response.status(Response.Status.ACCEPTED).build();
		} catch (DAOException e) {
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e)
					.build();
		}
	}

	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response deleteEntity(Entity entity, String entityType) {
		EntityDAO dao = DAOFactory.getInstance().getEntityDAO();
		try {
			dao.deleteEntity(entity, entityType);
			return Response.status(Response.Status.ACCEPTED).build();
		} catch (DAOException e) {
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e)
					.build();
		}
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response modifyEntity(Entity entity, String entityType) {
		EntityDAO dao = DAOFactory.getInstance().getEntityDAO();
		try {
			dao.modifyEntity(entity, entityType);
			return Response.status(Response.Status.ACCEPTED).build();
		} catch (DAOException e) {
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e)
					.build();
		}
	}
}
