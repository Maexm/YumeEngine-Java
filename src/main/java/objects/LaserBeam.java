package objects;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import erstesSpiel.Coordinate;
import gui.MainGUI;
import other.FileReader;
import rendering.Texture;

public class LaserBeam extends GameObject{

	public LaserBeam(Coordinate pPos, int pHealth, int pMaxHealth, String pName, int pAttack, String oType,
			MainGUI pParent, int movementSpeed, int pActingSpeed) {
		super(pPos, pHealth, pMaxHealth, pName, pAttack, oType, pParent, movementSpeed, pActingSpeed);
		// TODO Auto-generated constructor stub
		setInvincible(true);
		deathSound = "NONE";
		if(getName().contains("Horizontal")){
			turn('l');
		}
	}

	@Override
	public void loadNormal() {
		// TODO Auto-generated method stub
		try {
			normalLook[2] = new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/MapObjects/LaserBeam/horizontal.png")), "LaserBeamHorizontal", Color.pink);
			normalLook[0] = new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/MapObjects/LaserBeam/vertical.png")), "LaserBeamVertical", Color.pink);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		attack(pos);
	}

	@Override
	public void additionalStop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tickAction(String action) {
		// TODO Auto-generated method stub
		
	}

}
