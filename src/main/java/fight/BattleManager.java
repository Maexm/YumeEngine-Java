package fight;

import java.awt.Color;
import java.time.LocalTime;

import erstesSpiel.Coordinate;
import gui.MainGUI;
import objects.BattleWall;
import objects.DarkSpirit;
import objects.GameObject;
import objects.LaserBeam;
import objects.LaserDevice;
import timer.TimerJob;

public class BattleManager {

	private MainGUI parent;
	private LocalTime clock;
	private boolean success;
	public boolean active;
	private int count = 0;
	Coordinate pointA;
	Coordinate pointB;
	
	public BattleManager(MainGUI pParent){
		parent  = pParent;
		success = false;
		active = false;
	}
	/**
	 * Erster Kampf
	 * Muss mindestens 10 x 10 groß sein
	 * @param frstPoint
	 * @param scndPoint
	 */
	public void startBattle(Coordinate frstPoint, Coordinate scndPoint){
		success = true;
		pointA = frstPoint;
		pointB = scndPoint;
		//ZEICHNE MAUERN
		
		//Prüfe, ob Feld mindestens 10x1ß groß
		if(Math.abs(frstPoint.getX()-scndPoint.getX()) < 10 || Math.abs(frstPoint.getY()-scndPoint.getY()) < 10){
			success = false;
			System.out.println("FEHLER: Konnte Kampf mit "+frstPoint.stringCoordinate()+ " - "+scndPoint.stringCoordinate()+" nicht starten! Feld zu klein - Muss 10x10 sein, ist aber "+Math.abs(frstPoint.getX()-scndPoint.getX())+"x"+Math.abs(frstPoint.getY()-scndPoint.getY()));
		}
		//Standard = erster Punkt ist oben links, zweiter Punkt ist unten rechts
		else if(frstPoint.getX() < scndPoint.getX() && frstPoint.getY() < scndPoint.getY()){
			for(int i = frstPoint.getX(); i <= scndPoint.getX(); i++){//Waagerecht
				parent.objectList.add(new BattleWall(new Coordinate(i, frstPoint.getY()), 10, 10, "Battlewall", 0, GameObject.OBJECTTYPE_BATTLEOBJECT(), parent, 0, 1000));
				parent.objectList.add(new BattleWall(new Coordinate(i, scndPoint.getY()), 10, 10, "Battlewall", 0, GameObject.OBJECTTYPE_BATTLEOBJECT(), parent, 0, 1000));
			}
			for(int i = frstPoint.getY(); i < scndPoint.getY(); i++){//Senkrecht
				parent.objectList.add(new BattleWall(new Coordinate(scndPoint.getX(), i), 10, 10, "Battlewall", 0, GameObject.OBJECTTYPE_BATTLEOBJECT(), parent, 0, 1000));
				parent.objectList.add(new BattleWall(new Coordinate(frstPoint.getX(), i), 10, 10, "Battlewall", 0, GameObject.OBJECTTYPE_BATTLEOBJECT(), parent, 0, 1000));
			}
		}//Wenn erster Punkt rechts vom zweiten Punkt ist, aber dafür höher
		else if(frstPoint.getX() > scndPoint.getX()){
			for(int i = frstPoint.getX(); i <= scndPoint.getX(); i--){//Waagerecht
				parent.objectList.add(new BattleWall(new Coordinate(i, frstPoint.getY()), 10, 10, "Battlewall", 0, GameObject.OBJECTTYPE_BATTLEOBJECT(), parent, 0, 1000));
				parent.objectList.add(new BattleWall(new Coordinate(i, scndPoint.getY()), 10, 10, "Battlewall", 0, GameObject.OBJECTTYPE_BATTLEOBJECT(), parent, 0, 1000));
			}
			for(int i = frstPoint.getY(); i < scndPoint.getY(); i++){//Senkrecht
				parent.objectList.add(new BattleWall(new Coordinate(scndPoint.getX(), i), 10, 10, "Battlewall", 0, GameObject.OBJECTTYPE_BATTLEOBJECT(), parent, 0, 1000));
				parent.objectList.add(new BattleWall(new Coordinate(frstPoint.getX(), i), 10, 10, "Battlewall", 0, GameObject.OBJECTTYPE_BATTLEOBJECT(), parent, 0, 1000));
			}
		}//Wenn erster Punkt unter zweitem Punkt
		else if(frstPoint.getY() > scndPoint.getY()){
			for(int i = frstPoint.getX(); i <= scndPoint.getX(); i++){//Waagerecht
				parent.objectList.add(new BattleWall(new Coordinate(i, frstPoint.getY()), 10, 10, "Battlewall", 0, GameObject.OBJECTTYPE_BATTLEOBJECT(), parent, 0, 1000));
				parent.objectList.add(new BattleWall(new Coordinate(i, scndPoint.getY()), 10, 10, "Battlewall", 0, GameObject.OBJECTTYPE_BATTLEOBJECT(), parent, 0, 1000));
			}
			for(int i = frstPoint.getY(); i < scndPoint.getY(); i--){//Senkrecht
				parent.objectList.add(new BattleWall(new Coordinate(scndPoint.getX(), i), 10, 10, "Battlewall", 0, GameObject.OBJECTTYPE_BATTLEOBJECT(), parent, 0, 1000));
				parent.objectList.add(new BattleWall(new Coordinate(frstPoint.getX(), i), 10, 10, "Battlewall", 0, GameObject.OBJECTTYPE_BATTLEOBJECT(), parent, 0, 1000));
			}
		}//Wenn erster Punkt rechts vom UND unter zweitem Punkt ist
		else if(frstPoint.getY() > scndPoint.getY() && frstPoint.getX() > scndPoint.getX()){
			for(int i = frstPoint.getX(); i <= scndPoint.getX(); i--){//Waagerecht
				parent.objectList.add(new BattleWall(new Coordinate(i, frstPoint.getY()), 10, 10, "Battlewall", 0, GameObject.OBJECTTYPE_BATTLEOBJECT(), parent, 0, 1000));
				parent.objectList.add(new BattleWall(new Coordinate(i, scndPoint.getY()), 10, 10, "Battlewall", 0, GameObject.OBJECTTYPE_BATTLEOBJECT(), parent, 0, 1000));
			}
			for(int i = frstPoint.getY(); i < scndPoint.getY(); i--){//Senkrecht
				parent.objectList.add(new BattleWall(new Coordinate(scndPoint.getX(), i), 10, 10, "Battlewall", 0, GameObject.OBJECTTYPE_BATTLEOBJECT(), parent, 0, 1000));
				parent.objectList.add(new BattleWall(new Coordinate(frstPoint.getX(), i), 10, 10, "Battlewall", 0, GameObject.OBJECTTYPE_BATTLEOBJECT(), parent, 0, 1000));
			}
		}//Einer oder beide x/y Werte sind identisch, wodurch keine Kampffläche entstehen kann
		else{
			success = false;
			System.out.println("FEHLER: Konnte Kampf mit "+frstPoint.stringCoordinate()+ " - "+scndPoint.stringCoordinate()+" nicht starten! Koordinaten fehlerhaft.");
		}
		
		//ERSTELLE OBJEKTE, WENN MAUERN ERFOLGREICH GEBAUT
		
		if(success){
			active = true;
			parent.objectList.add(new DarkSpirit(new Coordinate(frstPoint.getX()+2,frstPoint.getY()+5), 75, 75, "DarkSpirit1", 15, GameObject.OBJECTTYPE_ENEMY(), parent, 1, 1000));
			count = 0;
		}
	}
	
