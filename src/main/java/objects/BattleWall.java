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

public class BattleWall extends GameObject{

	public BattleWall(Coordinate pPos, int pHealth, int pMaxHealth, String pName, int pAttack, String oType,
			MainGUI pParent, int movementSpeed, int pActingSpeed) {
		super(pPos, pHealth, pMaxHealth, pName, pAttack, oType, pParent, movementSpeed, pActingSpeed);
		// TODO Auto-generated constructor stu
		setInvincible(true);
	}

	@Override
	public void loadNormal() {
		// TODO Auto-generated method stub
		ArrayList<Texture> good = new ArrayList<Texture>();
		try {
			good.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/MapObjects/BattleWall/good.png")), "good", Color.black));
			good.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/MapObjects/BattleWall/goodB.png")), "goodB", Color.black));
			normalLook[0] = new Texture('N', good, "battleWallGood", Color.pink, 1000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Texture> medium = new ArrayList<Texture>();
		try {
			medium.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/MapObjects/BattleWall/medium.png")), "medium", Color.black));
			medium.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/MapObjects/BattleWall/mediumB.png")), "mediumB", Color.black));
			normalLook[1] = new Texture('N', medium, "battleWallMedium", Color.pink, 1000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Texture> low = new ArrayList<Texture>();
		try {
			low.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/MapObjects/BattleWall/low.png")), "low", Color.black));
			low.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/MapObjects/BattleWall/lowB.png")), "lowB", Color.black));
			normalLook[2] = new Texture('N', low, "battleWallLow", Color.pink, 1000);
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
		if(action.equals("playerChangedHealth")){
			if(parent.getPlayer().healthPercentage() > 60){
				turn('d');
			}
			else if(parent.getPlayer().healthPercentage() > 15){
				turn('u');
			}
			else{
				turn('l');
			}
		}
	}

}
