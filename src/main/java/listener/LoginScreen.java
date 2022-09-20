package listener;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import gui.MainGUI;
import other.FileReader;

public class LoginScreen implements KeyListener{
	
	private String name;

	MainGUI parent;
	public LoginScreen(MainGUI pParent){
		parent = pParent;
		name = System.getProperty("user.name");
		update();
	}
	
	private void update(){
		if (!parent.getPool().online) {
			try {
				parent.setOverPic(ImageIO.read(new File(FileReader.path() + "NewGeeemu/Pictures/OverPic/login.png")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Graphics text = parent.getOverPic().getGraphics();
			text.setColor(Color.black);
			text.setFont(new Font(Font.DIALOG, Font.BOLD, 25));
			text.drawString(name, 276, 500);
			//Erlaubte Zeichen
			text.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
			text.drawString(name.length() + " von 21 erlaubten Zeichen", 276, 525);
		}
	}
	
	@Override
	public void keyPressed(KeyEvent ke) {
		if (parent != null && !parent.getPool().online) {
			// TODO Auto-generated method stub
			if (ke.getKeyCode() == KeyEvent.VK_ENTER && name.equals("")) {
				if (!parent.getSoundPlayer().isPlaying()) {
					parent.getSoundPlayer().play("warn");
				}
			} else if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
				parent.getPlayer().setName(name);
				if(parent.getPlayer().getName().equals("ADMIN")){
					parent.debugMode = true;
					System.out.println("DEBUG MODE ACIVATED");
				}
				parent.tick("WORLDTICKlogin");
				try {
					parent.setOverPic(
							ImageIO.read(new File(FileReader.path() + "NewGeeemu/Pictures/OverPic/loginSuccess.png")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Graphics text = parent.getOverPic().getGraphics();
				text.setColor(Color.black);
				text.setFont(new Font(Font.DIALOG, Font.BOLD, 40));
				if(name.toLowerCase().contains("nietzsche")){
					text.drawString("Chaos, CHAOS", 150, 450);
				}
				else{
					text.drawString("Willkommen, "+name+"!", 150, 450);
				}
				parent.getPool().online = true;
				parent = null;
			} else if (ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
				if (!name.equals("")) {
					name = name.substring(0, name.length() - 1);
				}
				update();
			} else if (ke.getKeyCode() == KeyEvent.VK_F2) {
				parent.setOverPic(null);
				parent.normalListener();
				parent = null;
			} else if (ke.getKeyCode() != KeyEvent.VK_SHIFT && ke.getKeyCode() != KeyEvent.VK_CONTROL
					&& ke.getKeyCode() != KeyEvent.VK_ALT && ke.getKeyCode() != KeyEvent.VK_F1) {
				if (name.length() != 21) {
					name = name + ke.getKeyChar();
				} else {
					if (!parent.getSoundPlayer().isPlaying()) {
						parent.getSoundPlayer().play("warn");
					}
				}
				update();
			} 
		}
	}

	@Override
	public void keyReleased(KeyEvent ke) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent ke) {
		// TODO Auto-generated method stub
		
	}

}
