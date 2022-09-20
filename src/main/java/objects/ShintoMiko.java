package objects;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import erstesSpiel.Coordinate;
import gui.MainGUI;
import other.FileReader;
import rendering.Texture;

public class ShintoMiko extends GameObject{

	private int talk = 0;
	
	public ShintoMiko(Coordinate pPos, int pHealth, int pMaxHealth, String pName, int pAttack, String oType,
			MainGUI pParent, int movementSpeed, int pActingSpeed) {
		super(pPos, pHealth, pMaxHealth, pName, pAttack, oType, pParent, movementSpeed, pActingSpeed);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void loadNormal() {
		// TODO Auto-generated method stub
		try {
			normalLook[0] = new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/NPC/ShintoMiko/front.png")), "ShintoMikoFront", Color.pink);
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
			switch(talk){
			case 0:
				parent.getPlayer().setTalking(true);
				parent.setTextCustom("はじめまして！あたしはミコちゃんの巫女です。#NEXT#よろしくお願いします。",Color.black);
				talk++;
				break;
			case 1:
				parent.startDecision("あの...あなたは日本語がわかりますか？", "はい！" ,"Omae wa mou shindeiru");
				talk++;//case 2 does nothing
				break;
			case 3:
				parent.setTextCustom("", Color.black);
				parent.getPlayer().setTalking(false);
				talk = 0;
				break;
			case 4:
				parent.setTextCustom("", Color.black);
				parent.getPlayer().setTalking(false);
				talk++;
				break;
			case 5:
				parent.setTextCustom("ええええ~？！",Color.black);
				talk--;
				break;
			}
		}
		else if(action.equals("jap")){
			parent.setTextCustom("ええええ~　本当ですか？#NEXT#それは珍しくてすごいね！",Color.black);
			talk++;//To case 3
		}
		else if(action.equals("nap")){
			try {
				normalLook[0] = new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/NPC/ShintoMiko/scared.png")), "ShintoMikoScared", Color.pink);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			talk = talk + 3;//to case 5
			tickAction("SPACE");
		}
	}

}
