package by.maribo.web_service;

import by.maribo.web_service.entity.Entity;
import by.maribo.web_service.entity.Method;
import org.apache.axis2.AxisFault;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Client implements IJavaBeansHandbookService{
	private static final String TARGET = "http://localhost:8080/rest";
	private final static Logger logger = LoggerFactory.getLogger(Client.class);

	private JavaBeansHandbookServiceStub serviceStub;

	public Client() throws AxisFault {
		serviceStub = new JavaBeansHandbookServiceStub(END_POINT);
	}

	@Override
	public List<Method> getAllMethods() {
		try {
			JavaBeansHandbookServiceStub.GetAllMethods allMethods = new JavaBeansHandbookServiceStub.GetAllMethods();
			JavaBeansHandbookServiceStub.GetAllMethodsResponse response = serviceStub.getAllMethods(allMethods);
			JavaBeansHandbookServiceStub.Method[] methods = response.get_return();
			return createMethods(methods);
		} catch (RemoteException e) {
			throw new ClientConnectionException("Cannot get all methods: " + e.getMessage());
		}
	}

	@Override
	public void addMethod(Method method) {
		logger.info("Adding method " + method);
		Client client = ClientBuilder.newClient();

		WebTarget webTarget = client.target(TARGET)
				.path("method").path("add");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.post(Entity.entity(method, MediaType.APPLICATION_XML));
		int status = response.getStatus();
		LOGGER.info("Response from server" + response);
		if (status != Response.Status.ACCEPTED.getStatusCode()) {
			throw new ProtocolException("Cannot add method. Error code " + status);
		}

		try {
			method = encodeMethod(method);
			JavaBeansHandbookServiceStub.Method methodFromStub = createMethod(method);

			JavaBeansHandbookServiceStub.AddMethod addMethod = new JavaBeansHandbookServiceStub.AddMethod();
			addMethod.setMethod(methodFromStub);

			serviceStub.addMethod(addMethod);
		} catch (RemoteException e) {
			throw new ClientConnectionException("Cannot add method: " + e.getMessage());
		}
	}

	@Override
	public void deleteMethod(Method method) {
		try {
			method = encodeMethod(method);
			JavaBeansHandbookServiceStub.Method methodFromStub = createMethod(method);

			JavaBeansHandbookServiceStub.DeleteMethod deleteMethod = new JavaBeansHandbookServiceStub.DeleteMethod();
			deleteMethod.setMethod(methodFromStub);

			serviceStub.deleteMethod(deleteMethod);
		} catch (RemoteException e) {
			throw new ClientConnectionException("Cannot delete method: " + e.getMessage());
		}
	}

	@Override
	public void modifyMethod(int id, Method method) {
		try {
			method = encodeMethod(method);
			JavaBeansHandbookServiceStub.Method methodFromStub = createMethod(method);

			JavaBeansHandbookServiceStub.ModifyMethod modifyMethod = new JavaBeansHandbookServiceStub.ModifyMethod();
			modifyMethod.setMethod(methodFromStub);

			serviceStub.modifyMethod(modifyMethod);
		} catch (RemoteException e) {
			throw new ClientConnectionException("Cannot modify method: " + e.getMessage());
		}
	}

	@Override
	public List<Entity> getAllEntities(String entityType) {
		try {
			JavaBeansHandbookServiceStub.GetAllEntities allEntities = new JavaBeansHandbookServiceStub.GetAllEntities();
			allEntities.setEntityType(entityType);
			JavaBeansHandbookServiceStub.GetAllEntitiesResponse response = serviceStub.getAllEntities(allEntities);
			JavaBeansHandbookServiceStub.Entity[] entities = response.get_return();
			return createEntities(entities);
		} catch (RemoteException e) {
			throw new ClientConnectionException("Cannot get all entities: " + e.getMessage());
		}
	}

	@Override
	public void addEntity(Entity entity, String entityType) {
		try {
			entity = encodeEntity(entity);
			JavaBeansHandbookServiceStub.Entity entityFromStub = createEntity(entity);

			JavaBeansHandbookServiceStub.AddEntity addEntity = new JavaBeansHandbookServiceStub.AddEntity();
			addEntity.setEntity(entityFromStub);
			addEntity.setEntityType(entityType);

			serviceStub.addEntity(addEntity);
		} catch (RemoteException e) {
			throw new ClientConnectionException("Cannot add entity: " + e.getMessage());
		}
	}

	@Override
	public void deleteEntity(Entity entity, String entityType) {
		try {
			entity = encodeEntity(entity);
			JavaBeansHandbookServiceStub.Entity entityFromStub = createEntity(entity);

			JavaBeansHandbookServiceStub.DeleteEntity deleteEntity = new JavaBeansHandbookServiceStub.DeleteEntity();
			deleteEntity.setEntity(entityFromStub);
			deleteEntity.setEntityType(entityType);

			serviceStub.deleteEntity(deleteEntity);
		} catch (RemoteException e) {
			throw new ClientConnectionException("Cannot delete entity: " + e.getMessage());
		}
	}

	@Override
	public void modifyEntity(int id, Entity entity, String entityType) {
		try {
			entity = encodeEntity(entity);
			JavaBeansHandbookServiceStub.Entity entityFromStub = createEntity(entity);

			JavaBeansHandbookServiceStub.ModifyEntity modifyEntity = new JavaBeansHandbookServiceStub.ModifyEntity();
			modifyEntity.setId(id);
			modifyEntity.setEntity(entityFromStub);
			modifyEntity.setEntityType(entityType);

			serviceStub.modifyEntity(modifyEntity);
		} catch (RemoteException e) {
			throw new ClientConnectionException("Cannot modify entity: " + e.getMessage());
		}
	}

	private JavaBeansHandbookServiceStub.Entity createEntity(Entity entity) {
		JavaBeansHandbookServiceStub.Entity entityFromStub = new JavaBeansHandbookServiceStub.Entity();
		entityFromStub.setId(entity.getId());
		entityFromStub.setName(entity.getName());
		entityFromStub.setDescription(entity.getDescription());
		return entityFromStub;
	}

	private JavaBeansHandbookServiceStub.Method createMethod(Method method) {
		JavaBeansHandbookServiceStub.Method methodFromStub = new JavaBeansHandbookServiceStub.Method();
		methodFromStub.setId(method.getId());
		methodFromStub.setName(method.getName());
		methodFromStub.setDescription(method.getDescription());
		methodFromStub.setNecessity(method.getNecessity());
		return methodFromStub;
	}

	private List<Method> createMethods(JavaBeansHandbookServiceStub.Method[] methods) {
		List<Method> methodList = new ArrayList<>();
		for (JavaBeansHandbookServiceStub.Method methodFromStub : methods) {
			Method method = new Method();
			method.setId(methodFromStub.getId());

			String stubName = methodFromStub.getName();
			method.setName(encodeFromCp1251ToUtf8(stubName));
			String description = methodFromStub.getDescription();
			method.setDescription(encodeFromCp1251ToUtf8(description));
			String necessity = methodFromStub.getNecessity();
			method.setNecessity(encodeFromCp1251ToUtf8(necessity));

			methodList.add(method);
		}
		return methodList;
	}

	private List<Entity> createEntities(JavaBeansHandbookServiceStub.Entity[] entities) {
		List<Entity> entityList = new ArrayList<>();
		for (JavaBeansHandbookServiceStub.Entity entityFromStub : entities) {
			Entity entity = new Entity();
			entity.setId(entityFromStub.getId());

			String stubName = entityFromStub.getName();
			entity.setName(encodeFromCp1251ToUtf8(stubName));
			String description = entityFromStub.getDescription();
			entity.setDescription(encodeFromCp1251ToUtf8(description));

			entityList.add(entity);
		}
		return entityList;
	}

	private Method encodeMethod(Method method) {
		String name = encodeFromUtf8ToCp1251(method.getName());
		method.setName(name);
		String description = encodeFromUtf8ToCp1251(method.getDescription());
		method.setDescription(description);
		String necessity = encodeFromUtf8ToCp1251(method.getNecessity());
		method.setNecessity(necessity);
		return method;
	}

	private Entity encodeEntity(Entity entity) {
		String name = encodeFromUtf8ToCp1251(entity.getName());
		entity.setName(name);
		String description = encodeFromUtf8ToCp1251(entity.getDescription());
		entity.setDescription(description);
		return entity;
	}
	private String encodeFromCp1251ToUtf8(String stringToEncode) {
		try {
			byte text[] = stringToEncode.getBytes("cp1251");
			return new String(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.info("Cannot encode string - %s - from cp-1251 to utf-8", stringToEncode);
			return stringToEncode;
		}
	}

	private String encodeFromUtf8ToCp1251(String stringToEncode) {
		try {
			byte text[] = stringToEncode.getBytes("UTF-8");
			return new String(text, "cp1251");
		} catch (UnsupportedEncodingException e) {
			logger.info("Cannot encode string - %s - from utf-8 to cp-1251", stringToEncode);
			return stringToEncode;
		}
	}
}