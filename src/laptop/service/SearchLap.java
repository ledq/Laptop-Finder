package laptop.service;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SpringLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Statement;

public class SearchLap implements ActionListener {
	
	
	private static JComboBox[] comboBox;
	private static String[] attr;
	private static ArrayList<ArrayList<String>> laptopRow;
	private static String[][] data;
	
	private static JPanel p2;
	//private DatabaseConnectionService dbsv;
    private int deter;
	private JFrame frame;	
	
	private String myUserName;
	
	public SearchLap(String myUserName) {
		this.myUserName = myUserName;
		attr = new String[8];
		laptopRow = new ArrayList<ArrayList<String>>();
		deter =0;
		
		//FRAME
		frame = new JFrame();
		frame.setSize(1000,500);
		frame.setTitle("Laptop Buddy");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
	
		
		//PANEL
		JPanel p1 = new JPanel();
		p1.setBounds(0,0,1000,50);
		//panel0.setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
		
		p1.setBackground(Color.BLUE);
		//panel0.setBounds(0,0,1000,500);
		p1.setLayout(new FlowLayout());
		
		
		p2 = new JPanel();
		p2.setBounds(0,50,1000,450);
		p2.setLayout(null);
		//panel1.setBackground(Color.RED);
		
		// RESET BUTTON
		JButton reset = new JButton ("Reset");
		p1.add(reset);
		reset.addActionListener(new ResetListener(this));
		
		// SEARCH BUTTON
		JButton search = new JButton("Search");
		p1.add(search);
		
		
		
		
		//Combo box
		comboBox = new JComboBox[8];
		
		String[] brand = {"0","Dell","ASUS","HP","Acer","Lenovo","Apple"};
		comboBox[0]= new JComboBox(brand);
		
		
		String[] cpu = {"0","11th Gen Intel® Core™ i3-1115G4","Celeron","13?? Gen Intel® Core™ i5-1335U","AMD Ryzen 3 3000 Series","Core I7 11800H","Intel Core i5"};
		comboBox[1] = new JComboBox(cpu);
		
		String[] gpu = {"0","Intel® UHD Graphics","Intel Integrated Gra","Intel HD Graphics 50","NVIDIA GeForce RTX 3","Intel HD Graphics 40","Intel® HD Graphics 6"};
		comboBox[2]= new JComboBox(gpu);
		
		String[] RAM = {"0","4","8","16","32"};
		comboBox[3]= new JComboBox(RAM);
		
		String[] storage = {"0","16","128","256","1024"};
		comboBox[4]= new JComboBox(storage);
		
		String[] screensize = {"0","11.6","13.3","14","15.6","17.3"};
		comboBox[5]= new JComboBox(screensize);
		
		String[] color = {"0","Black","White"};
		comboBox[6]= new JComboBox(color);
		
		String[] os = {"0","Windows 11","Chrome OS","Mac OS"};
		comboBox[7]= new JComboBox(os);
		
		for (int i=0;i<8;i++) {
			p1.add(comboBox[i]);
			
		}
		
//		JLabel result = new JLabel("Result");
//		
		search.addActionListener(this);
		
//		panel1.add(result);
//		JLabel result1 = new JLabel("Result");
//		
//		panel1.add(result1);
//		JLabel result2 = new JLabel("Result");
//		
//		panel1.add(result2);
//		
		//TABLE
//		dbsv.connect(user, pass);
//		
		
		
		
		
		
		
		frame.add(p1);
		frame.add(p2);
		//frame.getContentPane().add(scroll);
		
		
		
		
		frame.setVisible(true);
	}

//	public String getSQL() {
//		String sql = "exec searchLaptop null";
//		for(int i=0;i<8;i++) {
//			sql+=","+""'"+attr[i];
//		}
//		sql+=",null,null";
//		System.out.println(sql);
//		return sql;
//		
//	}
	
