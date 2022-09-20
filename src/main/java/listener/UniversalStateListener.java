package listener;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import gui.MainGUI;

public class UniversalStateListener implements WindowListener{
	
	MainGUI parent;
	public UniversalStateListener(MainGUI pParent){
		parent = pParent;
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {

		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("USER CLOSED WINDOW - STOPPING GAME");
		parent.stopGame();
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
