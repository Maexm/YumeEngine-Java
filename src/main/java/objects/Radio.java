package objects;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import erstesSpiel.Coordinate;
import gui.MainGUI;
import other.FileReader;
import rendering.Texture;

public class Radio extends GameObject{

	public Radio(Coordinate pPos, int pHealth, int pMaxHealth, String pName, int pAttack, String oType, MainGUI pParent,
			int movementSpeed, int pActingSpeed) {
		super(pPos, pHealth, pMaxHealth, pName, pAttack, oType, pParent, movementSpeed, pActingSpeed);
		// TODO Auto-generated constructor stub
		deathSound = "NONE";
	}

	@Override
	public void loadNormal() {
		// TODO Auto-generated method stub
		try {
			normalLook[0] = new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/MapObjects/Radio/idle.png")), "Radio", Color.pink);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<Texture> animate = new ArrayList<Texture>();
		try {
			animate.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/MapObjects/Radio/activeA.png")), "animationRadio1", Color.pink));
			animate.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/MapObjects/Radio/activeB.png")), "animationRadio2", Color.pink));
			animate.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/MapObjects/Radio/activeC.png")), "animationRadio3", Color.pink));
			animate.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/MapObjects/Radio/activeD.png")), "animationRadio4", Color.pink));
			animate.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/MapObjects/Radio/activeE.png")), "animationRadio5", Color.pink));
			animate.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/MapObjects/Radio/activeF.png")), "animationRadio6", Color.pink));
			animate.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/MapObjects/Radio/activeG.png")), "animationRadio7", Color.pink));
			normalLook[1] = new Texture('N', animate, "animationEncounter", Color.pink, 500);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void additionalStop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tickAction(String action) {
		// TODO Auto-generated method stub
		if(action.equals("SPACE")){
			if (parent.getMapName().equals("MainArea")) {
				if (getDirection() == 'd') {
					turn('u');
					parent.getMusicPlayer().playLooped("MainArea");
				} else {
					turn('d');
					parent.getMusicPlayer().stopMusic();
				} 
			}
			else if(parent.getMapName().equals("EasterWorldA")){
				if (getDirection() == 'd') {
					turn('u');
					parent.getWorld().ownPlayer.stopMusic();
					parent.getWorld().ownPlayer.playLooped("c");
				} else {
					turn('d');
					parent.getWorld().ownPlayer.stopMusic();
					parent.getWorld().ownPlayer.playLooped("easterEgg");
					parent.getPool().easterEggRoomRadio = true;
					kill();
				}
			}
			
		}
	}

}
