package listener;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gui.MainGUI;

public class Decision implements KeyListener{

	MainGUI parent;
	String ques;
	String ansA;
	String ansB;
	int select;
	public Decision(String pQues, String pAnsA, String pAnsB, MainGUI pParent){
		parent = pParent;
		if(pAnsA.equals("")){
			ansA = "???";
		}
		else{
			ansA = pAnsA;
		}
		if(pAnsB.equals("")){
			ansB = "???";
		}
		else{
			ansB = pAnsB;
		}
		if(pQues.equals("")){
			ques = "MISSING QUESTION";
		}
		else{
			ques = pQues;
		}
		select = 3;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			if(select == 0){
				parent.setTextCustom("", Color.black);
				parent.tick("WORLDTICKDECISION"+ansA);
				parent.normalListener();
				parent = null;
			}
			else if(select == 1){
				parent.setTextCustom("", Color.black);
				parent.tick("WORLDTICKDECISION"+ansB);
				parent.normalListener();
				parent = null;
			}
		}
		else if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT){
			select = 0;
			parent.setTextCustomMute(ques+"#NEXT#["+ansA+"]        "+ansB, Color.black);
			parent.getSoundPlayer().play("menu2");
		}
		else if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT){
			select = 1;
			parent.setTextCustomMute(ques+"#NEXT#"+ansA+"        ["+ansB+"]", Color.black);
			parent.getSoundPlayer().play("menu2");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
