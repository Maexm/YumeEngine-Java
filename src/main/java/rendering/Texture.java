package rendering;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.Timer;

import gameWorlds.World;
import gui.MainGUI;
import listener.TimeListenerTexture;

public class Texture {

	private char id;
	private String name;
	private BufferedImage texture;
	private ArrayList<Texture> animation;
	private boolean animated;
	private int count;
	private Color background;
	private Timer timer;
	private boolean selfAnimated;
	private ArrayList<BufferedImage> picList;
	public String stepSound = "NONE";
	public int[] delayArray;
	
	/**
	 * Constructor for NOT ANIMATED texture
	 * @param pID
	 * @param pTexture
	 * @param pName
	 * @param bckgrnd
	 */
	public Texture(char pID, BufferedImage pTexture, String pName, Color bckgrnd){
		background = bckgrnd;
		id = pID;
		name = pName;
		texture = pTexture;
		animated = false;
		selfAnimated = false;
	}
	/**
	 * Constructor for NORNAL texture WITHOUT animation.
	 * Contains the name of the step sound file.
	 * @param pID
	 * @param pTexture
	 * @param pName
	 * @param bckgrnd
	 */
	public Texture(char pID, BufferedImage pTexture, String pName, Color bckgrnd, String pStepSound){
		background = bckgrnd;
		id = pID;
		name = pName;
		texture = pTexture;
		animated = false;
		selfAnimated = false;
		stepSound = pStepSound;
	}
	/**
	 * Constructor for ANIMATED texture
	 * This texture will change its current BufferedImage on its own
	 * @param pID
	 * @param list
	 * @param pName
	 * @param bckgrnd
	 */
	public Texture(char pID, ArrayList<Texture> list, String pName, Color bckgrnd, int tDelay){
		background = bckgrnd;
		id = pID;
		name = pName;
		count = 0;
		animation = list;
		animated = true;
		selfAnimated = true;
		timer = new Timer(tDelay, new TimeListenerTexture(this));
		if(!list.isEmpty()){
			timer.start();
		}
		else{
			System.out.println("WARNUNG: Die Textur "+name+" ist fehlerhaft und wird nicht abgespielt!");
		}
	}
	/**
	 * Constructor for NORMAL ANIMATED texture but with different delays for each frame
	 * @param pID
	 * @param list
	 * @param pName
	 * @param bckgrnd
	 * @param tDelay
	 * @param pDelays
	 */
	public Texture(char pID, ArrayList<Texture> list, String pName, Color bckgrnd, int tDelay, int[] pDelays){
		background = bckgrnd;
		id = pID;
		name = pName;
		count = 0;
		animation = list;
		animated = true;
		selfAnimated = true;
		delayArray = pDelays;
		if(delayArray.length != list.size()){
			System.out.println("Warnung, Anzahl der Animationen ("+list.size()+") der Textur '"+pName+"' stimmt nicht mit der Länge der Delayliste ("+delayArray.length+") überein. Dies kann zu seltsamen Verhalten führen!");
		}
		timer = new Timer(tDelay, new TimeListenerTexture(this));
		if(!list.isEmpty()){
			timer.start();
		}
		else{
			System.out.println("WARNUNG: Die Textur "+name+" ist fehlerhaft und wird nicht abgespielt!");
		}
	}
	/**
	 * 
	 * Constructor for ANIMATED texture.
	 * This texture will change its current BufferedImage on its own.
	 * Contains the name of the step sound file.
	 * @param pID
	 * @param list
	 * @param pName
	 * @param bckgrnd
	 * @param tDelay
	 * @param pStepSound
	 */
	public Texture(char pID, ArrayList<Texture> list, String pName, Color bckgrnd, int tDelay, String pStepSound){
		background = bckgrnd;
		id = pID;
		name = pName;
		count = 0;
		animation = list;
		animated = true;
		selfAnimated = true;
		timer = new Timer(tDelay, new TimeListenerTexture(this));
		stepSound = pStepSound;
		if(!list.isEmpty()){
			timer.start();
		}
		else{
			System.out.println("WARNUNG: Die Textur "+name+" ist fehlerhaft und wird nicht abgespielt!");
		}
	}
	/**
	 * Constructor for ANIMATED texture
	 * This texture will NOT trigger an animation on its own
	 * Changes texture when accessing getTexture()
	 * @param pID
	 * @param list
	 * @param pName
	 * @param bckgrnd
	 */
	public Texture(char pID, ArrayList<Texture> list, String pName, Color bckgrnd){
		background = bckgrnd;
		id = pID;
		name = pName;
		count = 0;
		animation = list;
		animated = true;
		selfAnimated = false;
	}
	/**
	 * Texture is static, but can change texture when needed (it has a variety of BufferedImages saved in an ArrayList)
	 * @param pID
	 * @param list
	 * @param pName
	 */
	public Texture(char pID, ArrayList<BufferedImage> list, String pName){
		id = pID;
		name = pName;
		count = 0;
		picList = list;
		animated = true;
		selfAnimated = false;
	}
	
