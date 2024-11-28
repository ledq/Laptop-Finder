package laptop.service;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetListener implements ActionListener {
	private SearchLap lap;
	public ResetListener(SearchLap cur) {
		// TODO Auto-generated constructor stub
		this.lap = cur;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String username = lap.myUserName();
		lap.close();
		SearchLap wow = new SearchLap(username);
		
	}

}
