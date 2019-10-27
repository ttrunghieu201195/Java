package com.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionString {

	public class MySQLConnUtils {

		public Connection getMySQLConnection() throws SQLException, ClassNotFoundException {
			String hostName = "localhost";
			String dbName = "quanly_phongtro";
			String userName = "root";
			String password = "";

			return getMySQLConnection(hostName, dbName, userName, password);
		}

		public Connection getMySQLConnection(String hostName, String dbName, String userName, String password)
				throws SQLException, ClassNotFoundException {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

			Connection conn = DriverManager.getConnection(connectionURL, userName, password);
			return conn;
		}
	}

	public class ConnectionUtils {

		public Connection getMyConnection() throws SQLException, ClassNotFoundException {

			MySQLConnUtils conn = new MySQLConnUtils();
			return conn.getMySQLConnection();
		}
	}

	public Connection ConnectString() throws ClassNotFoundException, SQLException  {
		ConnectionUtils connUtil = new ConnectionUtils();
		return connUtil.getMyConnection();
	}
}
