package by.maribo.web_service;

import by.maribo.web_service.entity.Entity;
import by.maribo.web_service.entity.Method;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import java.util.List;

public class Client {
	private HandbookServer.Client client;

	public void connect() {
		try {
			TTransport transport = new TSocket("0.0.0.0", 7911);
			transport.open();
			TProtocol protocol = new TBinaryProtocol(transport);
			client = new HandbookServer.Client(protocol);
		} catch (TTransportException e) {
			throw new ClientConnectionException("Cannot connect to server");
		}
	}

	public List<Method> getAllMethods() {
		try {
			return client.getAllMethods();
		} catch (TException e) {
            throw new EntitiesNotFoundException("Methods not found");
		}
	}

	public void addMethod(Method method) {
		try {
			client.addMethod(method);
		} catch (TException e) {
            throw new EntitiesNotFoundException("Cannot add method");
		}
	}

	public void deleteMethod(Method method){
		try {
			client.deleteMethod(method);
		} catch (TException e) {
            throw new EntitiesNotFoundException("Cannot delete method");
		}
	}

	public void modifyMethod(int id, Method method){
		try {
			client.modifyMethod(id, method);
		} catch (TException e) {
            throw new EntitiesNotFoundException("Cannot change method");
		}
	}

	public List<Entity> getAllEntities(String entityType){
		try {
			return client.getAllEntities(entityType);
		} catch (TException e) {
			throw new EntitiesNotFoundException("Entities not found");
		}
	}

	public void addEntity(Entity entity, String entityType){
		try {
			client.addEntity(entity, entityType);
		} catch (TException e) {
            throw new EntitiesNotFoundException("Cannot add entity");
		}
	}

	public void deleteEntity(Entity entity, String entityType){
		try {
			client.deleteEntity(entity, entityType);
		} catch (TException e) {
            throw new EntitiesNotFoundException("Cannot delete entity");
		}
	}

	public void modifyEntity(int id, Entity entity, String entityType){
		try {
			client.modifyEntity(id, entity, entityType);
		} catch (TException e) {
            throw new EntitiesNotFoundException("Cannot change entity");
		}
	}
}