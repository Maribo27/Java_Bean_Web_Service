package by.maribo.java_beans_handbook.entity;

import by.maribo.java_beans_handbook.entity.validation.EntityValidator;
import by.maribo.java_beans_handbook.entity.validation.InvalidEntityTypeException;
import by.maribo.java_beans_handbook.structure.dao.DAOException;
import by.maribo.java_beans_handbook.structure.dao.DAOFactory;
import by.maribo.java_beans_handbook.structure.dao.NothingFoundException;
import by.maribo.java_beans_handbook.structure.service.ServiceException;
import by.maribo.java_beans_handbook.structure.service.validation.InputException;
import by.maribo.java_beans_handbook.structure.service.validation.Validator;

import java.util.List;

public class EntityService {

	public List<Entity> getAllEntities(String entityType) throws ServiceException {
		EntityDAO dao = DAOFactory.getInstance().getEntityDAO();
		try {
			EntityValidator.isType(entityType);
			return dao.getAllEntities(entityType);
		} catch (NothingFoundException e) {
			throw new EntityNotFoundException("Entities not found");
		} catch (InvalidEntityTypeException | DAOException e) {
			throw new ServiceException("Entity service exception : " + e.getMessage());
		}
	}

	public Entity addEntity(String name, String description, String type) throws ServiceException {
		EntityDAO dao = DAOFactory.getInstance().getEntityDAO();
		try {
			EntityValidator.isType(type);
			Validator.isName(name);
			Entity entity = new Entity(name, description, type);
			return dao.addEntity(entity);
		} catch (InputException | DAOException e) {
			throw new ServiceException("Entity service exception : " + e.getMessage());
		}
	}

	public void deleteEntity(String idString, String type) throws ServiceException {
		EntityDAO dao = DAOFactory.getInstance().getEntityDAO();
		try {
			EntityValidator.isType(type);
			Validator.isID(idString);
			int id = Integer.parseInt(idString);
			dao.deleteEntity(id, type);
		} catch (InputException | DAOException e) {
			throw new ServiceException("Entity service exception : " + e.getMessage());
		}
	}

	public void modifyEntity(String idString, String name, String description, String type) throws ServiceException {
		EntityDAO dao = DAOFactory.getInstance().getEntityDAO();
		try {
			EntityValidator.isType(type);
			Validator.isName(name);
			Validator.isID(idString);
			int id = Integer.parseInt(idString);
			Entity entity = new Entity(id, name, description, type);
			dao.modifyEntity(entity);
		} catch (InputException | DAOException e) {
			throw new ServiceException("Entity service exception : " + e.getMessage());
		}
	}

	public Entity getEntity(String idString, String type) {
		EntityDAO dao = DAOFactory.getInstance().getEntityDAO();
		try {
			EntityValidator.isType(type);
			Validator.isID(idString);
			int id = Integer.parseInt(idString);
			return dao.getEntity(id, type);
		} catch (InputException | DAOException e) {
			throw new ServiceException("Entity service exception : " + e.getMessage());
		}
	}
}