package gameWorlds;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import erstesSpiel.Coordinate;
import gui.MainGUI;
import objects.BlueMan;
import objects.EasterEgg;
import objects.Radio;
import other.FileReader;
import rendering.Texture;

public class EasterWorldA extends World{

	public EasterWorldA(MainGUI pParent) {
		super(pParent);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadTextures() {
		// TODO Auto-generated method stub
		try {
			textureList.add(new Texture('0', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/walls/whiteWall.png")), "whiteWall", Color.MAGENTA));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void timeTick(String pTask) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void soundTick(String pTask) {
		// TODO Auto-generated method stub
		if(pTask.equals("easter")){
			parent.changeWorld(new Beach(parent), new Coordinate(9,11));
			stop();
		}
	}

	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		mapFile = "EasterWorldA";
		illegal = "0";
		parent.getPool().easterEggRoom = true;
		parent.removeOverPic();
		ownPlayer.playLooped("easterEgg");
		parent.objectList.add(new EasterEgg(new Coordinate(4, 1), 20, 20, "EasterEgg", 1, "NPC", parent, 1, 1000));
		parent.objectList.add(new BlueMan(new Coordinate(6, 4), 20, 20, "BlueMan", 1, "NPC", parent, 1, 1000));
		if(MainGUI.randomNumber(51) == 2){
			parent.objectList.add(new Radio(new Coordinate(1,1), 1, 1, "Radio", 0, "OBJECT", parent, 1, 1000));
		}
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
