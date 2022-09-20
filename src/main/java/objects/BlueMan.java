package objects;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import erstesSpiel.Coordinate;
import gui.MainGUI;
import other.FileReader;
import rendering.Texture;


public class BlueMan extends GameObject{
	private int talk = 0;

	public BlueMan(Coordinate pPos, int pHealth, int pMaxHealth, String pName, int pAttack, String oType,
			MainGUI pParent, int movementSpeed, int pActingSpeed) {
		super(pPos, pHealth, pMaxHealth, pName, pAttack, oType, pParent, movementSpeed, pActingSpeed);
		// TODO Auto-generated constructor stub
		extraHeight = 40;
	}

	@Override
	public void loadNormal() {
		// TODO Auto-generated method stub
		try {
			normalLook[0] = new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/NPC/BlueMan/normal.png")), "blueMan", Color.pink);
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
			if (parent.getWorld().getFile().equals("EasterWorldA")) {
				if (talk == 0) {
					cry.play("cold");
					parent.getPlayer().setTalking(true);
					parent.setTextCustom("...#NEXT#S..s..so k...k...kalt...", Color.blue);
					talk++;
				}
				else if(talk == 1){
					parent.setTextCustom("Hüte dich vor meinem Bruder...#NEXT#U...u...und....", Color.blue);
					talk++;
				}
				else if(talk == 2){
					parent.setTextCustom("Erinner dich...an mich.#NEXT#B...b...b...bitte...", Color.blue);
					talk = 3;
				}
				else if(talk == 3){
					parent.setTextCustom("", Color.blue);
					parent.getPlayer().setTalking(false);
					parent.getPool().cold = true;
					talk = 0;
					deathSound = "coldFaint";
					kill();
				}
			}
		}
	}

}
