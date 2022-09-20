package objects;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import erstesSpiel.Coordinate;
import gui.MainGUI;
import other.FileReader;
import rendering.Texture;

public class AGBBook extends GameObject{
private int talk = 0;

	public AGBBook(Coordinate pPos, int pHealth, int pMaxHealth, String pName, int pAttack, String oType,
			MainGUI pParent, int movementSpeed, int pActingSpeed) {
		super(pPos, pHealth, pMaxHealth, pName, pAttack, oType, pParent, movementSpeed, pActingSpeed);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void loadNormal() {
		// TODO Auto-generated method stub
		deathSound = "NONE";
		try {
			normalLook[0] = new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/items/bookAGB.png")), "agbBook", Color.pink);
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
			if(parent.getWorld().getFile().equals("Beach")){
				switch(talk){
				case 0:
					parent.getPlayer().setTalking(true);
					parent.setTextCustom("(Vor dir liegt ein Buch, mit der Aufschrift#NEXT#'YUME Capsule AGB')", Color.black);
					talk++;
					break;
				case 1:
					parent.startDecision("(Was möchtest du tun?)", "Lesen", "Ignorieren");
					break;
				case 2:
					parent.setTextCustom("", Color.black);
					parent.getPlayer().setTalking(false);
					kill();
				}
			}
		}
		else if(action.equals("DECISIONLesen")){
			parent.setTextCustom("(Du hast die AGB schon längst gelesen und#NEXT#akzeptiert. Erinnerst du dich nicht?)", Color.black);
			talk = 2;
		}
		else if(action.equals("DECISIONIgnorieren")){
			parent.setTextCustom("(Richtig, wer ließt sich schon die AGBS durch?)", Color.black);
			talk = 2;
		}
	}

}
