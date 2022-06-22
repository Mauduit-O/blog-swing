package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database_connection {
	public  Connection getConnection() {
		String url = "jdbc:mysql://localhost:8889/";
		String dbName = "DAO";
		String user="root";
		String pwd="root";
		Connection connect = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			try {
				connect = (Connection) DriverManager.getConnection(url+dbName,user,pwd);

			} catch (SQLException e) {

				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {e.printStackTrace();
		}
		return connect;
	}

}
