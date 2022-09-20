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
import objects.FirstEncounter;
import other.FileReader;
import other.SoundPlayer;
import rendering.Texture;
import timer.TimerJob;

public class Demo extends World{

	private int tutCount = 0;
	public Demo(MainGUI pParent) {
		super(pParent);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		if(parent.getPlayer().getPos().yInRange(2, 4) && parent.getPlayer().getPos().xInRange(34, 37) && parent.getMapArray()[37][2] == '3'){
			
			if(!getPlayerByName("laser").isPlaying() ){
				getPlayerByName("laser").playLooped("laserBeam");
			}
		}
		else if(getPlayerByName("laser").isPlaying()){
			getPlayerByName("laser").stopMusic();
		}
		if(parent.getPlayer().getPos().yInRange(2, 4) && parent.getPlayer().getPos().getX() == 37 && parent.getMapArray()[37][2] == '3'){
			parent.getPlayer().hurt(10);
			parent.getSoundPlayer().play("paralyze");
			if(parent.getPlayer().isAlive()){
				parent.getPlayer().shiftPos(-1, 0);
			}
		}
		if(parent.getPlayer().getPos().yInRange(2, 4) && parent.getPlayer().getPos().getX() == 9 && tutCount == 1){//KOLLISION
			parent.getPlayer().setTalking(true);
			textCount = 8;
			tickAction();
		}
		if(parent.getPlayer().getPos().yInRange(2, 4) && parent.getPlayer().getPos().getX() == 25 && tutCount == 2){//
			parent.getPlayer().setTalking(true);
			textCount = 10;
			tickAction();
		}
		if(parent.getPlayer().getPos().yInRange(2, 4) && parent.getPlayer().getPos().getX() == 50 && tutCount == 3){
			parent.getPlayer().setTalking(true);
			textCount = 11;
			tickAction();
		}
		if(parent.getPlayer().getPos().is(48, 3) && parent.getPlayer().healthPercentage() < 100){
			parent.getSoundPlayer().play("medicHealed");
			player.repatch();
		}
		if(parent.getPlayer().getPos().yInRange(2, 4) && parent.getPlayer().getPos().getX() == 60 && tutCount == 4){
			tutCount++;
			parent.getPlayer().setTalking(true);
			parent.getSoundPlayer().play("phoneAlert");
			parent.setTextCustomMute("NEUE NACHRICHT ERHALTEN", Color.cyan);
			textCount = -10;
		}
		if(parent.getPlayer().getPos().yInRange(2, 4) && parent.getPlayer().getPos().getX() == 70){
			tutCount++;
			parent.getPlayer().setTalking(true);
			textCount = 13;
			tickAction();
		}
		
	}

