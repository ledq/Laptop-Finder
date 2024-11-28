package laptop.service;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ColumnButton implements ListSelectionListener {
	private JTable table;
	private String username;
	private ArrayList<String> row;
	public ColumnButton(JTable table,String username) {
		// TODO Auto-generated constructor stub
		this.table = table;
		this.username = username;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
		 if(!e.getValueIsAdjusting()){
			 row = new ArrayList<String>();
			 for (int i =0; i<12;i++) {
				 row.add(table.getValueAt(table.getSelectedRow(), i).toString());
				 
			 }
			 
			 String str = new String();
			 for (int i = 0; i<12;i++) {
				 str+="___"+ row.get(i);
			 }
			 System.out.println(str);
			 
			 ProductFrame product = new ProductFrame(row,username);
			 
         }

	}

}
