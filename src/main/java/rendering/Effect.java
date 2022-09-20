package rendering;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import gui.MainGUI;

public class Effect {
	
	String type;
	int intensity;
	Texture effectPic;
	
	public Effect(String pType, int pIntensity){
		type = pType;
		intensity = pIntensity;
	}
	
	//CREATE EFFECT
	public BufferedImage create(BufferedImage pic){
		switch(type){
		case "NOISE":
			pic = noise(pic);
			break;
		case "DARK":
			pic = dark(pic);
		}
		return pic;
	}
	

	//EFFECTS
	private BufferedImage noise(BufferedImage pic){
		Random randGen = new Random();
		intensity = 100 - intensity;
		for(int y = 0; y < pic.getHeight(); y++){
			for(int x = 0; x < pic.getWidth(); x++){
				if(randGen.nextInt(intensity+1) == 0){
					pic.setRGB(x, y, randGen.nextInt(16777217));
				}
			}
		}
		return pic;
	}
	private BufferedImage flash(BufferedImage pic, int speed){
		int level = 0;
		for(int y = 0; y < pic.getHeight(); y++){
			for(int x = 0; x < pic.getWidth(); x++){
				for(int i = 0; i < level; i++){
					pic.setRGB(x, y, new Color(pic.getRGB(x, y)).brighter().getRGB());
				}
				level = level + speed;
			}
		}
		return pic;
	}
	
	private BufferedImage dark(BufferedImage pic){
		Random randGen = new Random();
		intensity = 100 - intensity;
		for(int y = 0; y < pic.getHeight(); y++){
			for(int x = 0; x < pic.getWidth(); x++){
				if(randGen.nextInt(intensity+1) == 0){
					pic.setRGB(x, y, new Color(pic.getRGB(x, y)).darker().getRGB());
				}
			}
		}
		return pic;
	}
	
	
	
	
	
	//GET and SET
	public String getType(){
		return type;
	}
	public int getIntensity(){
		return intensity;
	}
	public void setIntensity(int pIn){
		intensity = pIn;
	}
	
	
	//RETURN TYPES
	public static String typeNoise(){
		return "NOISE";
	}
	public static String typeDark(){
		return "DARK";
	}
	
	//COMPARE METHOD
	public boolean isSameAs(Effect compare){
		if(compare.getIntensity() == intensity && compare.getType().equals(type)){
			return true;
		}
		return false;
	}
}