	public boolean noEnemiesLeft(){
		for(int i = 0; i < parent.getObjectList().size(); i++){
			if(parent.getObjectList().get(i).getType().equals("ENEMY")){
				//System.out.println("EnemiesLeft");
				return false;
			}
		}
		return true;
	}
	public void removeBattleObjects(){
		for(int i = 0; i < parent.objectList.size(); i++){
			if(parent.objectList.get(i).getType().equals(GameObject.OBJECTTYPE_BATTLEOBJECT())){
				parent.objectList.get(i).stop();
				i--;
			}
		}
	}
	
	public void tick(String pJob){
		if(pJob.equals("DarkSpirit1Died") && count == 0){
			parent.objectList.add(new DarkSpirit(new Coordinate(pointA.getX()+2,pointA.getY()+4), 20, 20, "DarkSpirit2", 15, GameObject.OBJECTTYPE_ENEMY(), parent, 1, 1000));
			parent.objectList.add(new DarkSpirit(new Coordinate(pointA.getX()+2,pointA.getY()+5), 20, 20, "DarkSpirit3", 15, GameObject.OBJECTTYPE_ENEMY(), parent, 1, 1000));
			parent.objectList.add(new DarkSpirit(new Coordinate(pointA.getX()+2,pointA.getY()+6), 20, 20, "DarkSpirit4", 15, GameObject.OBJECTTYPE_ENEMY(), parent, 1, 1000));
			count++;
		}
		else if(count == 1){//Prüft, ob keine Gegner mehr da sind und erhöht, wenn true, den count und erstellt neue Gegner
			if(noEnemiesLeft()){
				count++;
				//System.out.println("NEUE GEGNER");
				parent.objectList.add(new DarkSpirit(new Coordinate(pointA.getX()+2,pointA.getY()+6), 100, 100, "DarkSpirit4", 15, GameObject.OBJECTTYPE_ENEMY(), parent, 2, 250));
				parent.objectList.add(new LaserDevice(new Coordinate(pointA.getX()+5,pointA.getY()+6), 100, 100, "LaserDeviceA", 30, GameObject.OBJECTTYPE_ENEMY(), parent, 1, 2000));
			}
		}
		else if(count == 2){
			if(noEnemiesLeft()){
				count++;
				parent.getPlayer().getWeapon().fullRecharge();
				parent.getPlayer().repatch();
				parent.setTextCustomMute("WAFFE NEU AUFGELADEN#NEXT#LEBEN WIEDERHERGESTELLT", Color.orange);
				parent.timeJobList.add(new TimerJob("REMOVETEXT", 3500, parent));
				//System.out.println("NEUE GEGNER");
				parent.objectList.add(new DarkSpirit(parent.getPlayer().getPos().copy(), 75, 75, "DarkSpirit4", 15, GameObject.OBJECTTYPE_ENEMY(), parent, 1, 1000));
				parent.objectList.add(new DarkSpirit(new Coordinate(pointA.getX()+1,pointA.getY()+1), 50, 50, "DarkSpirit4", 15, GameObject.OBJECTTYPE_ENEMY(), parent, 1, 1000));
				parent.objectList.add(new DarkSpirit(new Coordinate(pointA.getX()+1,pointA.getY()+9), 50, 50, "DarkSpirit4", 15, GameObject.OBJECTTYPE_ENEMY(), parent, 1, 1000));
				parent.objectList.add(new DarkSpirit(new Coordinate(pointA.getX()+14,pointA.getY()+1), 50, 50, "DarkSpirit4", 15, GameObject.OBJECTTYPE_ENEMY(), parent, 1, 1000));
				parent.objectList.add(new DarkSpirit(new Coordinate(pointA.getX()+14,pointA.getY()+9), 50, 50, "DarkSpirit4", 15, GameObject.OBJECTTYPE_ENEMY(), parent, 1, 1000));
				parent.objectList.add(new LaserDevice(new Coordinate(pointA.getX()+2,pointA.getY()+2), 1500, 1500, "LaserDeviceA", 30, GameObject.OBJECTTYPE_ENEMY(), parent, 1, 2000));
				parent.objectList.add(new LaserDevice(new Coordinate(pointA.getX()+2,pointA.getY()+8), 1500, 1500, "LaserDeviceB", 30, GameObject.OBJECTTYPE_ENEMY(), parent, 1, 2000));
				parent.objectList.add(new LaserDevice(new Coordinate(pointA.getX()+13,pointA.getY()+2), 1500, 1500, "LaserDeviceC", 30, GameObject.OBJECTTYPE_ENEMY(), parent, 1, 2000));
				parent.objectList.add(new LaserDevice(new Coordinate(pointA.getX()+13,pointA.getY()+8), 1500, 1500, "LaserDeviceD", 30, GameObject.OBJECTTYPE_ENEMY(), parent, 1, 2000));
			}
		}
		else if(count == 3){
			if(noEnemiesLeft()){
				count = 0;
				active = false;
				removeBattleObjects();
			}
		}
	}
}
