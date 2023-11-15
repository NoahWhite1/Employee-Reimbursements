package dev.noah.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtility {
	
	public static Connection getConnection() {
		
		try {
			FileInputStream stream = new FileInputStream("src/main/resources/connection.properties");
			Properties prop = new Properties();
			prop.load(stream);
			String info = prop.getProperty("condetails");
			Connection conn = DriverManager.getConnection(info);
			return conn;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
