package listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import gui.MainGUI;
import other.FileReader;


public class StartScreenListener implements KeyListener{

	BufferedImage picA;
	BufferedImage picB;
	BufferedImage picC;
	MainGUI parent;
	char screen = 'a';//Determines wether a or be should be selected
	
	public StartScreenListener(MainGUI pParent){
		parent = pParent;
		parent.getMusicPlayer().playLooped("startScreen");
		try {
			picA = ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/startScreen.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			picB = ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/startScreen2.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			picC = ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/startScreen3.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent key) {
		if(key.getKeyCode() == KeyEvent.VK_ESCAPE){
			System.out.println("STOPPING GAME");
			parent.getMusicPlayer().stopMusic();
			System.exit(0);
		}
		else{
			parent.timer.stop();
			if(screen != 'c'){
				screen = 'c';
				parent.label.setIcon(new ImageIcon(picC));
			}
			else{
				parent.getMusicPlayer().stopMusic();
				parent.startGame();
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void tick(){
		if(screen == 'a'){
			parent.label.setIcon(new ImageIcon(picA));
			screen = 'b';
		}
		else{
			parent.label.setIcon(new ImageIcon(picB));
			screen = 'a';
		}
	}

}
