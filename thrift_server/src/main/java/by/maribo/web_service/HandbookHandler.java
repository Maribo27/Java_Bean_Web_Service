package by.maribo.web_service;

import by.maribo.web_service.entity.Entity;
import by.maribo.web_service.entity.Method;
import org.apache.thrift.TException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HandbookHandler implements HandbookServer.Iface {

	private static final String GET_ALL_METHODS_QUERY = "SELECT id, name, description, necessity FROM method";
	private static final String ADD_METHOD_QUERY = "INSERT INTO method(name, description, necessity) VALUES(?, ?, ?)";
	private static final String DELETE_METHOD_QUERY = "DELETE FROM method WHERE id=?";
	private static final String UPDATE_METHOD_QUERY = "UPDATE method SET name=?, description=?, necessity=? WHERE id=?";

	private static final String GET_ALL_ENTITIES_QUERY = "SELECT id, name, description FROM %s";
	private static final String ADD_ENTITY_QUERY = "INSERT INTO  %s(name, description) VALUES(?, ?)";
	private static final String DELETE_ENTITY_QUERY = "DELETE FROM  %s WHERE id=?";
	private static final String UPDATE_ENTITY_QUERY = "UPDATE  %s SET name=?, description=? WHERE id=?";


	@Override
	public List<Method> getAllMethods() throws TException {
		Connection connection = null;

		try {
			connection = Connector.getConnection();
			PreparedStatement statement = connection.prepareStatement(GET_ALL_METHODS_QUERY);
			ResultSet resultSet = statement.executeQuery();

			if (!resultSet.isBeforeFirst()) {
				throw new EntityNotFoundException("Methods not found");
			}

			List<Method> methods = new ArrayList<>();
			while (resultSet.next()){
				int column = 1;
				Method method = new Method();
				method.setId(resultSet.getInt(column++));
				method.setName(resultSet.getString(column++));
				method.setDescription(resultSet.getString(column++));
				method.setNecessity(resultSet.getString(column));
				methods.add(method);
			}

			return methods;
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		} finally {
			try {
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				Connector.closeConnection(connection);
			}
		}
	}

	@Override
	public void addMethod(Method method) throws TException {
		Connection connection = null;

		try {
			connection = Connector.getConnection();
			PreparedStatement statement = connection.prepareStatement(ADD_METHOD_QUERY);

			statement.setString(1, method.getName());
			statement.setString(2, method.getDescription());
			statement.setString(3, method.getNecessity());

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		} finally {
			try {
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				Connector.closeConnection(connection);
			}
		}
	}

	@Override
	public void deleteMethod(Method method) throws TException {
		Connection connection = null;

		try {
			connection = Connector.getConnection();
			PreparedStatement statement = connection.prepareStatement(DELETE_METHOD_QUERY);

			statement.setInt(1, method.getId());

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		} finally {
			try {
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				Connector.closeConnection(connection);
			}
		}
	}

	@Override
	public void modifyMethod(int id, Method method) throws TException {
		Connection connection = null;

		try {
			connection = Connector.getConnection();
			PreparedStatement statement = connection.prepareStatement(UPDATE_METHOD_QUERY);

			statement.setString(1, method.getName());
			statement.setString(2, method.getDescription());
			statement.setString(3, method.getNecessity());
			statement.setInt(4, method.getId());

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		} finally {
			try {
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				Connector.closeConnection(connection);
			}
		}

	}

	@Override
	public List<Entity> getAllEntities(String entityType) throws TException {
		Connection connection = null;

		try {
			connection = Connector.getConnection();
			String query = String.format(GET_ALL_ENTITIES_QUERY, entityType);
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();

			if (!resultSet.isBeforeFirst()) {
				throw new EntityNotFoundException("Entities not found");
			}

			List<Entity> entities = new ArrayList<>();
			while (resultSet.next()){
				int column = 1;
				Entity entity = new Entity();
				entity.setId(resultSet.getInt(column++));
				entity.setName(resultSet.getString(column++));
				entity.setDescription(resultSet.getString(column));
				entities.add(entity);
			}

			return entities;
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		} finally {
			try {
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				Connector.closeConnection(connection);
			}
		}
	}

	@Override
	public void addEntity(Entity entity, String entityType) throws TException {
		Connection connection = null;

		try {
			connection = Connector.getConnection();
			String query = String.format(ADD_ENTITY_QUERY, entityType);
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setString(1, entityType);
			statement.setString(2, entity.getName());
			statement.setString(3, entity.getDescription());

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		} finally {
			try {
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				Connector.closeConnection(connection);
			}
		}
	}

	@Override
	public void deleteEntity(Entity entity, String entityType) throws TException {
		Connection connection = null;

		try {
			connection = Connector.getConnection();
			String query = String.format(DELETE_ENTITY_QUERY, entityType);
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setString(1, entityType);
			statement.setInt(2, entity.getId());

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		} finally {
			try {
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				Connector.closeConnection(connection);
			}
		}
	}

	@Override
	public void modifyEntity(int id, Entity entity, String entityType) throws TException {
		Connection connection = null;

		try {
			connection = Connector.getConnection();
			String query = String.format(UPDATE_ENTITY_QUERY, entityType);
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setString(1, entityType);
			statement.setString(2, entity.getName());
			statement.setString(3, entity.getDescription());
			statement.setInt(4, entity.getId());

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		} finally {
			try {
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				Connector.closeConnection(connection);
			}
		}
	}
}
