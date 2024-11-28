package laptop.service;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import laptop.service.DatabaseConnectionService;

public class DemoTrial {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String serverName="titan.csse.rose-hulman.edu";
		String databaseName="LaptopOnly10";
		String user ="ledq";
		String pass ="Password123";
		DatabaseConnectionService dbsv = new DatabaseConnectionService(serverName, databaseName);
		dbsv.connect(user, pass);
		
		ArrayList<String> rests = new ArrayList<String>();
		String query = "SELECT LapTopName FROM Laptop";
		try	(Statement stmt = dbsv.getConnection().createStatement()){
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String restName = rs.getString("LapTopName");
				System.out.println(restName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
