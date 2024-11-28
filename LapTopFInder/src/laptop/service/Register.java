package laptop.service;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

//import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Register implements ActionListener {
	
	private static JLabel userLabel;
	private static JTextField userText;
	private static JLabel passwordLabel;
	private static JPasswordField passwordText;
	private static JLabel notification;
	private static JFrame frame;
	private static DatabaseConnectionService dbsv;
	
	
	
	public static void main(String[] args) {
		
		
		
		
		// PANEL
		JPanel panel = new JPanel();
		int top = 300;
		int bottom = 300;
		int left = 100;
		int right = 300;
		panel.setBorder(BorderFactory.createEmptyBorder(top, bottom, left, right));
		panel.setLayout(null);
		//new GridLayout(0,1)
		
		
		int x = 10;
		int y = 20;
		int width = 80;
		int height = 25;
		
		//USERNAME
		//		Label
	    userLabel = new JLabel("Username");
		userLabel.setBounds(x,y,width,height);
		panel.add(userLabel);
		//		Text field
		userText = new JTextField(20);
		userText.setBounds(x+90,y,width+85,height);
		panel.add(userText);
		
		
		//PASSWORD
		//		label
		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(x,y+30,width,height);
		panel.add(passwordLabel);
		//		Text field
		passwordText = new JPasswordField();
		passwordText.setBounds(x+90, y+30, width+85,height);
		panel.add(passwordText);
		
		//Login result
		notification = new JLabel("");
		notification.setBounds(x,y+90,300,25);
		panel.add(notification);
		
		//Confirm BUTTON
		JButton button = new JButton("Confirm");
		button.setBounds(x+90,80,80,25);
		button.addActionListener(new Register());
		panel.add(button);
		
		
		
		
		// FRAME
			frame = new JFrame();
		frame.setSize(500,500);
		frame.setTitle("Laptop Buddy");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel, BorderLayout.CENTER);
		
		
		
		//frame.pack();
		frame.setVisible(true);
		
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String user = userText.getText();
		char[] pswd = passwordText.getPassword();
		String password = String.valueOf(pswd);
		System.out.println(user);
		System.out.println(password);
		
		//success.setText("Register successfully");
		String serverName="titan.csse.rose-hulman.edu";
		String databaseName="MyLaptop";
		String user1 ="ledq";
		String pass ="Password123";
		dbsv = new DatabaseConnectionService(serverName, databaseName);
		dbsv.connect(user1, pass);
		Connection con =dbsv.getConnection();
		try(CallableStatement cstmt = con.prepareCall("{call createAccount(?, ?)}")){
			cstmt.setString(1, user);
			cstmt.setString(2,password);
			cstmt.execute();
				System.out.println("user added");
				notification.setText("Sign up successfully");
			
			dbsv.closeConnection();
		}catch (Exception e2) {
			e2.printStackTrace();
		}
		
		
		//frame.dispose();
			
			//SearchLap sl = new SearchLap(this.dbsv);
		
	}
}
