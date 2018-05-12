package by.maribo.web_service.service;

import by.maribo.web_service.dao.DAOException;
import by.maribo.web_service.dao.DAOFactory;
import by.maribo.web_service.dao.MethodDAO;
import by.maribo.web_service.entity.Method;
import by.maribo.web_service.entity.MethodList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/method")
public class MethodService {
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_XML)
	public Response getAllMethods() {
		MethodDAO dao = DAOFactory.getInstance().getMethodDAO();
		try {
			List<Method> methods = dao.getAllMethods();
			return Response
					.status(Response.Status.ACCEPTED)
					.entity(new MethodList(methods))
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
	public Response addMethod(Method method) {
		MethodDAO dao = DAOFactory.getInstance().getMethodDAO();
		try {
			dao.addMethod(method);
			return Response.status(Response.Status.ACCEPTED).build();
		} catch (DAOException e) {
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e)
					.build();
		}
	}

	@DELETE
	@Path("/{id}/delete")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response deleteMethod(@PathParam("id") String id) {
		MethodDAO dao = DAOFactory.getInstance().getMethodDAO();
		try {
			dao.deleteMethod(Integer.parseInt(id));
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
	public Response modifyMethod(Method method) {
		MethodDAO dao = DAOFactory.getInstance().getMethodDAO();
		try {
			dao.modifyMethod(method);
			return Response.status(Response.Status.ACCEPTED).build();
		} catch (DAOException e) {
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e)
					.build();
		}
	}
}
