package laptop.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DatabaseConnectionService {

	//DO NOT EDIT THIS STRING, YOU WILL RECEIVE NO CREDIT FOR THIS TASK IF THIS STRING IS EDITED
	private final String SampleURL = "jdbc:sqlserver://${dbServer};databaseName=${dbName};user=${user};password={${pass}}";

	private Connection connection = null;

	private String databaseName;
	private String serverName;

	public DatabaseConnectionService(String serverName, String databaseName) {
		//DO NOT CHANGE THIS METHOD
		this.serverName = serverName;
		this.databaseName = databaseName;
	}

	public boolean connect(String user, String pass) {
		//TODO: Task 1
		//BUILD YOUR CONNECTION STRING HERE USING THE SAMPLE URL ABOVE
		String url = SampleURL.replace("${dbServer}", this.serverName)
				.replace("${dbName}",this.databaseName)
				.replace("${user}",user)
				.replace("${pass}", pass);
		try {
			this.connection = DriverManager.getConnection(url);
			System.out.println("Connect successfully");
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(url);
			e.printStackTrace();
			return false;
		}
	}
	

	public Connection getConnection() {
		return this.connection;
	}
	
	public void closeConnection() {
		//TODO: Task 1
		try {
			this.connection.close();
			System.out.println("Disconnected");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Could not close connection.");
			e.printStackTrace();
		}
	}

}
