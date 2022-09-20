package gameWorlds;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import erstesSpiel.Coordinate;
import gui.MainGUI;
import listener.LoginScreen;
import objects.DarkSpirit;
import objects.GameObject;
import objects.LaserDevice;
import objects.MirrorMan;
import objects.MysteryMan;
import objects.Player;
import objects.Radio;
import objects.ShintoPriest;
import other.FileReader;
import rendering.Effect;
import rendering.Texture;

public class MainArea extends World{
	
	

	public MainArea(MainGUI pParent) {
		super(pParent);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		if(parent.getPlayer().getPos().getY() == 7 && (parent.getPlayer().getPos().getX() == 5 || parent.getPlayer().getPos().getX() == 6)){
			parent.clearEffects();
			parent.changeWorld(new ShinshiGorakugai(parent), new Coordinate(39,35));
			stop();
		}
	}

	@Override
	public void loadTextures() {
	try {
		background = new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/background/starrySkyMoon.png")), "starrySkyMoonBackground", Color.MAGENTA);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	//LAPTOP
		ArrayList<Texture> laptop = new ArrayList<Texture>();
		try {
			laptop.add(new Texture('0', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/laptop/laptopNormal.png")), "laptop1", Color.darkGray));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			laptop.add(new Texture('0', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/laptop/laptopPink.png")), "laptop1", Color.darkGray));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		textureList.add(new Texture('0', laptop, "laptopAnimated", Color.black, 600));
		
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
		
	}

	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		mapFile = "MainArea";
		illegal = "012";
		parent.getRenderer().setZoom(1.5);
		//parent.objectList.add(new DarkSpirit(new Coordinate(8,5), 75, 75, "DarkSpirit", 15, GameObject.OBJECTTYPE_ENEMY(), parent, 10, 1000));
		//parent.objectList.add(new LaserDevice(new Coordinate(5,6), 2000, 2000, "LaserDeviceA", 30, GameObject.OBJECTTYPE_ENEMY(), parent, 1, 2000));
		parent.objectList.add(new Radio(new Coordinate(10,4), 1, 1, "Radio", 0, "OBJECT", parent, 1, 1000));
		loadTextures();
	}


	@Override
	public void tickAction() {
			// TODO Auto-generated method stub	
			if(parent.getPlayer().looksAt().is(9, 2)) {//Laptop
				parent.changeListener(new LoginScreen(parent));
			} 
			//Bed
			else if((parent.getPlayer().getPos().is(3, 2) || parent.getPlayer().getPos().is(3, 3)) && parent.getPlayer().getDirection() == 'l' || parent.getPlayer().getPos().is(2, 3) && parent.getPlayer().getDirection() == 'u'){
				if(parent.getPlayer().isTalking()){
					parent.setText(-1, Color.pink);
					parent.getPlayer().setTalking(false);
					textCount = 0;
				}
				else{
					parent.getPlayer().setTalking(true);
					parent.setText(1, Color.pink);
					textCount = 1;
				}
			}
			//bookshelf
			else if((parent.getPlayer().getPos().is(5, 3) || parent.getPlayer().getPos().is(6, 3)) && parent.getPlayer().getDirection() == 'u' || parent.getPlayer().getPos().is(4, 2) && parent.getPlayer().getDirection() == 'r' || parent.getPlayer().getPos().is(7, 2) && parent.getPlayer().getDirection() == 'l') {
				if(parent.getPlayer().isTalking()){
					parent.setText(-1, Color.pink);
					parent.getPlayer().setTalking(false);
					textCount = 0;
				}
				else{
					parent.getPlayer().setTalking(true);
					parent.setText(2, Color.pink);
					textCount = 1;
				}
			}
	}

	@Override
	public String getDialogue() {
		return null;
	}

	@Override
	public void timeTick(String pTask) {
		// TODO Auto-generated method stub
		if(pTask.equals("login")){
			parent.addEffect(new Effect(Effect.typeNoise(), 100));
			parent.getAmbientPlayer().playTick("login", "WORLDTICKSOUNDlogin");
		}
		
	}

	@Override
	public void soundTick(String pTask) {
		if(pTask.equals("login")){
			stopMusic();
			parent.clearEffects();
			parent.normalListener();
			parent.setOverPic(null);
			parent.changeWorld(new MainAreaOnline(parent), parent.getPlayer().getPos());
			stop();
		}
		
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		
	}

}
