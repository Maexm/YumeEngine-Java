package gameWorlds;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import erstesSpiel.Coordinate;
import gui.MainGUI;
import listener.SmartphoneListener;
import objects.GameObject;
import objects.Player;
import other.SoundPlayer;
import rendering.Texture;

public abstract class World {

	protected MainGUI parent;
	protected Player player;
	protected ArrayList<Texture> textureList;
	protected Texture background;
	protected int textCount = 0;
	protected String mapFile;
	protected String illegal;
	public SoundPlayer ownPlayer;
	protected ArrayList<SoundPlayer> playerList;
	
	public World(MainGUI pParent){
		illegal = "";
		textureList = new ArrayList<Texture>();
		parent = pParent;
		player = parent.getPlayer();
		ownPlayer = new SoundPlayer(this, "ownPlayer");
		playerList = new ArrayList<SoundPlayer>();
		playerList.add(ownPlayer);
		//Deleting all old objects
		while(!parent.objectList.isEmpty()){
			parent.objectList.get(0).stop();
		}
		parent.objectList.clear();		
		setUp();
	}
	
	
	/**
	 * Triggeres, when the player has moved
	 */
	public abstract void tick();
	/**
	 * Load textures here. Do NOT create any objects here!
	 */
	public abstract void loadTextures();
	
	public abstract void timeTick(String pTask);
	
	public abstract void soundTick(String pTask);
	/**
	 * SetUp for the map
	 */
	public abstract void setUp();
	/**
	 * Checks wether or not the move, the player made, was legal or not
	 * @param x
	 * @param y
	 * @return True if move was legal, false if it was not.
	 */
	public boolean legalMove(int x, int y){
		if(parent.debugMode){
			return true;
		}
		if(illegal.contains(String.valueOf(parent.getMapArray()[x][y]))){
			return false;
		}
		for(int i = 0; i < parent.objectList.size(); i++){
			if(parent.objectList.get(i).getPos().is(x, y)){
				return false;
			}
		}
		return true;
	}
	public abstract void tickAction();
	
	public abstract String getDialogue();
	
	
	public ArrayList<Texture> getTextures(){
		return textureList;
	}
	public Texture getBackgound(){
		return background;
	}
	public String getFile(){
		return mapFile;
	}
	public void stop(){
		for(int i = 0; i < textureList.size(); i++){
			textureList.get(i).stopTimer();
		}
		textureList.clear();
		for(int i= 0; i < playerList.size(); i++){
			playerList.get(i).clearQueue();
			playerList.get(i).stopMusic();;
		}
		playerList.clear();
		ownPlayer.clearQueue();
		ownPlayer.stopMusic();
		parent = null;
	}
	protected void stopAnimation(){
		for(int i = 0; i < textureList.size(); i++){
			textureList.get(i).stopTimer();
		}
	}
	protected void continueAnimation(){
		for(int i = 0; i < textureList.size(); i++){
			textureList.get(i).continueTimer();
		}
	}
	protected void startMusic(String pMusic){
		parent.getMusicPlayer().stopMusic();
		parent.getMusicPlayer().playLooped(pMusic);
	}
	protected void stopMusic(){
		parent.getMusicPlayer().stopMusic();
	}
	protected void tickMusic(String pa, String j){
		parent.getMusicPlayer().playTick(pa, "WORLDTICKSOUND"+j);
	}
	public abstract void keyPressed(KeyEvent ke);
	
	protected SoundPlayer getPlayerByName(String pName){
		for(int i = 0; i< playerList.size(); i++){
			if(playerList.get(i).name.equals(pName)){
				return playerList.get(i);
			}
		}
			return null;
	}
	public void unLoadTextures(){
		for(int i = 0; i < textureList.size(); i++){
			textureList.get(i).stopTimer();
		}
		textureList.clear();
	}
	public GameObject getObjectFromListByName(String identifier){
		for(int i= 0; i < parent.objectList.size(); i++){
			if(parent.objectList.get(i).getName().equals(identifier)){
				return parent.objectList.get(i);
			}
		}
		return null;
	}
	public GameObject getObjectFromListByType(String identifier){
		for(int i= 0; i < parent.objectList.size(); i++){
			if(parent.objectList.get(i).getType().equals(identifier)){
				return parent.objectList.get(i);
			}
		}
		return null;
	}
	public GameObject getObjectFromListByNameAndType(String pName, String pType){
		for(int i= 0; i < parent.objectList.size(); i++){
			if(parent.objectList.get(i).getName().equals(pName) && parent.objectList.get(i).getType().equals(pType)){
				return parent.objectList.get(i);
			}
		}
		return null;
	}
	public GameObject getObjectFromListByPos(Coordinate checkPos){
		for(int i= 0; i < parent.objectList.size(); i++){
			if(parent.objectList.get(i).getPos().isSameAs(checkPos)){
				return parent.objectList.get(i);
			}
		}
		return null;
	}
	

}