	public void getProc() {
		
		
		String serverName="titan.csse.rose-hulman.edu";
		String databaseName="MyLaptop";
		String user ="ledq";
		String pass ="Password123";
		DatabaseConnectionService dbsv = new DatabaseConnectionService(serverName, databaseName);
		dbsv.connect(user, pass);
		Connection con =dbsv.getConnection();
		try(CallableStatement cs = con.prepareCall("{call searchLaptop(?,?,?,?,?,?,?,?,?,?,?,?)}")){
			cs.setNull(1, java.sql.Types.VARCHAR);
			if(attr[0].equals("0")) {cs.setNull(2,java.sql.Types.VARCHAR);} else {cs.setString(2, String.valueOf(attr[0]));}
			if(attr[1].equals("0")) {cs.setNull(3,java.sql.Types.VARCHAR);} else {cs.setString(3, String.valueOf(attr[1]));}
			if(attr[2].equals("0")) {cs.setNull(4,java.sql.Types.VARCHAR);} else {cs.setString(4, String.valueOf(attr[2]));}
			if(attr[3].equals("0")) {cs.setNull(5,java.sql.Types.VARCHAR);} else {cs.setInt(5, Integer.parseInt(attr[3]));}
			if(attr[4].equals("0")) {cs.setNull(6,java.sql.Types.VARCHAR);}else{cs.setInt(6, Integer.parseInt(attr[4]));}
			if(attr[5].equals("0")) {cs.setNull(7,java.sql.Types.VARCHAR);}else{cs.setFloat(7, Float.parseFloat(attr[5]));}
			if(attr[6].equals("0")) {cs.setNull(8,java.sql.Types.VARCHAR);}else{cs.setString(8, String.valueOf(attr[6]));}
			if(attr[7].equals("0")) {cs.setNull(9,java.sql.Types.VARCHAR);}else{cs.setString(9, String.valueOf(attr[7]));}
			cs.setNull(10, java.sql.Types.INTEGER);
			cs.setNull(11, java.sql.Types.INTEGER);
			cs.registerOutParameter(12, java.sql.Types.INTEGER);
			cs.execute();
			ResultSet rs = cs.getResultSet();
			while(rs.next()) {
				
				ArrayList<String> laprow = new ArrayList<String>();
				String test = new String();
				for(int i =0;i<12;i++) {
					
					laprow.add(rs.getString(i+2));
					test+= "___"+ rs.getString(i+2);
					
				}
				System.out.println(test);
				
				laptopRow.add(laprow);
			}
			updateTable();
			dbsv.closeConnection();
			
		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(deter==0) {
			deter=1;
			for(int i=0;i<8;i++) {
				attr[i]=(String) comboBox[i].getSelectedItem();
				System.out.println(attr[i]);
				
			}
			getProc();	
			
		}	
	}
	
	public String[][] dataConversion(ArrayList<ArrayList<String>> lap){
		int size = lap.size();
		int sizecol = lap.get(0).size();
		String[][] data = new String[size][sizecol];
		for (int i=0; i< size;i++) {
			for(int j=0;j<sizecol;j++) {
				data[i][j]=lap.get(i).get(j);
				
			}
		}
		return data;
	}
	
	public void updateTable() {
		data = dataConversion(laptopRow);

		Object[] col = {"Name","Brand","CPU","GPU","RAM","Storage","Screen size","Color","OS","Seller","LID","Price"};
		JTable table = new JTable();
		DefaultTableModel model = new DefaultTableModel(data,col);
		model.setColumnIdentifiers(col);
		table.setModel(model);
		table.getSelectionModel().addListSelectionListener(new ColumnButton(table,this.myUserName));
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(0,0,1000,400);
		p2.add(scroll);
		
	}
	
	public String myUserName() {
		return this.myUserName;
	}
	
	public void close() {
		frame.dispose();
		
	}
	
	
	public static void main(String[] args) {
		SearchLap wow = new SearchLap(null);
	}
	
	

	
}
