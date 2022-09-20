package gameWorlds;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import erstesSpiel.Coordinate;
import gui.MainGUI;
import other.FileReader;
import rendering.Texture;

public class TransitD extends World{

	public TransitD(MainGUI pParent) {
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
			textureList.add(new Texture('B', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/MapObjects/sand.png")), "sand", Color.magenta));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Texture> water = new ArrayList<Texture>();
		try {
			water.add(new Texture('0', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/MapObjects/water.png")), "water1", Color.darkGray));
			water.add(new Texture('0', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/MapObjects/waterB.png")), "water2", Color.darkGray));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		textureList.add(new Texture('0', water, "animatedWater", Color.black, 500));
	}

	@Override
	public void timeTick(String pTask) {
		// TODO Auto-generated method stub
		if(pTask.equals("text")){
			parent.timeJobList.get(0).fullStop();
			textCount++;
			player.setTalking(true);
			ownPlayer.playLooped("BeachStart");
			tickAction();
		}
	}

	@Override
	public void soundTick(String pTask) {
		// TODO Auto-generated method stub
		if(pTask.equals("change")){
			parent.changeWorld(new Beach(parent), new Coordinate(9,11));
			stop();
		}
	}

	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		mapFile = "TransitD";
		parent.removeOverPic();
		parent.getRenderer().setZoom(1.0);
		loadTextures();
	}

	@Override
	public void tickAction() {
		// TODO Auto-generated method stub
		if(textCount == 1){
			parent.setTextCustom("Besser...es fehlt aber etwas.#NEXT#Einen Moment bitte.", Color.black);
			textCount++;
		}
		else if(textCount == 2){
			parent.setTextCustomMute("", Color.black);
			ownPlayer.stopMusic();
			parent.blackOverPic();
			parent.getAmbientPlayer().playTick("port", "WORLDTICKSOUNDchange");
			textCount = 0;
		}
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
