package db;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DBConnection {
	
	private static Connection connection = null;
	
	public static Connection openConnection() {
		if (connection == null){
			try {
				 Properties properties = loadProperties();
				 String url = properties.getProperty("dburl"); 
				 connection = DriverManager.getConnection(url, properties);
			} catch (SQLException e) {
				throw new DBException(e.getMessage());
			}
		}
		return connection;
	}
	
	public static void closeConnection() {
		if (connection != null) {
			try {
				connection.close();	
			} catch (Exception e) {
				throw new DBException(e.getMessage());
			}
		}
	}
	
	public static Properties loadProperties() {
		try(BufferedReader bReader = new BufferedReader(new FileReader("db.properties"))){
			Properties properties = new Properties();
			properties.load(bReader);
			return properties;
		} catch (Exception e) {
			throw new DBException(e.getMessage());
		}
	}
	
}
