package by.maribo.web_service.service;

import by.maribo.web_service.dao.DAOException;
import by.maribo.web_service.dao.DAOFactory;
import by.maribo.web_service.dao.EntityDAO;
import by.maribo.web_service.entity.Entity;
import by.maribo.web_service.entity.EntityList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/entity")
public class EntityService {
	@GET
	@Path("/{entityType}/list")
	@Produces(MediaType.APPLICATION_XML)
	public Response getAllEntities(@PathParam("entityType") String entityType) {
		EntityDAO dao = DAOFactory.getInstance().getEntityDAO();
		try {
			List<Entity> entities = dao.getAllEntities(entityType);
			return Response
					.status(Response.Status.ACCEPTED)
					.entity(new EntityList(entities))
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
	public Response addEntity(Entity entity) {
		EntityDAO dao = DAOFactory.getInstance().getEntityDAO();
		try {
			dao.addEntity(entity);
			return Response.status(Response.Status.ACCEPTED).build();
		} catch (DAOException e) {
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e)
					.build();
		}
	}

	@DELETE
	@Path("/{type}/{id}/delete")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response deleteEntity(@PathParam("type") String type, @PathParam("id") String id) {
		EntityDAO dao = DAOFactory.getInstance().getEntityDAO();
		try {
			dao.deleteEntity(type, Integer.parseInt(id));
			return Response.status(Response.Status.ACCEPTED).build();
		} catch (DAOException e) {
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e)
					.build();
		}
	}

	@PUT
	@Path("/modify")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response modifyEntity(Entity entity) {
		EntityDAO dao = DAOFactory.getInstance().getEntityDAO();
		try {
			dao.modifyEntity(entity);
			return Response.status(Response.Status.ACCEPTED).build();
		} catch (DAOException e) {
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e)
					.build();
		}
	}
}
