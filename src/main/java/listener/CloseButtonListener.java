package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CloseButtonListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent ae) {
		System.out.println("SHUTTING DOWN");
		System.exit(0);
		
	}

}
