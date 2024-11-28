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

public class GUI implements ActionListener {
	
	private static JLabel userLabel;
	private static JTextField userText;
	private static JLabel passwordLabel;
	private static JPasswordField passwordText;
	private static JLabel notification;
	private static JFrame frame;
	private static DatabaseConnectionService dbsv;
	
	public static void main(String[] args) {
		
		
//		String serverName="titan.csse.rose-hulman.edu";
//		String databaseName="LaptopOnly10";
//		String user ="ledq";
//		String pass ="Password123";
//		dbsv = new DatabaseConnectionService(serverName, databaseName);
//		dbsv.connect(user, pass);
		
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
		
		
		//LOGIN BUTTON
		JButton button = new JButton("Login");
		button.setBounds(x+90,80,80,25);
		button.addActionListener(new GUI());
		panel.add(button);
		
		//Register
		JButton reg = new JButton("Sign up");
		reg.addActionListener(new registerListener(new GUI()));
		reg.setBounds(x+200,80,100,25);
		
		panel.add(reg);
		
		
		//Login result
		notification = new JLabel("");
		notification.setBounds(x,y+90,300,25);
		panel.add(notification);
		
		
		// FRAME
			frame = new JFrame();
		frame.setSize(500,500);
		frame.setTitle("Laptop Buddy");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel, BorderLayout.CENTER);
		
		
		
		//frame.pack();
		frame.setVisible(true);
		
		
	}
	public void close() {
		frame.dispose();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String username = this.userText.getText();
		char[] pswd = passwordText.getPassword();
		String password = String.valueOf(pswd);
		
		System.out.println(username);
		System.out.println(password);
		String serverName="titan.csse.rose-hulman.edu";
		String databaseName="MyLaptop";
		String user1 ="ledq";
		String pass ="Password123";
		dbsv = new DatabaseConnectionService(serverName, databaseName);
		dbsv.connect(user1, pass);
		Connection con =dbsv.getConnection();
		try(CallableStatement cs = con.prepareCall("{call login(?,?,?)}")){
			cs.setString(1, username);
			cs.setString(2, password);
			cs.registerOutParameter(3, java.sql.Types.INTEGER);
			if(cs.execute()) {
				
				ResultSet rs = cs.getResultSet();
				if(rs.next()) {
					notification.setText("Login successfully");
					String myUserName = rs.getString(1);
					frame.dispose();
					new SearchLap(myUserName);
				}else {
					notification.setText("Invalid password or username");
				}
				
			}else {
				notification.setText("Invalid password or username");
				System.out.println("Invalid password or username");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
