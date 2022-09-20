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

public class OrangeMan extends GameObject{

	public OrangeMan(Coordinate pPos, int pHealth, int pMaxHealth, String pName, int pAttack, String oType,
			MainGUI pParent, int movementSpeed, int pActingSpeed) {
		super(pPos, pHealth, pMaxHealth, pName, pAttack, oType, pParent, movementSpeed, pActingSpeed);
		// TODO Auto-generated constructor stub
		extraHeight = 80;
		walkSound = "orangeManMove";
		if(getType().equals("ENEMY")){
			act();
		}
	}

	@Override
	public void loadNormal() {
		// TODO Auto-generated method stub
		ArrayList<Texture> animate = new ArrayList<Texture>();
		try {
			animate.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/NPC/OrangeMan/rightFront.png")), "Front", Color.black));
			animate.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/NPC/OrangeMan/rightFrontB.png")), "FrontB", Color.black));
			normalLook[0] = new Texture('N', animate, "animationOrangeRight", Color.pink, 500);
			normalLook[3] = normalLook[0];
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Texture> animateLeft = new ArrayList<Texture>();
		try {
			animateLeft.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/NPC/OrangeMan/leftFront.png")), "left", Color.black));
			animateLeft.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/NPC/OrangeMan/leftFrontB.png")), "left", Color.black));
			normalLook[2] = new Texture('N', animateLeft, "animationOrangeLeft", Color.pink, 500);
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
		
	}

}
