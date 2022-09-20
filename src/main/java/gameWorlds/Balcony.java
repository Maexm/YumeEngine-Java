package gameWorlds;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import erstesSpiel.Coordinate;
import gui.MainGUI;
import objects.BalconyThing;
import objects.GameObject;
import other.FileReader;
import rendering.Texture;
import timer.TimerJob;

public class Balcony extends World{

int sirenCount;
	public Balcony(MainGUI pParent) {
		super(pParent);
		// TODO Auto-generated constructor stub
		sirenCount = 0;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		if(player.getPos().is(10, 3)){
			parent.clearEffects();
			parent.getAmbientPlayer().stopMusic();
			parent.changeWorld(new MainAreaOnline(parent), new Coordinate(11,5));
			for(int i = 0; i < parent.timeJobList.size(); i++){
				if(parent.timeJobList.get(i).getJob().contains("dio")){
					parent.timeJobList.get(i).fullStop();
					break;
				}
			}
			stop();
		}
	}

	@Override
	public void loadTextures() {
		// TODO Auto-generated method stub
		ArrayList<Texture> city = new ArrayList<Texture>();
		try {
			city.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/background/starrySkyMoonCityA.png")), "starrySkyMoonBackgroundA", Color.MAGENTA));
			city.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/background/starrySkyMoonCityB.png")), "starrySkyMoonBackgroundB", Color.MAGENTA));
			city.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/background/starrySkyMoonCityC.png")), "starrySkyMoonBackgroundC", Color.MAGENTA));
			city.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/background/starrySkyMoonCityD.png")), "starrySkyMoonBackgroundD", Color.MAGENTA));
			city.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/background/starrySkyMoonCityF.png")), "starrySkyMoonBackgroundF", Color.MAGENTA));
			if (player.getName().toLowerCase().contains("alina")) {
				city.add(new Texture('N',
						ImageIO.read(new File(FileReader.path() + "NewGeeemu/Pictures/background/starrySkyMoonCityBTS.png")),
						"starrySkyMoonBackgroundBTS", Color.MAGENTA));
			}
			else if(player.getName().toLowerCase().contains("nietzsche")){
				city.add(new Texture('N',
						ImageIO.read(new File(FileReader.path() + "NewGeeemu/Pictures/background/starrySkyMoonCityWagner.png")),
						"starrySkyMoonBackgroundWagner", Color.MAGENTA));
			}
			else if(player.getName().toLowerCase().contains("platon") || player.getName().toLowerCase().contains("aristoteles") || player.getName().toLowerCase().contains("astristotetels")){
				city.add(new Texture('N',
						ImageIO.read(new File(FileReader.path() + "NewGeeemu/Pictures/background/starrySkyMoonCityAthen.png")),
						"starrySkyMoonBackgroundAthen", Color.MAGENTA));
			}
			background = new Texture('0', city, "animatedCityBackground", Color.black, 5000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			textureList.add(new Texture('0', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/building/BalconyRailLeft.png")), "balconyRail", Color.magenta));
			textureList.add(new Texture('1', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/building/BalconyRailRight.png")), "balconyRail", Color.magenta));
			textureList.add(new Texture('2', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/building/BalconyRail.png")), "balconyRail", Color.magenta));
			textureList.add(new Texture('3', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/tiles/darkFloor.png")), "dark", Color.magenta));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void timeTick(String pTask) {
		// TODO Auto-generated method stub
		if(pTask.equals("dio")){
			if(MainGUI.randomNumber(50) == 6){
				ownPlayer.play("dio");
			}
			else if(MainGUI.randomNumber(3) == 2){
				if(sirenCount < 2){
					ownPlayer.play("siren");
					sirenCount++;
				}
				else{
					sirenCount = 0;
				}
				
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
		mapFile = "Balcony";
		illegal = "013";
		parent.getAmbientPlayer().playLooped("desert_wind");
		parent.getRenderer().setZoom(2.0);
		parent.timeJobList.add(new TimerJob("WORLDTICKTIMERdio", 10000, parent));
		parent.objectList.add(new BalconyThing(new Coordinate(8,2), 200, 2000, "BalconyThing", 30, GameObject.OBJECTTYPE_NPC(), parent, 1, 2000));
		loadTextures();
	}

	@Override
	public void tickAction() {
		// TODO Auto-generated method stub
		if(player.isTalking()){
			player.setTalking(false);
			parent.setTextCustom("", Color.black);
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
