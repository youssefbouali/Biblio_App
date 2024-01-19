package contextDb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionDB {
	
	private static Connection conn;
	private static String url = "jdbc:mysql://localhost/biblio";
	
	static {
			
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, "root", "123456");
		}catch(SQLException e) {
			System.out.println("Problème de base de donnée");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public static Connection getConnection() {
		return conn;
	}

}
