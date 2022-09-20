package listener;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JWindow;

import gui.MainGUI;
import other.FileReader;

public class MainWindowListener implements KeyListener{
	JFrame window;
	MainGUI parent;
	public String debugEnter;
	//public boolean isDebug;
	public MainWindowListener(MainGUI pParent){
		parent = pParent;
		debugEnter = "";
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		if (debugEnter.length() < 6) {
			debugEnter = debugEnter + ke.getKeyChar();
			System.out.println(debugEnter);
			if(debugEnter.equals("debug")){
				if (!parent.debugMode) {
					System.out.println("Debug mode AKTIVIERT");
					//isDebug = true;
					parent.getPlayer().setInvincible(true);
					parent.debugMode = true;
				}
			}
		}
		
		
		//SPACE PRESSED
		if(ke.getKeyCode() == KeyEvent.VK_SPACE){
				parent.getWorld().tickAction();
				parent.getPlayer().attack();
			if(parent.getWorld().getObjectFromListByPos(parent.getPlayer().looksAt()) != null){
				parent.getWorld().getObjectFromListByPos(parent.getPlayer().looksAt()).tickAction("SPACE");
			}
		}
		//MOVEMENT
		if(!parent.getPlayer().isFrozen()){//Turning, when not frozen
			if(ke.getKeyCode() == KeyEvent.VK_W || ke.getKeyCode() == KeyEvent.VK_UP){
				parent.getPlayer().turn('u');
			}
			else if(ke.getKeyCode() == KeyEvent.VK_S || ke.getKeyCode() == KeyEvent.VK_DOWN){
				parent.getPlayer().turn('d');
			}
			else if(ke.getKeyCode() == KeyEvent.VK_A || ke.getKeyCode() == KeyEvent.VK_LEFT){
				parent.getPlayer().turn('l');
			}
			else if (ke.getKeyCode() == KeyEvent.VK_D || ke.getKeyCode() == KeyEvent.VK_RIGHT){
				parent.getPlayer().turn('r');
			}
		}
		if(!parent.getPlayer().isMoving() && !parent.getPlayer().isTalking() && !parent.getPlayer().isFrozen()){
			int x = parent.getPlayer().getPos().getX();
			int y = parent.getPlayer().getPos().getY();
			try {
				if (ke.getKeyCode() == KeyEvent.VK_W || ke.getKeyCode() == KeyEvent.VK_UP) {
					if ((parent.getMapArray()[x][y - 1] != 'N' && parent.getMapArray()[x][y - 1] != '#' && parent.getWorld().legalMove(x, y - 1)) || parent.debugMode) {
						parent.getPlayer().movePos(0, -1);
						if(!parent.getSoundPlayer().isPlaying()){
							parent.playStep();
						}
					}
				} else if (ke.getKeyCode() == KeyEvent.VK_S || ke.getKeyCode() == KeyEvent.VK_DOWN) {
					if ((parent.getMapArray()[x][y + 1] != 'N' && parent.getMapArray()[x][y + 1] != '#' && parent.getWorld().legalMove(x, y + 1)) || parent.debugMode) {
						parent.getPlayer().movePos(0, 1);
						if(!parent.getSoundPlayer().isPlaying()){
							parent.playStep();
						}
					}
				} else if (ke.getKeyCode() == KeyEvent.VK_A || ke.getKeyCode() == KeyEvent.VK_LEFT) {
					if ((parent.getMapArray()[x-1][y] != 'N' && parent.getMapArray()[x - 1][y] != '#' && parent.getWorld().legalMove(x - 1, y)) || parent.debugMode) {
						parent.getPlayer().movePos(-1, 0);
						if(!parent.getSoundPlayer().isPlaying()){
							parent.playStep();
						}
					}
				} else if(ke.getKeyCode() == KeyEvent.VK_D || ke.getKeyCode() == KeyEvent.VK_RIGHT){
					if ((parent.getMapArray()[x+1][y] != 'N' && parent.getMapArray()[x + 1][y] != '#' && parent.getWorld().legalMove(x + 1, y)) || parent.debugMode) {
						parent.getPlayer().movePos(1, 0);
						if(!parent.getSoundPlayer().isPlaying()){
							parent.playStep();
						}
					}
				} 
			} catch (Exception e) {
				System.out.println("Spieler ist außerhalb der Karte! "+"X: "+x+" - Y: "+y);
			}
		}
		if(ke.getKeyCode() == KeyEvent.VK_SHIFT){
			if(parent.getPlayer().healthPercentage() <= 15){
				parent.getPlayer().setSpeed(8);
			}
			else{
				parent.getPlayer().setSpeed(2);
			}
		}
		
		parent.getWorld().keyPressed(ke);
		if(ke.getKeyCode() == KeyEvent.VK_X){
			parent.getSoundPlayer().play("equip");
			parent.changeListener(new SmartphoneListener(parent));
			//System.out.println("HI");
		}
		//parent.getDebug().refresh();
	}

	@Override
	public void keyReleased(KeyEvent ke) {
		// TODO Auto-generated method stub
		if(ke.getKeyCode() == KeyEvent.VK_SHIFT){
			if(parent.getPlayer().healthPercentage() <= 15){
				parent.getPlayer().setSpeed(4);
			}
			else{
				parent.getPlayer().setSpeed(1);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

}
