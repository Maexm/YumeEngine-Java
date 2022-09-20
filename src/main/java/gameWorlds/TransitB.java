package gameWorlds;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import erstesSpiel.Coordinate;
import gui.MainGUI;
import other.FileReader;
import rendering.Texture;

public class TransitB extends World{

	public TransitB(MainGUI pParent) {
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
			textureList.add(new Texture('A', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/Players/medic.png")), "medic", Color.magenta));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Lava
		ArrayList<Texture> lava = new ArrayList<Texture>();
		try {
			lava.add(new Texture('L', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/MapObjects/lava.png")), "lava1", Color.darkGray));
			lava.add(new Texture('L', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/MapObjects/lavaB.png")), "lava2", Color.darkGray));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		textureList.add(new Texture('L', lava, "animatedLava", Color.black, 400));
		
		//Old door
		textureList.add(new Texture('D', new BufferedImage(40, 40, BufferedImage.TYPE_INT_RGB), "oldDoor", Color.magenta));
		Graphics blue = textureList.get(2).getTexture().createGraphics();
		blue.setColor(Color.blue);
		blue.fillRect(0, 0, 40, 40);
	}

	@Override
	public void timeTick(String pTask) {
		// TODO Auto-generated method stub
		if(pTask.equals("text")){
			parent.timeJobList.get(0).fullChange(3000, "WORLDTICKTIMERchange");
			parent.setTextCustom("Auch falsch.", Color.black);
		}
		else if(pTask.equals("change")){
			parent.timeJobList.get(0).stop();
			parent.setTextCustomMute("", Color.black);
			parent.blackOverPic();
			parent.getMusicPlayer().stopMusic();
			parent.getAmbientPlayer().stopMusic();
			parent.getAmbientPlayer().playTick("port", "WORLDTICKSOUNDchange");
		}
	}

	@Override
	public void soundTick(String pTask) {
		// TODO Auto-generated method stub
		if(pTask.equals("change")){
			parent.changeWorld(new TransitC(parent), new Coordinate(5, 5));
			parent.timeJobList.get(0).fullChange(1000, "WORLDTICKTIMERtext");
			stop();
		}
	}

	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		mapFile = "TransitB";
		parent.removeOverPic();
		parent.getRenderer().setZoom(1.0);
		parent.getMusicPlayer().play("amb1");
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
