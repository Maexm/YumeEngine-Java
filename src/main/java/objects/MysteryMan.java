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

public class MysteryMan extends GameObject{

	public MysteryMan(Coordinate pPos, int pHealth, int pMaxHealth, String pName, int pAttack, String oType,
			MainGUI pParent, int movementSpeed, int pActingSpeed) {
		super(pPos, pHealth, pMaxHealth, pName, pAttack, oType, pParent, movementSpeed, pActingSpeed);
		// TODO Auto-generated constructor stub
		extraHeight = 15;
	}

	@Override
	public void loadNormal() {
		// TODO Auto-generated method stub
		ArrayList<Texture> animate = new ArrayList<Texture>();
		try {
			animate.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/NPC/MysteryMan/normal.png")), "Front", Color.black));
			animate.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/NPC/MysteryMan/normalb.png")), "Frontb", Color.black));
			animate.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/NPC/MysteryMan/normalc.png")), "Frontc", Color.black));
			animate.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/NPC/MysteryMan/normalb.png")), "Frontbb", Color.black));
			normalLook[0] = new Texture('N', animate, "animationMysteryMan", Color.pink, 1000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			normalLook[1] = new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/NPC/MysteryMan/talk.png")), "MysteryManCry", Color.pink);
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
			if (parent.getMapName().equals("Beach") || parent.getMapName().equals("MainArea")) {
				if (getDirection() == 'd') {
					turn('u');
					parent.getWorld().ownPlayer.stopMusic();
					parent.getWorld().ownPlayer.playLooped("scream");
					parent.getPlayer().setTalking(true);
					parent.setTextCustomMute("ÜBERTRAGUNG WIRD EMPFANGEN...", Color.red);
				} else {
					turn('d');
					parent.getWorld().ownPlayer.stopMusic();
					parent.getWorld().ownPlayer.playLooped("Beach", 460800);
					if(parent.getPlayer().getName().toLowerCase().contains("alina")){
						parent.setTextCustomMute("안녕하세요, 아리나!", Color.black);
					}
					else{
						parent.setTextCustomMute("+5 PESSIMISMUS#NEXT#-2 OPTIMISMUS", Color.red);
						parent.getPool().shiftOptimistic(-2);
						parent.getPool().shiftPessimistic(5);
					}
					parent.getPool().easterEggBeach = true;
					kill();
				} 
			}
		}
	}

}
