package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextPane;

import gui.MainGUI;

public class DebugButtonListener implements ActionListener{

	MainGUI parent;
	public DebugButtonListener(MainGUI pParent){
		parent = pParent;
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		JFrame window = new JFrame("DEBUG");
		window.setSize(200, 200);
		JTextPane text = new JTextPane();
		text.setEditable(false);
		window.add(text);
		window.setVisible(true);
		
		text.setText(refresh());
	}
	public String refresh(){
		String content;
		content = "PlayerPos: "+parent.getPlayer().getPos().stringCoordinate()+"\n";
		content = content+"PlayerName: "+parent.getPlayer().getName()+"\n";
		content = content+"PlayerHealth: "+parent.getPlayer().getHealth()+"\n";
		content = content+"Loaded Map: "+parent.getMapName()+"\n";
		
		return content;
	}
}
