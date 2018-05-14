package by.maribo.java_beans_handbook.entity;

import by.maribo.java_beans_handbook.structure.dao.Connector;
import by.maribo.java_beans_handbook.structure.dao.DAOException;
import by.maribo.java_beans_handbook.structure.dao.NothingFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EntityDAO {
	private static final String GET_ALL_ENTITIES_QUERY = "SELECT id, name, description FROM %s";
	private static final String GET_ENTITY_ID_QUERY = "SELECT id FROM %s WHERE name=?";
	private static final String GET_ENTITY_QUERY = "SELECT id, name, description FROM %s WHERE id=?";
	private static final String ADD_ENTITY_QUERY = "INSERT INTO  %s(name, description) VALUES(?, ?)";
	private static final String DELETE_ENTITY_QUERY = "DELETE FROM  %s WHERE id=?";
	private static final String UPDATE_ENTITY_QUERY = "UPDATE  %s SET name=?, description=? WHERE id=?";
	private static final String ENTITY_NOT_FOUND = "Entity not found";

	List<Entity> getAllEntities(String entityType) {
		Connection connection = null;

		try {
			connection = Connector.getConnection();
			String query = String.format(GET_ALL_ENTITIES_QUERY, entityType);
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();

			if (!resultSet.isBeforeFirst()) {
				throw new NothingFoundException("Entities not found");
			}

			List<Entity> entities = new ArrayList<>();
			while (resultSet.next()){
				int column = 1;
				Entity entity = new Entity();
				entity.setId(resultSet.getInt(column++));
				entity.setName(resultSet.getString(column++));
				entity.setDescription(resultSet.getString(column));
				entity.setType(entityType);
				entities.add(entity);
			}

			return entities;
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
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

	Entity addEntity(Entity entity) {
		Connection connection = null;

		try {
			connection = Connector.getConnection();
			String query = String.format(ADD_ENTITY_QUERY, entity.getType());
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setString(1, entity.getName());
			statement.setString(2, entity.getDescription());

			statement.executeUpdate();

			query = String.format(GET_ENTITY_ID_QUERY, entity.getType());
			statement = connection.prepareStatement(query);

			statement.setString(1, entity.getName());

			ResultSet resultSet = statement.executeQuery();

			if (!resultSet.isBeforeFirst()) {
				throw new NothingFoundException(ENTITY_NOT_FOUND);
			}

			while (resultSet.next()){
				entity.setId(resultSet.getInt(1));
			}

			return entity;
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
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

	void deleteEntity(int id, String type) {
		Connection connection = null;

		try {
			connection = Connector.getConnection();
			String query = String.format(DELETE_ENTITY_QUERY, type);
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setInt(1, id);

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
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

	void modifyEntity(Entity entity) {
		Connection connection = null;

		try {
			connection = Connector.getConnection();
			String query = String.format(UPDATE_ENTITY_QUERY, entity.getType());
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setString(1, entity.getName());
			statement.setString(2, entity.getDescription());
			statement.setInt(3, entity.getId());

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
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

	Entity getEntity(int id, String type) {
		Connection connection = null;

		try {
			connection = Connector.getConnection();
			String query = String.format(GET_ENTITY_QUERY, type);
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setInt(1, id);

			ResultSet resultSet = statement.executeQuery();

			if (!resultSet.isBeforeFirst()) {
				throw new NothingFoundException(ENTITY_NOT_FOUND);
			}

			Entity entity = new Entity();
			while (resultSet.next()){
				int column = 1;
				entity.setId(resultSet.getInt(column++));
				entity.setName(resultSet.getString(column++));
				entity.setDescription(resultSet.getString(column));
				entity.setType(type);
			}

			return entity;
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
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
