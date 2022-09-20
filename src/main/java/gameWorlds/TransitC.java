package gameWorlds;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import erstesSpiel.Coordinate;
import gui.MainGUI;
import objects.EasterEgg;
import objects.FirstEncounter;
import objects.OrangeMan;
import other.FileReader;
import rendering.Texture;

public class TransitC extends World{

	public TransitC(MainGUI pParent) {
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
			textureList.add(new Texture('1', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/demo/yumeSitRight.png")), "sitRight", Color.MAGENTA));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			textureList.add(new Texture('2', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/demo/yumeSitLeft.png")), "sitLeft", Color.MAGENTA));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void timeTick(String pTask) {
		// TODO Auto-generated method stub
		if(pTask.equals("text")){
			parent.timeJobList.get(0).stop();
			parent.setTextCustom("Oh Gott was ist das denn?!", Color.black);
		}
	}

	@Override
	public void soundTick(String pTask) {
		// TODO Auto-generated method stub
		if(pTask.equals("change")){
			parent.setTextCustomMute("", Color.black);
			parent.blackOverPic();
			parent.getAmbientPlayer().playTick("port", "WORLDTICKSOUNDchange2");
		}
		if(pTask.equals("change2")){
			parent.changeWorld(new TransitD(parent), new Coordinate(9,11));
			parent.timeJobList.get(0).fullChange(700, "WORLDTICKTIMERtext");
			stop();
		}
		if(pTask.equals("easter")){
			parent.changeWorld(new EasterWorldA(parent), new Coordinate(1,2));
			stop();
		}
	}

	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		mapFile = "TransitC";
		parent.removeOverPic();
		parent.getRenderer().setZoom(2.0);
		ownPlayer.playTick("transitC", "change");
		parent.objectList.add(new OrangeMan(new Coordinate(3, 2), 20, 20, "OrangeManA", 1, "ENEMY", parent, 1, 3000));
		parent.objectList.add(new OrangeMan(new Coordinate(7, 2), 20, 20, "OrangeManB", 1, "ENEMY", parent, 1, 3000));
		parent.objectList.add(new EasterEgg(new Coordinate(5, 22), 20, 20, "EasterEgg", 1, "NPC", parent, 1, 1000));
		parent.objectList.get(1).turn('l');
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