	public char getID(){
		return id;
	}
	public BufferedImage getTexture(){
		if(!animated){
			return texture;
		}
		else if(selfAnimated){
			try{
				return animation.get(count).getTexture();
			}catch(Exception e){
				return ScreenCreator.getMissingTexture();
			}
			
		}
		else if(animation != null){
			if(count == animation.size()){
				count = 0;
			}
			else{
				count++;
			}
			return animation.get(count-1).getTexture();
		}
		else{
			return picList.get(0);
		}
	}
	public BufferedImage getByID(int value){
		if (animated) {
			if (picList != null) {
				return picList.get(value);
			} else {
				return animation.get(value).getTexture();
			} 
		}
		else{
			return null;
		}
	}
	/**
	 * Updates the BufferedImage that getTexture() will return
	 */
	public void tick(){
		if(animated) {
			if (count == animation.size() - 1) {
				count = 0;
			} else {
				count++;
			}
			if(delayArray != null){
				timer.stop();
				timer.setDelay(delayArray[count]);
				timer.start();
			}
		}
	}
	public void changeID(char newValue){
		id = newValue;
	}
	public boolean isAnimated(){
		return animated;
	}
	public void changeTexture(BufferedImage txt){
		texture = txt;
		if(animated){
			animated = false;
			texture = txt;
			animation = null;
			System.out.println(name+" wurde in eine TEXTUR verwandelt");
		}
	}
	public void changeAnimation(ArrayList<Texture> list){
		count = 0;
		animation = list;
		if(!animated){
			texture = null;
			animated = true;
			System.out.println(name+" wurde in eine ANIMATION verwandelt");
		}
	}
	
	public String getName(){
		return name;
	}
	
	public void changeName(String newName){
		name = newName;
	}
	
	public Color getBackground(){
		if(!isAnimated()){
			return background;
		}
		else{
			if(count == 0){
				return animation.get(animation.size()-1).getBackground();
			}
			else{
				return animation.get(count).getBackground();
			}
		}
	
	}
	
	public void changeColor(Color pColor){
		background = pColor;
	}
	
	public void stopTimer(){
		if(animated){
			timer.stop();
			for(int i=0; i < animation.size();i++){
				animation.get(i).stopTimer();
			}
		}
	}
	public void continueTimer(){
		if(animated && !timer.isRunning()){
			timer.start();
		}
	}
	
	/**
	 * Checks wether or not a texture list contains a certain ID
	 * For example: The wantedID is 1. This method returns true, if the list DOES have a texture with this ID. Returns false if that is not the case.
	 * @param probe
	 * @param wantedID
	 * @return
	 */
	public static boolean textureExists(ArrayList<Texture> probe, char wantedID){
		for(int i = 0; i < probe.size(); i++){
			if(probe.get(i).getID() == wantedID){
				return true;
			}
		}
		return false;
	}
	public static Texture getTXTfromList(char theID, ArrayList<Texture> pList){
		for(int a = 0; a < pList.size(); a++){
			if(pList.get(a).getID() == theID){
				return pList.get(a);
			}
		}
		System.out.println("KONNTE TEXTUR IN DER ARRAYLISTE NICHT FINDEN");
		return null;
	}
	public boolean isSelfAnimated(){
		return selfAnimated;
	}
}
