package laptop.service;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class registerListener implements ActionListener {
	private GUI gui;
	public registerListener(GUI gui) {
		// TODO Auto-generated constructor stub
		this.gui = gui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.gui.close();
		Register.main(null);
		
		
	}

}
