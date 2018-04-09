package by.maribo.web_service;

import by.maribo.web_service.entity.Entity;
import by.maribo.web_service.entity.Method;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Client {
	private static final Logger log = LoggerFactory.getLogger(Client.class);
	private HandbookServer.Client client;

	public void connect() {
		try {
			TTransport transport = new TSocket("0.0.0.0", 7911);
			transport.open();

			TProtocol protocol = new TBinaryProtocol(transport);
			client = new HandbookServer.Client(protocol);

		} catch (TTransportException | Exception e) {
			log.error(e.getMessage());
		}
	}

	public List<Method> getAllMethods() {
		try {
			return client.getAllMethods();
		} catch (TException e) {
			log.error(e.getMessage());
            throw new EntitiesNotFoundException(e);
		}
	}

	public void addMethod(Method method) {
		try {
			client.addMethod(method);
		} catch (TException e) {
			log.error(e.getMessage());
            throw new EntitiesNotFoundException(e);
		}
	}

	public void deleteMethod(Method method){
		try {
			client.deleteMethod(method);
		} catch (TException e) {
			log.error(e.getMessage());
            throw new EntitiesNotFoundException(e);
		}
	}

	public void modifyMethod(int id, Method method){
		try {
			client.modifyMethod(id, method);
		} catch (TException e) {
			log.error(e.getMessage());
            throw new EntitiesNotFoundException(e);
		}
	}

	public List<Entity> getAllEntities(String entityType){
		try {
			return client.getAllEntities(entityType);
		} catch (TException e) {
			log.error(e.getMessage());
			throw new EntitiesNotFoundException(e);
		}
	}

	public void addEntity(Entity entity, String entityType){
		try {
			client.addEntity(entity, entityType);
		} catch (TException e) {
			log.error(e.getMessage());
            throw new EntitiesNotFoundException(e);
		}
	}

	public void deleteEntity(Entity entity, String entityType){
		try {
			client.deleteEntity(entity, entityType);
		} catch (TException e) {
			log.error(e.getMessage());
            throw new EntitiesNotFoundException(e);
		}
	}

	public void modifyEntity(int id, Entity entity, String entityType){
		try {
			client.modifyEntity(id, entity, entityType);
		} catch (TException e) {
			log.error(e.getMessage());
            throw new EntitiesNotFoundException(e);
		}
	}
}
