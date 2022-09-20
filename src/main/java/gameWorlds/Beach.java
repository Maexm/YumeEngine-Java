package gameWorlds;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import erstesSpiel.Coordinate;
import gui.MainGUI;
import listener.SmartphoneListener;
import objects.AGBBook;
import objects.EasterEgg;
import objects.MirrorMan;
import objects.MysteryMan;
import objects.OrangeMan;
import objects.Radio;
import objects.ShintoGate;
import other.FileReader;
import rendering.ScreenCreator;
import rendering.Texture;
import timer.TimerJob;
import weapons.Weapon;

public class Beach extends World{
private int tutCount = 0;
	public Beach(MainGUI pParent) {
		super(pParent);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		if(player.getPos().getX() == 32 && player.getPos().yInRange(26, 28) && tutCount == 0){
			parent.getPlayer().setTalking(true);
			parent.getSoundPlayer().play("phoneAlert");
			parent.setTextCustomMute("NEUE NACHRICHT ERHALTEN", Color.cyan);
			textCount = 1;
		}
		if(player.getPos().getX() == 38 && player.getPos().yInRange(23, 28)&& tutCount == 1){
			parent.getPlayer().setTalking(true);
			parent.setTextCustom("OH VERDAMMTE SCHEIßE#NEXT#Unsere Projektleider ziehen nun den Kürzeren!", Color.black);
			textCount = 2;
		}
		if(player.getPos().xInRange(4, 6)&& player.getPos().getY() == 30){
			parent.clearEffects();
			parent.changeWorld(new OceanShrine(parent), new Coordinate(6,1));
			stop();
		}
		if(player.getPos().getX() == 27  && player.getPos().yInRange(26, 28)){
			parent.getRenderer().setZoom(2.0);
		}
		else if(player.getPos().getX() == 26  && player.getPos().yInRange(26, 28)){
			parent.getRenderer().setZoom(1.5);
		}
		else if(player.getPos().xInRange(0, 25)){
			parent.getRenderer().setZoom(1.0);
		}
		else if(player.getPos().getX() == 45 && tutCount == 2){
			parent.startBattle(new Coordinate(40, 20), new Coordinate(55, 30));
			tutCount++;
		}
		else if(player.getPos().is(61,28) && tutCount == 3){
			tutCount++;
			parent.setTextCustom("GLÜCKWUNSCH! DANKE FÜRS SPIELEN!#NEXT#+1000 OPTIMISMUS", Color.green);
			parent.getPool().shiftOptimistic(1000);
			parent.getPlayer().setTalking(true);
			textCount = 9;
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
			textureList.add(new Texture('B', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/MapObjects/sand.png")), "sand", Color.magenta, "sandStep"));
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
			textureList.add(new Texture('2', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/tiles/shallowWater.png")), "Flachwasser", Color.magenta));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void timeTick(String pTask) {
		// TODO Auto-generated method stub
		if(pTask.contains("DECISION")){
			for(int i = 0; i < parent.objectList.size(); i++){
				parent.objectList.get(i).tickAction(pTask);
			}
			player.setTalking(true);
		}
		else if(pTask.contains("zoom")){
			if(parent.getRenderer().getZoom() < 1.0){
				parent.getRenderer().shiftZoom(0.05);
			}
			else{
				for(int i = 0; i < parent.timeJobList.size(); i++){
					if(parent.timeJobList.get(i).getJob().equals("WORLDTICKTIMEzoom")){
						parent.timeJobList.get(i).fullStop();
						player.setFrozen(false);
						parent.getRenderer().setZoom(1.0);
						break;
					}
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
		illegal = "02";
		mapFile = "Beach";
		player.setTalking(false);
		player.setFrozen(true);
		ownPlayer.playLooped("Beach", 460800);
		parent.removeOverPic();
		parent.getRenderer().setZoom(0.01);
		//Wichtig: Objekte müssen in der SetUp Medothe erstellt werden. Die Main GUI führt loadTextures mehrmals aus, weshalb die Objekte doppelt erstellt werden! Da sollte ich evt. fixen.
		parent.objectList.add(new EasterEgg(new Coordinate(29, 13), 20, 20, "EasterEgg", 1, "NPC", parent, 1, 1000));
		parent.objectList.add(new AGBBook(new Coordinate(28, 27), 20, 20, "AGBBook", 1, "OBJECT", parent, 1, 1000));
		parent.objectList.add(new ShintoGate(new Coordinate(3, 30), 20, 20, "ShintoSchrein", 1, "OBJECT", parent, 1, 1000));
		parent.objectList.add(new OrangeMan(new Coordinate(46, 1), 20, 20, "OrangeMan", 1, "NPC", parent, 1, 1000));
		if(MainGUI.randomNumber(2001) == 14){
			parent.objectList.add(new MysteryMan(new Coordinate(43,2), 1, 1, "MysteryMan", 0, "NPC", parent, 1, 1000));
		}
		//parent.objectList.add(new MirrorMan(new Coordinate(1, 27), 20, 20, "MirrorMan", 1, "NPC", parent, 1, 30, 0.05));
		loadTextures();
			parent.timeJobList.add(new TimerJob("WORLDTICKTIMEzoom", 40, parent));
	}

	@Override
	public void tickAction() {
		if (player.isTalking()) {
			// TODO Auto-generated method stub
			switch (textCount) {
			case 1:
				parent.demoText(-1);
				parent.getPlayer().setTalking(false);
				parent.changeListener(new SmartphoneListener(parent, "PUSH", "ACHTUNG - FRISTLOSE KÜNDIGUNG ALLER MITARBEITER#NEXT##NEXT#Sehr geehrte Damen und Herren,#NEXT#Aufgrund von Mangel an finanziellen Ressourcen#NEXT#werden alle unsere Mitarbeiter leider gekündigt.#NEXT#Das YUME CAPSULE Projekt wird erstmal auf Eis gelegt.#NEXT#Die Server sind allerdings noch für eine Woche aktiv.#NEXT#Mit freundlichen Grüßen.#NEXT#Sie können diese Nachricht mit X schließen"));
				textCount = 0;
				tutCount++;
				break;
			case 2:
				parent.setTextCustom("Hi, du hast die traurige Botschaft ja auch gesehen.#NEXT#Mehr muss ich dazu wohl nicht sagen...", Color.black);
				textCount++;
				break;
			case 3:
				parent.setTextCustom("Wir können am Projekt nicht weiterarbeiten.#NEXT#Die Führung durch die DEMO ist somit beendet.", Color.black);
				textCount++;
				break;
			case 4:
				parent.setTextCustom("Weiter vorne ist allerdings ein 'Überraschungsfight'#NEXT#falls du ihn erleben möchtest.", Color.black);
				textCount++;
				break;
			case 5:
				parent.setTextCustom("Also, machs gut!", Color.black);
				textCount++;
				break;
			case 6:
				parent.setTextCustom("Du kannst dich jeder Zeit über das Smartphone#NEXT#ausloggen und die Demo von vorne abspielen.", Color.black);
				textCount++;
				break;
			case 7:
				parent.setTextCustom("Die Security jagt uns schon aus den Büros raus.#NEXT#(Obwohl die Security doch auch gefeuert wird...?)", Color.black);
				textCount++;
				break;
			case 8:
				parent.setTextCustom("Und was den Fragebogen von vorhin angeht...#NEXT#shit Icghw werde rausrgeworefen aaaaaaaaaaaaaaaaaaa", Color.black);
				textCount++;
				tutCount++;
				break;
			case 9:
				parent.demoText(-1);
				parent.getPlayer().setTalking(false);
				textCount = 0;
			}
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
