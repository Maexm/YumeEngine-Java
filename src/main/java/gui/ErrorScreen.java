package gui;

import javax.swing.JDialog;
import javax.swing.JLabel;


public class ErrorScreen {

	public static void open(String message, String title){
		JDialog window = new JDialog();
		if(title != ""){
			window.setTitle(title);
		}
		else{
			window.setTitle("ERROR");
		}
		window.setSize(400, 200);
		window.setModal(true);
		JLabel text = new JLabel(message);
		window.add(text);
		window.setVisible(true);
	}
	
}
