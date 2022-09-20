package gameWorlds;

import java.awt.Color;
import java.awt.event.KeyEvent;

import erstesSpiel.Coordinate;
import gui.MainGUI;

public class TransitA extends World{

	public TransitA(MainGUI pParent) {
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
		
	}

	@Override
	public void timeTick(String pTask) {
		// TODO Auto-generated method stub
		if(pTask.equals("text")){
			//System.out.println(parent.timeJobList.size());
			parent.setTextCustom("Ups, falscher Ort.", Color.black);
			parent.timeJobList.get(0).fullChange(3000, "WORLDTICKTIMERchange");
		}
		else if(pTask.equals("change")){
			parent.timeJobList.get(0).stop();
			parent.setTextCustomMute("", Color.black);
			parent.blackOverPic();
			parent.getSoundPlayer().playTick("port", "WORLDTICKSOUNDchange");
		}
		
		
	}

	@Override
	public void soundTick(String pTask) {
		// TODO Auto-generated method stub
		if(pTask.equals("change")){
			parent.timeJobList.get(0).fullChange(2000, "WORLDTICKTIMERtext");
			parent.changeWorld(new TransitB(parent), new Coordinate(9, 9));
			stop();
		}
	}

	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		mapFile = "TransitA";
		parent.getRenderer().setZoom(1.5);
		parent.removeOverPic();
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
