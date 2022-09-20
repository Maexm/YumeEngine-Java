package objects;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import erstesSpiel.Coordinate;
import gui.MainGUI;
import other.FileReader;
import rendering.Texture;

public class FirstEncounter extends GameObject{
	
	

	public FirstEncounter(Coordinate pPos, int pHealth, int pMaxHealth, String pName, int pAttack, String oType,
			MainGUI pParent, int movementSpeed, int pActingSpeed) {
		super(pPos, pHealth, pMaxHealth, pName, pAttack, oType, pParent, movementSpeed, pActingSpeed);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void loadNormal() {
		// TODO Auto-generated method stub
		ArrayList<Texture> animate = new ArrayList<Texture>();
		try {
			animate.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/NPC/FirstEncounter/a.png")), "animationEncounter1", Color.pink));
			animate.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/NPC/FirstEncounter/b.png")), "animationEncounter2", Color.pink));
			animate.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/NPC/FirstEncounter/c.png")), "animationEncounter3", Color.pink));
			animate.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/NPC/FirstEncounter/b.png")), "animationEncounter4", Color.pink));
			normalLook[0] = new Texture('N', animate, "animationEncounter", Color.pink, 500);
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
		
		
	}

}
