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

public class EasterEgg extends GameObject{

	public EasterEgg(Coordinate pPos, int pHealth, int pMaxHealth, String pName, int pAttack, String oType,
			MainGUI pParent, int movementSpeed, int pActingSpeed) {
		super(pPos, pHealth, pMaxHealth, pName, pAttack, oType, pParent, movementSpeed, pActingSpeed);
		// TODO Auto-generated constructor stub
		extraHeight = 18;
	}

	@Override
	public void loadNormal() {
		// TODO Auto-generated method stub
		ArrayList<Texture> txtNormal = new ArrayList<Texture>();
		try {
			txtNormal.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/NPC/EasterEgg/pic.png")), "normal", Color.black));
			txtNormal.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/NPC/EasterEgg/picB.png")), "normalB", Color.black));
			txtNormal.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/NPC/EasterEgg/pic.png")), "normal", Color.black));
			txtNormal.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/NPC/EasterEgg/picC.png")), "normalC", Color.black));
			normalLook[0] = new Texture('N', txtNormal, "easterEggPerson", Color.pink, 500);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			normalLook[1] = new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/NPC/EasterEgg/picD.png")), "easterEggPersonWarp", Color.pink);
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
			turn('u');
			for(int i= 0; i< parent.timeJobList.size(); i++){
				parent.timeJobList.get(i).fullStop();
			}
			parent.timeJobList.clear();
			cry.playTick("weird", "WORLDTICKSOUNDeaster");
		}
	}

}
