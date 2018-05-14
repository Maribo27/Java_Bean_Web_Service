package by.maribo.java_beans_handbook.method;

import by.maribo.java_beans_handbook.structure.dao.Connector;
import by.maribo.java_beans_handbook.structure.dao.DAOException;
import by.maribo.java_beans_handbook.structure.dao.NothingFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MethodDAO {
	private static final String GET_ALL_METHODS_QUERY = "SELECT id, name, description, necessity FROM method";
	private static final String GET_METHOD_ID_QUERY = "SELECT id FROM method WHERE name=?";
	private static final String GET_METHOD_QUERY = "SELECT id, name, description, necessity FROM method WHERE id=?";
	private static final String ADD_METHOD_QUERY = "INSERT INTO method(name, description, necessity) VALUES(?, ?, ?)";
	private static final String DELETE_METHOD_QUERY = "DELETE FROM method WHERE id=?";
	private static final String UPDATE_METHOD_QUERY = "UPDATE method SET name=?, description=?, necessity=? WHERE id=?";
	private static final String METHOD_NOT_FOUND = "Method not found";

	List<Method> getAllMethods() {
		Connection connection = null;

		try {
			connection = Connector.getConnection();
			PreparedStatement statement = connection.prepareStatement(GET_ALL_METHODS_QUERY);
			ResultSet resultSet = statement.executeQuery();

			if (!resultSet.isBeforeFirst()) {
				throw new NothingFoundException("Methods not found");
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

	Method addMethod(Method method) {
		Connection connection = null;

		try {
			connection = Connector.getConnection();
			PreparedStatement statement = connection.prepareStatement(ADD_METHOD_QUERY);

			statement.setString(1, method.getName());
			statement.setString(2, method.getDescription());
			statement.setString(3, method.getNecessity());

			statement.executeUpdate();

			statement = connection.prepareStatement(GET_METHOD_ID_QUERY);

			statement.setString(1, method.getName());

			ResultSet resultSet = statement.executeQuery();

			if (!resultSet.isBeforeFirst()) {
				throw new NothingFoundException(METHOD_NOT_FOUND);
			}

			while (resultSet.next()){
				method.setId(resultSet.getInt(1));
			}

			return method;
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

	void deleteMethod(int id) {
		Connection connection = null;

		try {
			connection = Connector.getConnection();
			PreparedStatement statement = connection.prepareStatement(DELETE_METHOD_QUERY);

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

	void modifyMethod(Method method) {
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

	Method getMethod(int id) {
		Connection connection = null;

		try {
			connection = Connector.getConnection();
			PreparedStatement statement = connection.prepareStatement(GET_METHOD_QUERY);

			statement.setInt(1, id);

			ResultSet resultSet = statement.executeQuery();

			if (!resultSet.isBeforeFirst()) {
				throw new NothingFoundException(METHOD_NOT_FOUND);
			}

			Method method = new Method();
			while (resultSet.next()){
				int column = 1;
				method.setId(resultSet.getInt(column++));
				method.setName(resultSet.getString(column++));
				method.setDescription(resultSet.getString(column++));
				method.setNecessity(resultSet.getString(column));
			}

			return method;
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
