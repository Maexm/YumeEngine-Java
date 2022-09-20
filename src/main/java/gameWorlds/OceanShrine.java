package gameWorlds;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import erstesSpiel.Coordinate;
import gui.MainGUI;
import objects.MysteryMan;
import objects.ShintoGate;
import objects.ShintoMiko;
import objects.ShintoPriest;
import objects.ShintoShrine;
import other.FileReader;
import rendering.Texture;

public class OceanShrine extends World{

	public OceanShrine(MainGUI pParent) {
		super(pParent);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		if(player.getPos().xInRange(5, 7) && player.getPos().getY() == 0){
			parent.clearEffects();
			parent.changeWorld(new Beach(parent), new Coordinate(5,29));
			stop();
		}
	}

	@Override
	public void loadTextures() {
		// TODO Auto-generated method stub
		try {
			background = new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/background/blue.png")), "blueBackground", Color.MAGENTA);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {//Sand
			textureList.add(new Texture('2', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/MapObjects/sand.png")), "sand", Color.magenta, "sandStep"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Ozean
		ArrayList<Texture> water = new ArrayList<Texture>();
		try {
			water.add(new Texture('0', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/MapObjects/water.png")), "water1", Color.darkGray));
			water.add(new Texture('0', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/MapObjects/waterB.png")), "water2", Color.darkGray));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		textureList.add(new Texture('0', water, "animatedWater", Color.black, 500));
		//Flachwasser
		try {
			textureList.add(new Texture('1', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/tiles/shallowWater.png")), "Flachwasser", Color.magenta, "waterStep"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void timeTick(String pTask) {
		// TODO Auto-generated method stub
		if(pTask.contains("DECISION")){
			if(pTask.contains("はい！")){
				parent.getObjectForName("Mikochan").tickAction("jap");
			}
			else if(pTask.contains("Omae wa mou shindeiru")){
				parent.getObjectForName("Mikochan").tickAction("nap");
			}
		}
	}

	@Override
	public void soundTick(String pTask) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		illegal = "0";
		mapFile = "OceanShrine";
		parent.getRenderer().setZoom(1.5);
		parent.objectList.add(new ShintoShrine(new Coordinate(17, 12), 20, 20, "ShintoShrine", 1, "OBJECT", parent, 1, 1000));
		parent.objectList.add(new ShintoPriest(new Coordinate(25,14), 1, 1, "ShintoPriest", 0, "NPC", parent, 1, 1000));
		parent.objectList.add(new ShintoMiko(new Coordinate(18,12), 1, 1, "Mikochan", 0, "NPC", parent, 1, 1000));
		ownPlayer.playLooped("shrine", 918069);
		loadTextures();
	}

	@Override
	public void tickAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getDialogue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		// TODO Auto-generated method stub
		
	}

}
