package by.maribo.java_beans_handbook.structure.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Connector {
	private static final String RESOURCE_NAME = "database";
	private static final String PASSWORD = "db.password";
	private static final String USER = "db.user";
	private static final String URL = "db.url";
	private static final String DRIVER = "db.driver";

	private static final String CONNECTION_FAILED_MESSAGE = "Connection error : Can't get connection to database";
	private static final String CONNECTION_CLOSING_FAILED_MESSAGE = "Connection error : Failed to close database connection";
	private static final String JDBC_DRIVER_NOT_FOUND_MESSAGE = "Connection error : JDBC driver not found";

	public static Connection getConnection() {
		ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME);
		String url = resourceBundle.getString(URL);
		String user = resourceBundle.getString(USER);
		String password = resourceBundle.getString(PASSWORD);

		try {
			Class.forName(resourceBundle.getString(DRIVER));
			return DriverManager.getConnection(url,user,password);
		} catch (ClassNotFoundException e) {
			throw new DAOException(JDBC_DRIVER_NOT_FOUND_MESSAGE);
		} catch (SQLException e) {
			throw new DAOException(CONNECTION_FAILED_MESSAGE);
		}
	}

	public static void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			throw new DAOException(CONNECTION_CLOSING_FAILED_MESSAGE);
		}
	}
}
