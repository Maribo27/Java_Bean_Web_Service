package by.maribo.web_service;

import by.maribo.web_service.entity.Entity;
import by.maribo.web_service.entity.EntityList;
import by.maribo.web_service.entity.Method;
import by.maribo.web_service.entity.MethodList;
import org.glassfish.jersey.client.ClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class Client implements IJavaBeansHandbookService{
	private static final String TARGET = "http://localhost:8080/web-services-rest-server-1.0/rest/";
	private final static Logger logger = LoggerFactory.getLogger(Client.class);
	private javax.ws.rs.client.Client client;

	public Client() {
		ClientConfig config = new ClientConfig();
		client = ClientBuilder.newClient(config);
	}

	@Override
	public List<Method> getAllMethods() {
		logger.info("Taking all methods");
		client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(TARGET)
				.path("method").path("list");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
		MethodList response = invocationBuilder.get(MethodList.class);
		return response.getMethodList();
	}

	@Override
	public void addMethod(Method method) {
		logger.info("Adding method : " + method);

		javax.ws.rs.client.Client client = ClientBuilder.newClient();

		WebTarget webTarget = client.target(TARGET)
				.path("method").path("add");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.post(javax.ws.rs.client.Entity.entity(method, MediaType.APPLICATION_XML));
		int status = response.getStatus();
		logger.info("Response from server : " + response);
		if (status != Response.Status.ACCEPTED.getStatusCode()) {
			throw new ClientConnectionException("Cannot add method. Error code : " + status);
		}
	}

	@Override
	public void deleteMethod(Method method) {
		logger.info("Deleting method : " + method);
		javax.ws.rs.client.Client client = ClientBuilder.newClient();
		String id = String.valueOf(method.getId());
		WebTarget webTarget = client.target(TARGET)
				.path("method").path(id).path("delete");

		Invocation.Builder invocationBuilder = webTarget.request();
		Response response = invocationBuilder.delete();
		logger.info("Response from server : " + response);
		int status = response.getStatus();
		if (status != Response.Status.ACCEPTED.getStatusCode()) {
			throw new ClientConnectionException("Cannot delete method : " + method + ". Error code :" + status);
		}
	}

	@Override
	public void modifyMethod(Method method) {
		logger.info("Modifying method : " + method);
		javax.ws.rs.client.Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(TARGET)
				.path("method").path("modify");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.put(javax.ws.rs.client.Entity.entity(method, MediaType.APPLICATION_XML));
		logger.info("Response from server : " + response);
		int status = response.getStatus();
		if (status != Response.Status.ACCEPTED.getStatusCode()) {
			throw new ClientConnectionException("Cannot modify method : " + method + ". Error code : " + status);
		}
	}

	@Override
	public List<Entity> getAllEntities(String entityType) {
		logger.info("Taking all entities");
		javax.ws.rs.client.Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(TARGET)
				.path("entity").path(entityType).path("list");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
		EntityList response = invocationBuilder.get(EntityList.class);
		return response.getEntityList();
	}

	@Override
	public void addEntity(Entity entity) {
		logger.info("Adding entity : " + entity);

		javax.ws.rs.client.Client client = ClientBuilder.newClient();

		WebTarget webTarget = client.target(TARGET)
				.path("entity").path("add");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.post(javax.ws.rs.client.Entity.entity(entity, MediaType.APPLICATION_XML));
		int status = response.getStatus();
		logger.info("Response from server : " + response);
		if (status != Response.Status.ACCEPTED.getStatusCode()) {
			throw new ClientConnectionException("Cannot add entity. Error code : " + status);
		}
	}

	@Override
	public void deleteEntity(Entity entity) {
		logger.info("Deleting entity : " + entity);
		javax.ws.rs.client.Client client = ClientBuilder.newClient();
		String id = String.valueOf(entity.getId());
		WebTarget webTarget = client.target(TARGET)
				.path("entity").path(entity.getType()).path(id).path("delete");

		Invocation.Builder invocationBuilder = webTarget.request();
		Response response = invocationBuilder.delete();
		logger.info("Response from server : " + response);
		int status = response.getStatus();
		if (status != Response.Status.ACCEPTED.getStatusCode()) {
			throw new ClientConnectionException("Cannot delete entity : " + entity + ". Error code :" + status);
		}
	}

	@Override
	public void modifyEntity(Entity entity) {
		logger.info("Modifying entity : " + entity);
		javax.ws.rs.client.Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(TARGET)
				.path("entity").path("modify");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.put(javax.ws.rs.client.Entity.entity(entity, MediaType.APPLICATION_XML));
		logger.info("Response from server : " + response);
		int status = response.getStatus();
		if (status != Response.Status.ACCEPTED.getStatusCode()) {
			throw new ClientConnectionException("Cannot modify entity : " + entity + ". Error code : " + status);
		}
	}
}