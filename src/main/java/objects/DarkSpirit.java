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

public class DarkSpirit extends GameObject{

	public DarkSpirit(Coordinate pPos, int pHealth, int pMaxHealth, String pName, int pAttack, String oType,
			MainGUI pParent, int movementSpeed, int pActingSpeed) {
		super(pPos, pHealth, pMaxHealth, pName, pAttack, oType, pParent, movementSpeed, pActingSpeed);
		// TODO Auto-generated constructor stub
		extraHeight = 10;
		deathSound = "darkSpiritFaint";
	}

	@Override
	public void loadNormal() {
		// TODO Auto-generated method stub
		ArrayList<Texture> txtNormal = new ArrayList<Texture>();
		try {
			txtNormal.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/NPC/DarkSpirit/a.png")), "normalA", Color.black));
			txtNormal.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/NPC/DarkSpirit/b.png")), "normalB", Color.black));
			txtNormal.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/NPC/DarkSpirit/c.png")), "normalC", Color.black));
			txtNormal.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/NPC/DarkSpirit/d.png")), "normalD", Color.black));
			txtNormal.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/NPC/DarkSpirit/e.png")), "normalE", Color.black));
			txtNormal.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/NPC/DarkSpirit/f.png")), "normalF", Color.black));
			txtNormal.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/NPC/DarkSpirit/g.png")), "normalG", Color.black));
			txtNormal.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/NPC/DarkSpirit/h.png")), "normalH", Color.black));
			normalLook[0] = new Texture('N', txtNormal, "darkSpirit", Color.pink, 250);
			normalLook[1] = normalLook[0];
			normalLook[2] = normalLook[0];
			normalLook[3] = normalLook[0];
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
