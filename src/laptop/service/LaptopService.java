package laptop.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class LaptopService {

	private DatabaseConnectionService dbService = null;
	
	public LaptopService(DatabaseConnectionService dbService) {
		this.dbService = dbService;
	}
	
	public boolean addLaptop(String restName, String addr, String contact) {
		
		JOptionPane.showMessageDialog(null, "Add Laptop not implemented.");
		return false;
	}
	
	
	
	public ArrayList<Laptop> getLaptop() {	
		
		
		ArrayList<Laptop> lap = new ArrayList<Laptop>();
		String query = "SELECT * FROM Laptop";
		try	(Statement stmt = dbService.getConnection().createStatement()){
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Laptop myLap= new Laptop(rs.getString("LapTopName"),
												rs.getString("Brand"),
												rs.getString("CPU"),
												rs.getString("GPU"),
												rs.getString("Ram"),
												rs.getString("Storage"),
												rs.getString("ScreenSize"),
												rs.getString("Color"),
												rs.getString("OperatingSystem"));
				
				lap.add(myLap);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lap;
	}
}
