package objects;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import erstesSpiel.Coordinate;
import gui.MainGUI;
import other.FileReader;
import rendering.Texture;

public class LaserDevice extends GameObject{

	public LaserDevice(Coordinate pPos, int pHealth, int pMaxHealth, String pName, int pAttack, String oType,
			MainGUI pParent, int movementSpeed, int pActingSpeed) {
		super(pPos, pHealth, pMaxHealth, pName, pAttack, oType, pParent, movementSpeed, pActingSpeed);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void loadNormal() {
		// TODO Auto-generated method stub
		try {
			normalLook[0] = new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/NPC/Laserdevice/idle.png")), "LaserDeviceIdle", Color.pink);
			normalLook[1] = new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/NPC/Laserdevice/charging.png")), "LaserDeviceCharging", Color.pink);
			normalLook[2] = new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/NPC/Laserdevice/charged.png")), "LaserDeviceCharged", Color.pink);
			normalLook[3] = new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/NPC/Laserdevice/burst.png")), "LaserDeviceBurst", Color.pink);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void additionalStop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tickAction(String action) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void act(){
		int count = 0;
		if(getDirection() == 'd'){//normal
			turn('u');
		}
		else if(getDirection() == 'u'){//lade
			turn('l');
		}
		else if(getDirection() == 'l'){//voll geladen
			turn('r');
			for(int i = 1; i <= pos.getX(); i++){//Schieße Laser horizontal Richtung x = 0
				parent.objectList.add(new LaserBeam(new Coordinate(pos.getX()-i, pos.getY()), 2000, 2000, "LaserBeamHorizontal"+getName(), 30, GameObject.OBJECTTYPE_BATTLEOBJECT(), parent, 1, 3000));
				count++;
			}
			for(int i= 1; i+pos.getX() < parent.getMapArray().length; i++){//Schieße Laser horizontal Richtung x = unendlich
				parent.objectList.add(new LaserBeam(new Coordinate(pos.getX()+i, pos.getY()), 2000, 2000, "LaserBeamHorizontal"+getName(), 30, GameObject.OBJECTTYPE_BATTLEOBJECT(), parent, 1, 3000));
				count++;
			}
			for(int i= 1; i <= pos.getY(); i++){//Schieße Laser vertikal Richtung y = 0
				parent.objectList.add(new LaserBeam(new Coordinate(pos.getX(), pos.getY()-i), 2000, 2000, "LaserBeamVertical"+getName(), 30, GameObject.OBJECTTYPE_BATTLEOBJECT(), parent, 1, 3000));
				count++;
			}
			for(int i= 1; i+pos.getY() < parent.getMapArray()[1].length; i++){//Schieße Laser horizontal Richtung y = unendlich
				parent.objectList.add(new LaserBeam(new Coordinate(pos.getX(), pos.getY()+i), 2000, 2000, "LaserBeamVertical"+getName(), 30, GameObject.OBJECTTYPE_BATTLEOBJECT(), parent, 1, 3000));
				count++;
			}
			//System.out.println(count+" laser erstellt");
		}
		else if(getDirection() == 'r'){//abschuss
			turn('d');
			for(int i = 0; i < parent.getObjectList().size(); i++){
				if(parent.getObjectList().get(i).getName().contains("LaserBeam") && parent.getObjectList().get(i).getName().contains(getName())){
					parent.getObjectList().get(i).stop();
					i--;
					count++;
				}
			}
			//System.out.println(count+" Laser gelöscht");
		}
	}

}
