package by.maribo.web_service.dao;

import by.maribo.web_service.entity.Method;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MethodDAO {
	private static final String GET_ALL_METHODS_QUERY = "SELECT id, name, description, necessity FROM method";
	private static final String ADD_METHOD_QUERY = "INSERT INTO method(name, description, necessity) VALUES(?, ?, ?)";
	private static final String DELETE_METHOD_QUERY = "DELETE FROM method WHERE id=?";
	private static final String UPDATE_METHOD_QUERY = "UPDATE method SET name=?, description=?, necessity=? WHERE id=?";

	public List<Method> getAllMethods() {
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

	public void addMethod(Method method) {
		Connection connection = null;

		try {
			connection = Connector.getConnection();
			PreparedStatement statement = connection.prepareStatement(ADD_METHOD_QUERY);

			statement.setString(1, method.getName());
			statement.setString(2, method.getDescription());
			statement.setString(3, method.getNecessity());

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

	public void deleteMethod(Method method) {
		Connection connection = null;

		try {
			connection = Connector.getConnection();
			PreparedStatement statement = connection.prepareStatement(DELETE_METHOD_QUERY);

			statement.setInt(1, method.getId());

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

	public void modifyMethod(Method method) {
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
