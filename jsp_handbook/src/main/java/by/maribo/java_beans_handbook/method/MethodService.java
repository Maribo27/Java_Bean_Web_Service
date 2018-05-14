package by.maribo.java_beans_handbook.method;

import by.maribo.java_beans_handbook.method.validation.InvalidNecessityException;
import by.maribo.java_beans_handbook.method.validation.MethodValidator;
import by.maribo.java_beans_handbook.structure.dao.DAOException;
import by.maribo.java_beans_handbook.structure.dao.DAOFactory;
import by.maribo.java_beans_handbook.structure.service.ServiceException;
import by.maribo.java_beans_handbook.structure.service.validation.InputException;
import by.maribo.java_beans_handbook.structure.service.validation.NotNumberException;
import by.maribo.java_beans_handbook.structure.service.validation.Validator;

import java.util.List;

public class MethodService {
	public List<Method> getAllMethods() throws ServiceException {
		MethodDAO dao = DAOFactory.getInstance().getMethodDAO();
		try {
			return dao.getAllMethods();
		} catch (DAOException e) {
			throw new MethodNotFoundException("Method service exception : " + e.getMessage());
		}
	}

	public Method addMethod(String name, String description, String necessityName) throws ServiceException {
		MethodDAO dao = DAOFactory.getInstance().getMethodDAO();
		try {
			MethodValidator.isNecessity(necessityName);
			String necessity = Method.Necessity.valueOf(necessityName).getDescription();
			Method method = new Method(name, description, necessity);
			return dao.addMethod(method);
		} catch (InputException | InvalidNecessityException | DAOException e) {
			throw new ServiceException("Method service exception : " + e.getMessage());
		}
	}

	public void deleteMethod(String idString) throws ServiceException {
		MethodDAO dao = DAOFactory.getInstance().getMethodDAO();
		try {
			Validator.isID(idString);
			int id = Integer.parseInt(idString);
			dao.deleteMethod(id);
		} catch (NotNumberException | DAOException e) {
			throw new ServiceException("Method service exception : " + e.getMessage());
		}
	}

	public void modifyMethod(String idString, String name, String description, String necessityName) throws ServiceException {
		MethodDAO dao = DAOFactory.getInstance().getMethodDAO();
		try {
			Validator.isID(idString);
			int id = Integer.parseInt(idString);
			MethodValidator.isNecessity(necessityName);
			String necessity = Method.Necessity.valueOf(necessityName).getDescription();
			Method method = new Method(id, name, description, necessity);
			dao.modifyMethod(method);
		} catch (InputException | InvalidNecessityException | DAOException e) {
			throw new ServiceException("Method service exception : " + e.getMessage());
		}
	}

	public Method getMethod(String idString) {
		MethodDAO dao = DAOFactory.getInstance().getMethodDAO();
		try {
			Validator.isID(idString);
			int id = Integer.parseInt(idString);
			return dao.getMethod(id);
		} catch (InputException | DAOException e) {
			throw new ServiceException("Method service exception : " + e.getMessage());
		}
	}
}