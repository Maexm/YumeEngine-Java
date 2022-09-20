package gameWorlds;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import erstesSpiel.Coordinate;
import gui.MainGUI;
import objects.MirrorMan;
import other.FileReader;
import rendering.Texture;

public class MainAreaOnline extends World{

	public MainAreaOnline(MainGUI pParent) {
		super(pParent);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		if(parent.getPlayer().getPos().getY() == 8 && (parent.getPlayer().getPos().getX() == 5 || parent.getPlayer().getPos().getX() == 6)){
			parent.getPlayer().setTalking(true);
			parent.demoText(0);
			textCount = 1;
		}
		else if(parent.getPlayer().getPos().is(4, 1)){
			parent.getAmbientPlayer().playTick("teleport", "WORLDTICKSOUNDstartDemo");
			parent.getPlayer().setFrozen(true);
		}
		else if(parent.getPlayer().getPos().is(11, 5)){
			parent.clearEffects();
			parent.changeWorld(new Balcony(parent), new Coordinate(10,3));
			stop();
		}
	}

	@Override
	public void loadTextures() {
		// TODO Auto-generated method stub
		try {
			background = new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/background/starrySkyMoon.png")), "starrySkyMoonBackground", Color.MAGENTA);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//LAPTOP
		try {
			textureList.add(new Texture('0', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/laptop/laptopOff.png")), "laptop", Color.magenta));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			//BED
			try {
				textureList.add(new Texture('1', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/furniture/bed.png")), "bed", Color.magenta));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//BOOKSHELF
			try {
				textureList.add(new Texture('2', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/furniture/bookshelfFrontFilled.png")), "bookShelf", Color.magenta));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//ENTER
			
			try {
				textureList.add(new Texture('3', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/tiles/enter.png")), "enter", Color.lightGray));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Apaato sign
			try {
				textureList.add(new Texture('4', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/city/signs/apaato.png")), "apaato", Color.lightGray));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//3dish wall
			try {
				textureList.add(new Texture('5', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/building/wall/brown.png")), "brownWall", Color.lightGray));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//DEMO-Enter
			ArrayList<Texture> demoDoor = new ArrayList<Texture>();
			try {
				demoDoor.add(new Texture('6', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/building/wall/red.png")), "demoDoor", Color.darkGray));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				demoDoor.add(new Texture('6', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/building/wall/yellow.png")), "demoDoor", Color.darkGray));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				demoDoor.add(new Texture('6', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/building/wall/green.png")), "demoDoor", Color.darkGray));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				demoDoor.add(new Texture('6', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/building/wall/blue.png")), "demoDoor", Color.darkGray));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			textureList.add(new Texture('6', demoDoor, "demoDoor", Color.black, 40));
	}

	@Override
	public void timeTick(String pTask) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void soundTick(String pTask) {
		// TODO Auto-generated method stub
		if(pTask.equals("startDemo")){
			stopMusic();
			stopAnimation();
			parent.getPlayer().setFrozen(false);
			parent.clearEffects();
			parent.changeWorld(new Demo(parent), new Coordinate(1,3));
			stop();
		}
		
	}

	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		loadTextures();
		stopMusic();
		parent.getRenderer().setZoom(1.5);
		ownPlayer.playLooped("demoPortal");
		illegal = "0126";
		mapFile = "MainAreaOnline";
		parent.objectList.add(new MirrorMan(new Coordinate(2, 7), 20, 20, "MirrorMan", 1, "NPC", parent, 1, 1000, 30, 0.05));
	}

	@Override
	public void tickAction() {
		// TODO Auto-generated method stub
		if(textCount == 1){
			textCount = 0;
			parent.demoText(-1);
			parent.getPlayer().setTalking(false);
			parent.getPlayer().turn('u');
			parent.getPlayer().movePos(0, -1);
		}
		else if(parent.getPlayer().looksAt().is(2, 2) && parent.debugMode){
			parent.changeWorld(new Beach(parent), new Coordinate(9,11));
			stop();
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
