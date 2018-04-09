package by.maribo.web_service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

class Connector {
	private static final String RESOURCE_NAME = "database";
	private static final String PASSWORD = "db.password";
	private static final String USER = "db.user";
	private static final String URL = "db.url";
	private static final String DRIVER = "db.driver";

	private static final String CONNECTION_FAILED_MESSAGE = "Can't get connection to database";
	private static final String CONNECTION_CLOSING_FAILED_MESSAGE = "Failed to close database connection";
	private static final String JDBC_DRIVER_NOT_FOUND_MESSAGE = "JDBC driver not found";

	static Connection getConnection() throws Exception {
		ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME);
		String url = resourceBundle.getString(URL);
		String user = resourceBundle.getString(USER);
		String password = resourceBundle.getString(PASSWORD);

		try {
			Class.forName(resourceBundle.getString(DRIVER));
			return DriverManager.getConnection(url,user,password);
		} catch (ClassNotFoundException e) {
			throw new Exception(JDBC_DRIVER_NOT_FOUND_MESSAGE);
		} catch (SQLException e) {
			throw new Exception(CONNECTION_FAILED_MESSAGE);
		}
	}

	static void closeConnection(Connection connection) throws Exception {
		try {
			connection.close();
		} catch (SQLException e) {
			throw new Exception(CONNECTION_CLOSING_FAILED_MESSAGE);
		}
	}
}
