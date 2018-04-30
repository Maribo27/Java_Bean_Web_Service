package by.maribo.web_service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class Connector {
	private static final String CONNECTION_FAILED_MESSAGE = "Can't get connection to database";
	private static final String CONNECTION_CLOSING_FAILED_MESSAGE = "Failed to close database connection";
	private static final String JDBC_DRIVER_NOT_FOUND_MESSAGE = "JDBC driver not found";

	static Connection getConnection() throws DataBaseConnectorException {

		String user = "student";
		String password = "stud";
		String url = "jdbc:mysql://localhost:3306/java_bean?autoReconnect=true&useSSL=false";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(url,user,password);
		} catch (ClassNotFoundException e) {
			throw new DataBaseConnectorException(JDBC_DRIVER_NOT_FOUND_MESSAGE);
		} catch (SQLException e) {
			throw new DataBaseConnectorException(CONNECTION_FAILED_MESSAGE);
		}
	}

	static void closeConnection(Connection connection) throws DataBaseConnectorException {
		try {
			connection.close();
		} catch (SQLException e) {
			throw new DataBaseConnectorException(CONNECTION_CLOSING_FAILED_MESSAGE);
		}
	}
}