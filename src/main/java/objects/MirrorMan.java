package objects;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import erstesSpiel.Coordinate;
import gui.MainGUI;
import other.FileReader;
import rendering.Texture;
import timer.TimerJob;

public class MirrorMan extends GameObject{

	int zoomSpeed;
	double zoomInterval;
	double originalZoom;
	boolean mirrored = false;
	public MirrorMan(Coordinate pPos, int pHealth, int pMaxHealth, String pName, int pAttack, String oType,
			MainGUI pParent, int movementSpeed, int pActingSpeed, int pZoomSpeed, double pInterval ) {
		super(pPos, pHealth, pMaxHealth, pName, pAttack, oType, pParent, movementSpeed, pActingSpeed);
		// TODO Auto-generated constructor stub
		zoomSpeed = pZoomSpeed;
		zoomInterval = Math.abs(pInterval);
		originalZoom = parent.getRenderer().getZoom();
		deathSound = "mirrorFaint";
		extraHeight = 10;
	}

	@Override
	public void loadNormal() {
		// TODO Auto-generated method stub
		ArrayList<Texture> animate = new ArrayList<Texture>();
		try {
			animate.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/NPC/MirrorMan/normalA.png")), "Front", Color.black));
			animate.add(new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/NPC/MirrorMan/normalB.png")), "FrontB", Color.black));
			normalLook[0] = new Texture('N', animate, "animationNormalMirrorMan", Color.pink, 750);
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
		if(action.equals("SPACE")){
			parent.getPlayer().setFrozen(true);
			parent.timeJobList.add(new TimerJob("OBJECTTICKzoom", zoomSpeed, parent));
			if(mirrored){
				cry.play("mirrorMirrored");
			}
			else{
				cry.play("mirrorNormal");
			}
		}
		else if(action.equals("zoom")){
			if(!mirrored){//Spiegelt Welt, wenn noch nicht gespiegelt
				if(Math.abs(parent.getRenderer().getZoom()) < Math.abs(originalZoom+zoomInterval)){
					parent.getRenderer().shiftZoom(-zoomInterval);
				}
				else{//Stoppt Zoom, wenn gespiegelt
					for(int i= 0; i < parent.timeJobList.size(); i++){
						if(parent.timeJobList.get(i).getJob().equals("OBJECTTICKzoom")){
							parent.timeJobList.get(i).fullStop();
							parent.getPlayer().setFrozen(false);
							parent.getRenderer().setZoom(originalZoom*(-1));
							mirrored = true;
							System.out.println("STOPPE");
							break;
						}
					}
				}
			}
			else{//Kehrt die Welt wieder in die normale Form zurück
				if(Math.abs(parent.getRenderer().getZoom()) < Math.abs(originalZoom+zoomInterval)){
					parent.getRenderer().shiftZoom(zoomInterval);
				}
				else{//Stoppt Zoom, wenn gespiegelt
					for(int i= 0; i < parent.timeJobList.size(); i++){
						if(parent.timeJobList.get(i).getJob().equals("OBJECTTICKzoom")){
							parent.timeJobList.get(i).fullStop();
							parent.getPlayer().setFrozen(false);
							parent.getRenderer().setZoom(originalZoom);
							mirrored = false;
							System.out.println("STOPPE");
							break;
						}
					}
				}
			}
		}
	}

}
