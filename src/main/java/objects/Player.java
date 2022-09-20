package objects;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import erstesSpiel.Coordinate;
import gui.MainGUI;
import other.FileReader;
import rendering.Texture;

public class Player extends GameObject{


	private int money;
	private boolean waifu;
	private int stage;
	private boolean mayWaifu;
	private boolean mustWaifu;
	private boolean talking;


	public Player(Coordinate pPos, int pHealth, int pMaxHealth, String pName, int pAttack, String oType, MainGUI pParent, int movementSpeed, int pActingSpeed) {
		super(pPos, pHealth, pMaxHealth, pName, pAttack, oType, pParent, movementSpeed, pActingSpeed);
		money = 0;
		waifu = false;
		stage = 1;
		mayWaifu = false;
		mustWaifu = false;
		talking = false;
	}
	
	
	public int getMoney(){
		return money;
	}
	public void setMoney(int newMoneyValue){
		money = newMoneyValue;
	}
	public void addMoney(int value){
		money = money + value;
	}
	public int getStage(){
		return stage;
	}
	public void setStage(int value){
		stage = value;
	}
	public void stageUp(){
		stage++;
	}
	public boolean metHer(){
		return waifu;
	}
	public void setMetHer(boolean didHe){
		waifu = didHe;
	}
	public void printStats(){
		//System.out.println("###################");
		//System.out.println("Name: "+getName()+"\n"+"Health: "+getHealth()+"\n"+"Money: "+getMoney()+"\n"+"Stage: "+getStage()+"\n"+"WaifuValue: "+metHer()+"\n"+"MayMeet: "+mayMeetHer()+"\n"+"NeedsToMeetHer: "+mustMeetHer());
		//System.out.println("###################");
	}
	public boolean mayMeetHer(){
		return mayWaifu;
	}
	public void setMeyMeetHer(boolean mayHe){
		mayWaifu = mayHe;
	}
	public boolean mustMeetHer(){
		return mustWaifu;
	}
	public void setMustMeetHer(boolean mustHe){
		mustWaifu = mustHe;
	}
	public boolean isTalking(){
		return talking;
	}
	public void setTalking(boolean value){
		talking = value;
	}
	
	public boolean canMove(){
		if(isTalking()){
			return false;
		}
		else{
			return getCanMove();
		}
	}
	
	public char nextTo(char[][] map){
		if(map[getPos().getX()-1][getPos().getY()] == 'I' || map[getPos().getX()+1][getPos().getY()] == 'I' || map[getPos().getX()][getPos().getY()+1] == 'I' || map[getPos().getX()][getPos().getY()-1] == 'I'){
			return 'I';
		}
		else if(map[getPos().getX()-1][getPos().getY()] == 'A' || map[getPos().getX()+1][getPos().getY()] == 'A' || map[getPos().getX()][getPos().getY()+1] == 'A' || map[getPos().getX()][getPos().getY()-1] == 'A'){
			return 'A';
		}
		else{
			return ' ';
		}
	}


	@Override
	public void loadNormal() {
		try {
			normalLook[0] = new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/Players/player.png")), "playerFront", Color.black);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			normalLook[1] = new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/Players/playerBack.png")), "playerBack", Color.black);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			normalLook[2] = new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/Players/playerLeft.png")), "playerLeft", Color.black);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			normalLook[3] = new Texture('N', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/Players/playerRight.png")), "playerRight", Color.black);
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
	public void attack(){
		for(int i = 0; i < parent.objectList.size(); i++){
			if(parent.objectList.get(i).getType().contains("ENEMY") && parent.objectList.get(i).getPos().isSameAs(looksAt())){
				//parent.objectList.get(i).hurt(getAttack());
				if(equipped != null){
					if (!parent.debugMode) {
						parent.objectList.get(i).hurt(equipped.doDamage());
					}
					else{
						parent.objectList.get(i).kill();
					}
					break;
				}
			}
		}
	}
	
}