	@Override
	public void loadTextures() {
		//Background
		try {
			background = new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/background/outerWorld.png")), "oterWorld", Color.MAGENTA);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Frame
		try {
			textureList.add(new Texture('1', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/tiles/frame.png")), "frame", Color.magenta));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Frame2
		try {
			textureList.add(new Texture('0', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/tiles/frame.png")), "frame", Color.magenta));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Debug
		if(parent.debugMode){
			try {
				textureList.add(new Texture('5', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/tiles/debugTile.png")), "debug", Color.magenta));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			for(int i = 0; i < parent.getMapArray()[1].length; i++){
				for(int j = 0; j < parent.getMapArray().length; j++){
					if(parent.getMapArray()[j][i] == '5'){
						parent.getMapArray()[j][i] = 'Q';
					}
				}
			}
		}
		//Button
		try {
			textureList.add(new Texture('2', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/buttons/normal.png")), "button", Color.magenta));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Laser
		ArrayList<Texture> laser = new ArrayList<Texture>();
		try {
			laser.add(new Texture('3', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/laser/laserPink1.png")), "laser1", Color.darkGray));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			laser.add(new Texture('3', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/laser/laserPink2.png")), "laser2", Color.darkGray));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		textureList.add(new Texture('3', laser, "laserPinkAnimated", Color.black, 250));
		
		//HealthPack
		try {
			textureList.add(new Texture('4', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/items/healthPack.png")), "HPPack", Color.magenta));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void timeTick(String pTask) {
		// TODO Auto-generated method stub
		if(pTask.equals("DECISIONAnsprechen")){
			getObjectFromListByName("EncounterA").movePos(0, -1);
			parent.getPool().shiftOptimistic(5);
			parent.getPlayer().setTalking(true);
			parent.setTextCustom("Die Person erschrak und ging sofort zur Seite#NEXT#+5 OPTIMISMUS", Color.green);
		}
		else if(pTask.equals("DECISIONUmbringen")){
			getObjectFromListByName("EncounterA").kill();
			parent.getPool().shiftPessimistic(5);
			parent.getPlayer().setTalking(true);
			parent.setTextCustom("+5 PESSIMISMUS", Color.red);
		}
		else if(pTask.equals("DECISIONJA!")){
			parent.getPool().musikNervt = true;
			parent.getPlayer().setTalking(true);
			parent.setTextCustom("Ah, das ist schön zu -#NEXT#Moment, die Musik nervt dich?", Color.black);
			textCount = 15;
		}
		else if(pTask.equals("DECISIONNein")){
			parent.getPool().musikNervt = false;
			parent.getPlayer().setTalking(true);
			parent.setTextCustom("Ah, das ist schön zu hören, danke!#NEXT#Leider ist jetzt ein Mapwechsel vorgesehen...", Color.black);
			textCount = 18;
		}
		
	}

	@Override
	public void soundTick(String pTask) {
		// TODO Auto-generated method stub
		switch(pTask){
		case "button1":
			parent.getMapArray()[37][2] = 'Q';
			parent.getMapArray()[37][3] = 'Q';
			parent.getMapArray()[37][4] = 'Q';
			break;
		case "change":
			parent.timeJobList.add(new TimerJob("WORLDTICKTIMERtext", 3000, parent));
			parent.changeWorld(new TransitA(parent), new Coordinate(6,4));
			stop();
			break;
			default:
		}
		
	}

	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		mapFile = "Demo";
		illegal = "12";
		playerList.add(new SoundPlayer(this, "laser"));
		parent.getAmbientPlayer().playLooped("demo");
		parent.objectList.add(new FirstEncounter(new Coordinate(54, 3), 20, 20, "EncounterA", 1, "NPC", parent, 1, 1000));
		textCount  = 1;
		parent.getRenderer().setZoom(Math.abs(parent.getRenderer().getZoom()));
		loadTextures();
		parent.getPlayer().setTalking(true);
		tickAction();
	}

	@Override
	public void tickAction() {//LASER deaktiviert
		if(parent.getPlayer().looksAt().isSameAs(new Coordinate(35, 2)) && parent.getMapArray()[37][2] == '3'){
			if(!parent.getSoundPlayer().isPlaying()){
				parent.getSoundPlayer().playTick("click", "WORLDTICKSOUNDbutton1");
				getPlayerByName("laser").stopMusic();
			}
		}
		if(parent.getPlayer().looksAt().isSameAs(new Coordinate(54, 3)) && getObjectFromListByName("EncounterA") != null && getObjectFromListByName("EncounterA").getPos().is(54, 3)){
			parent.startDecision("(Eine Person steht im Weg.)", "Ansprechen", "Umbringen");
		}
			if (parent.getPlayer().isTalking()) {
				switch (textCount) {
				case -10:
					parent.demoText(-1);
					parent.getPlayer().setTalking(false);
					parent.changeListener(new SmartphoneListener(parent, "PUSH", "Hallo,#NEXT#du hast auch ein Smartphone,#NEXT#welches du mit X verwenden kannst!#NEXT#Beachte beim Ausloggen,#NEXT#dass dein Fortschritt NICHT gespeichert wird!#NEXT#Du kannst diese Nachricht mit X wieder schließen!#NEXT#Viele Grüße,#NEXT#der ADMIN"));
					textCount = 0;
					tutCount++;
					break;
				case 1:
					parent.demoText(1);
					textCount++;
					break;
				case 2:
					parent.demoText(2);
					textCount++;
					break;
				case 3:
					parent.demoText(3);
					textCount++;
					break;
				case 4:
					parent.demoText(4);
					textCount++;
					break;
				case 5:
					parent.demoText(5);
					textCount++;
					break;
				case 6:
					parent.demoText(6);
					textCount++;
					break;
				case 7:
					parent.demoText(7);
					tutCount++;
					textCount = 0;
					break;//START
				case 8:
					parent.demoText(8);
					tutCount++;
					textCount = 0;
					break;//KOLLISION
				case 9:
					parent.demoText(9);
					textCount = 0;
					break;//DEBUGINSEL
				case 10:
					parent.demoText(10);
					textCount = 0;
					tutCount++;
					break;//BEWEGUNG
				case 11:
					parent.setTextCustom("Unser System legt ein Charakterprofil über dich an,#NEXT#indem es dein Verhalten und deine Entscheidungen", Color.black);;
					textCount++;
					break;//TASTEN
				case 12:
					parent.setTextCustom("auswertet und deutet.#NEXT#Du stehst also unter ständiger Beobachtung :)", Color.black);
					textCount = 0;
					tutCount++;
					break;
				case 13:
					parent.setTextCustom("Hey, ich hätte da eine kurze Frage", Color.black);
					textCount++;
					break;
				case 14:
					textCount = 0;
					parent.startDecision("Nervt dich eigentlich die Hintergrundmusik?", "JA!", "Nein");
					break;
				case 15:
					parent.setTextCustom("Das ist geklaute Musik, die wir mühevoll#NEXT#durch Audacity gejagt haben!", Color.black);
					textCount++;
					break;
				case 16:
					parent.setTextCustom("Du solltest unsere Arbeit wenigstens ETWAS#NEXT#wertschätzen...", Color.black);
					textCount++;
					break;
				case 17:
					parent.setTextCustom("Nagut, planmäßig wollte ich jetzt sowieso die Map#NEXT#wechseln.", Color.black);
					textCount = 19;
					break;
				case 18:
					parent.setTextCustom("Wir müssen uns jetzt#NEXT#von der Musik verabschieden.", Color.black);
					textCount++;
					break;
				case 19:
					parent.setTextCustom("DREI", Color.black);
					textCount++;
					break;
				case 20:
					parent.setTextCustom("ZWEI", Color.black);
					textCount++;
					break;
				case 21:
					parent.setTextCustom("EINS", Color.black);
					textCount++;
					parent.getAmbientPlayer().stopMusic();
					break;
				case 22:
					parent.demoText(-1);
					textCount++;
					parent.getPlayer().setTalking(false);
					parent.getPlayer().setFrozen(true);
					parent.getAmbientPlayer().stopMusic();
					parent.blackOverPic();
					ownPlayer.playTick("port", "change");
					break;
				case 23:
					break;
				default:
					parent.getPlayer().setTalking(false);
					parent.demoText(-1);
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
