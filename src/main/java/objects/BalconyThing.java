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

public class BalconyThing extends GameObject{

	public BalconyThing(Coordinate pPos, int pHealth, int pMaxHealth, String pName, int pAttack, String oType,
			MainGUI pParent, int movementSpeed, int pActingSpeed) {
		super(pPos, pHealth, pMaxHealth, pName, pAttack, oType, pParent, movementSpeed, pActingSpeed);
		// TODO Auto-generated constructor stub
		deathSound  = "balconyThingFaint";
	}

	@Override
	public void loadNormal() {
		// TODO Auto-generated method stub
		ArrayList<Texture> idle = new ArrayList<Texture>();
		ArrayList<Texture> scared = new ArrayList<Texture>();
		try {
			idle.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/MapObjects/Balconykus/a.png")), "normalA", Color.black));
			idle.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/MapObjects/Balconykus/b.png")), "normalB", Color.black));
			idle.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/MapObjects/Balconykus/c.png")), "normalC", Color.black));
			idle.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/MapObjects/Balconykus/d.png")), "normalD", Color.black));
			idle.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/MapObjects/Balconykus/e.png")), "normalE", Color.black));
			idle.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/MapObjects/Balconykus/f.png")), "normalF", Color.black));
			idle.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/MapObjects/Balconykus/g.png")), "normalG", Color.black));
			idle.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/MapObjects/Balconykus/h.png")), "normalH", Color.black));
			idle.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/MapObjects/Balconykus/i.png")), "normalI", Color.black));
			idle.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/MapObjects/Balconykus/j.png")), "normalJ", Color.black));
			idle.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/MapObjects/Balconykus/k.png")), "normalK", Color.black));
			idle.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/MapObjects/Balconykus/l.png")), "normalL", Color.black));
			
			scared.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/MapObjects/Balconykus/scared.png")), "scared", Color.black));
			scared.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/MapObjects/Balconykus/scaredB.png")), "scaredB", Color.black));
			
			normalLook[0] = new Texture('N', idle, "balconyThingIdle", Color.pink, 250);
			normalLook[1] = new Texture('N', scared, "balconyThingScared", Color.pink, 100);
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
			if(getDirection() == 'd'){
				turn('u');
				normalLook[0].stopTimer();
				if(getHealth() <= 2){
					parent.getPool().shiftPessimistic(5);
					parent.setTextCustomMute("+5 PESSIMISUMS#NEXT#(Hast du eigentlich nichts Besseres zu tun?)", Color.red);
					parent.getPlayer().setTalking(true);
				}
				shiftHealth(-2);
				if(isAlive()){
					cry.playTick("balconyThingScared", "OBJECTTICKscared");
				}
			}
		}
		else if(action.equals("scared")){
			normalLook[0].continueTimer();
			turn('d');
		}
	}

}
