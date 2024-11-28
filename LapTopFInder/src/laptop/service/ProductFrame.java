package laptop.service;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.CellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductFrame implements ActionListener {
	private static JLabel commentLabel;
//	private static JTextField userText;
	private static String laptopID;
	private static JTextArea commentText;
	private static JComboBox rateBox;
	private static String myUserName;
	private static JFrame frame;
	
	public ProductFrame(ArrayList<String> laptop, String myUserName) {
		this.laptopID = laptop.get(10);
		this.myUserName = myUserName;
		System.out.println("Laptop ID: "+this.laptopID);
		
		int commentCount = 5;
		
		// FRAME
		frame = new JFrame();
		frame.setSize(1000,500);
		frame.setTitle("Laptop Buddy");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		
		// PANEL
		JPanel panel = new JPanel();
		int top = 0;
		int bottom = 0;
		int left = 1000;
		int right = 500;
		panel.setBounds(0,0,1000,500);
		panel.setLayout(null);
		//new GridLayout(0,1)
		
		
		
		int x = 10;
		int y = -20;
		int width = 80;
		int height = 25;
		
		int k = 160;
		
		//COMMENT
		//		Label
	    commentLabel = new JLabel("Comment");
		commentLabel.setBounds(x+30,y+270-k,width,height);
		panel.add(commentLabel);
		//		Text field
		commentText = new JTextArea();
		commentText.setBounds(x+30,y+300-k,width+300,height+100);
		commentText.setLineWrap(true);
		commentText.setWrapStyleWord(true);
		commentText.setEditable(true);
		panel.add(commentText);
		
		//SAVE BUTTON
		JButton save = new JButton("Save");
		save.setBounds(x+415,y+300-k,80,30);
		save.addActionListener(this);
		panel.add(save);
			
		//WISHLIST BUTTON
		JButton back = new JButton("Back");
		back.setBounds(x+415,y+300-k+50,80,30);
		back.setBackground(Color.WHITE);
		back.addActionListener(new GoBackListener(frame));
		panel.add(back);
		
		//BACK BUTTON
		

		//RATE LABEL
		JLabel rateLabel = new JLabel("Rate:");
		//rateLabel.setFont(new Font("Tahoma",Font.PLAIN,19));;
		rateLabel.setBounds(x+30,y+180-k,width,height);
		panel.add(rateLabel);
		
 		//RATING BUTTON
//		for(int i=0;i<5;i++) {
//			String rate =String.valueOf(i+1);
//			JButton star = new JButton(rate);
//			star.setBounds(x+30+i*50,y+220-k,40,25);
//			star.setBackground(Color.YELLOW);
//			panel.add(star);
//		}
		String[] rateNum = {"1","2","3","4","5"};
		rateBox = new JComboBox(rateNum);
		rateBox.setBounds(x+30,y+220-k,40,30);
		panel.add(rateBox);
		
		//LAPTOP DETAIL
		String[] col = {"Name: ","Brand: ","CPU: ","GPU: ","RAM: ",
				"Storage: ","Screen size: ","Color: ","OS: ","Seller: ","Price: "};
		String attr = new String();
		for(int i=0;i<col.length;i++) {
			if(i==10) {
				attr = col[i]+laptop.get(i+1);
			}else {
				attr = col[i] +laptop.get(i);
			}	
			JLabel prop = new JLabel(attr);
			prop.setBounds(x+500,y+25+i*40,width+350,height+20);
			
			prop.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
			panel.add(prop);
			
		}
		// CONNECT
		ArrayList<String> customer = new ArrayList<String>();
		ArrayList<String> customerComment = new ArrayList<String>();
		
		String serverName="titan.csse.rose-hulman.edu";
		String databaseName="LaptopOnly10";
		String user ="ledq";
		String pass ="Password123";
		DatabaseConnectionService dbsv = new DatabaseConnectionService(serverName, databaseName);
		dbsv.connect(user, pass);
		Connection con =dbsv.getConnection();
		String txt = new String();
		try(CallableStatement cs = con.prepareCall("{call getComment(?)}")){
			cs.setInt(1, Integer.parseInt(this.laptopID));
			cs.execute();
			ResultSet rs = cs.getResultSet();
			while(rs.next()) {
				
				//System.out.println(rs.getString(1));
				
				txt+= rs.getString(1)+": "+rs.getString(2)+"\n"
				+"--------------------------------------------------------------------"+"\n"
				+"----------------------------------------------------"+" Star: "+rs.getString(3)+"\n"+"\n"+"\n";
			}
			System.out.println(txt);
			dbsv.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		// COMMENT SECTION

		JTextArea cmt = new JTextArea();
		cmt.setLineWrap(true);
		cmt.setWrapStyleWord(true);
		cmt.setText(txt);
		cmt.setEditable(false);
		JScrollPane scroll = new JScrollPane(cmt,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBounds(40,270,width+350,height+150);
		//scroll.setBackground(Color.RED);
		
		for(int i=0;i<commentCount;i++) {
			
		}
		panel.add(scroll);
//		
			
		
		frame.add(panel, BorderLayout.CENTER);
		
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		ProductFrame wow = new ProductFrame(null, null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comment = this.commentText.getText();
		String rating = (String) this.rateBox.getSelectedItem();
		
		String serverName="titan.csse.rose-hulman.edu";
		String databaseName="LaptopOnly10";
		String user ="ledq";
		String pass ="Password123";
		DatabaseConnectionService dbsv = new DatabaseConnectionService(serverName, databaseName);
		dbsv.connect(user, pass);
		Connection con =dbsv.getConnection();
		try(CallableStatement cs = con.prepareCall("{call addComment(?,?,?,?)}")){
			cs.setInt(1,Integer.parseInt(laptopID));
			cs.setString(2,comment);
			cs.setInt(3,Integer.parseInt(rating));
			cs.setInt(4, Integer.parseInt(myUserName));
			cs.execute();
			System.out.println("Saved comment");
			dbsv.closeConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
