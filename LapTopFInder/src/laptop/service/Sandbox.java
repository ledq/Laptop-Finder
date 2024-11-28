package laptop.service;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Sandbox {
	public static void main(String[] args) {
		
		//FRAME
		JFrame frame = new JFrame();
		frame.setSize(1000,500);
		frame.setTitle("Laptop Buddy");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		
		//TABLE
		Object[] col = {"Name","Brand","CPU","Screen size","GPU","Storage","RAM","Price","Operating system"};
		JTable table = new JTable();
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(col);
		table.setModel(model);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(0,0,500,250);
		
		//PANEL
		JPanel p1 = new JPanel();
		p1.setBounds(0,0,500,250);
		p1.setBackground(Color.RED);
		p1.setLayout(null);
		p1.add(scroll);
		
		
		
		frame.add(p1);
		frame.setVisible(true);
		
		
		
		
		
		String serverName="titan.csse.rose-hulman.edu";
		String databaseName="LaptopOnly10";
		String user ="ledq";
		String pass ="Password123";
		DatabaseConnectionService dbsv = new DatabaseConnectionService(serverName, databaseName);
		dbsv.connect(user, pass);
		Connection con =dbsv.getConnection();
		try(CallableStatement cs = con.prepareCall("{call searchLaptop(?,?,?,?,?,?,?,?,?,?,?,?)}")){
			cs.setNull(1, java.sql.Types.VARCHAR);
			cs.setString(2, "Dell");
			cs.setNull(3, java.sql.Types.VARCHAR);
			cs.setNull(4, java.sql.Types.VARCHAR);
			cs.setNull(5, java.sql.Types.INTEGER);
			cs.setNull(6, java.sql.Types.INTEGER);
			cs.setNull(7, java.sql.Types.FLOAT);
			cs.setNull(8, java.sql.Types.VARCHAR);
			cs.setNull(9, java.sql.Types.VARCHAR);
			cs.setNull(10, java.sql.Types.INTEGER);
			cs.setNull(11, java.sql.Types.INTEGER);
			cs.registerOutParameter(12, java.sql.Types.INTEGER);
			cs.execute();
			ResultSet rs = cs.getResultSet();
			while(rs.next()) {
				System.out.println(rs.getString(2)+" "+rs.getString(3));
			}
		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
