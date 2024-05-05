package index;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseConnection {
	public Connection connection;
	public Statement statement;

	public DatabaseConnection() {
		//establish connection
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/puddingdb", "root", "");
			statement = connection.createStatement();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void migrateTable() {
		createPuddingTable();
	}
	
	public void createPuddingTable() {
		String query = "CREATE TABLE IF NOT EXISTS pudding("
//				+ "Kode INT AUTO_INCREMENT PRIMARY KEY,"
				+ "Nama VARCHAR(100) NOT NULL,"
				+ "Harga INT NOT NULL,"
				+ "Stok INT NOT NULL)";
		exec(query);
	}
	
	public void exec(String query) { 
		try {
			statement.execute(query);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	

}
