package by.maribo.web_service;

import by.maribo.web_service.entity.Entity;
import by.maribo.web_service.entity.Method;
import org.apache.axis2.AxisFault;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Client implements IJavaBeansHandbookService{
	private final static String END_POINT = "http://localhost:8080/axis2/services/JavaBeansHandbookService?wsdl";

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
			throw new ClientConnectionException("Cannot get all methods");
		}
	}

	@Override
	public void addMethod(Method method) {
		try {
			JavaBeansHandbookServiceStub.Method methodFromStub = createMethod(method);

			JavaBeansHandbookServiceStub.AddMethod addMethod = new JavaBeansHandbookServiceStub.AddMethod();
			addMethod.setMethod(methodFromStub);

			serviceStub.addMethod(addMethod);
		} catch (RemoteException e) {
			throw new ClientConnectionException("Cannot add method");
		}
	}

	@Override
	public void deleteMethod(Method method) {
		try {
			JavaBeansHandbookServiceStub.Method methodFromStub = createMethod(method);

			JavaBeansHandbookServiceStub.DeleteMethod deleteMethod = new JavaBeansHandbookServiceStub.DeleteMethod();
			deleteMethod.setMethod(methodFromStub);

			serviceStub.deleteMethod(deleteMethod);
		} catch (RemoteException e) {
			throw new ClientConnectionException("Cannot delete method");
		}
	}

	@Override
	public void modifyMethod(int id, Method method) {
		try {
			JavaBeansHandbookServiceStub.Method methodFromStub = createMethod(method);

			JavaBeansHandbookServiceStub.ModifyMethod modifyMethod = new JavaBeansHandbookServiceStub.ModifyMethod();
			modifyMethod.setMethod(methodFromStub);

			serviceStub.modifyMethod(modifyMethod);
		} catch (RemoteException e) {
			throw new ClientConnectionException("Cannot modify method");
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
			JavaBeansHandbookServiceStub.Entity entityFromStub = createEntity(entity);

			JavaBeansHandbookServiceStub.AddEntity addEntity = new JavaBeansHandbookServiceStub.AddEntity();
			addEntity.setEntity(entityFromStub);
			addEntity.setEntityType(entityType);

			serviceStub.addEntity(addEntity);
		} catch (RemoteException e) {
			throw new ClientConnectionException("Cannot add entity");
		}
	}

	@Override
	public void deleteEntity(Entity entity, String entityType) {
		try {
			JavaBeansHandbookServiceStub.Entity entityFromStub = createEntity(entity);

			JavaBeansHandbookServiceStub.DeleteEntity deleteEntity = new JavaBeansHandbookServiceStub.DeleteEntity();
			deleteEntity.setEntity(entityFromStub);
			deleteEntity.setEntityType(entityType);

			serviceStub.deleteEntity(deleteEntity);
		} catch (RemoteException e) {
			throw new ClientConnectionException("Cannot delete entity");
		}
	}

	@Override
	public void modifyEntity(int id, Entity entity, String entityType) {
		try {
			JavaBeansHandbookServiceStub.Entity entityFromStub = createEntity(entity);

			JavaBeansHandbookServiceStub.ModifyEntity modifyEntity = new JavaBeansHandbookServiceStub.ModifyEntity();
			modifyEntity.setId(id);
			modifyEntity.setEntity(entityFromStub);
			modifyEntity.setEntityType(entityType);

			serviceStub.modifyEntity(modifyEntity);
		} catch (RemoteException e) {
			throw new ClientConnectionException("Cannot modify entity");
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
			method.setName(methodFromStub.getName());
			method.setDescription(methodFromStub.getDescription());
			methodList.add(method);
		}
		return methodList;
	}

	private List<Entity> createEntities(JavaBeansHandbookServiceStub.Entity[] entities) {
		List<Entity> entityList = new ArrayList<>();
		for (JavaBeansHandbookServiceStub.Entity entityFromStub : entities) {
			Entity entity = new Entity();
			entity.setId(entityFromStub.getId());
			entity.setName(entityFromStub.getName());
			entity.setDescription(entityFromStub.getDescription());
			entityList.add(entity);
		}
		return entityList;
	}
}