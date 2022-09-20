package other;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import rendering.Texture;

public class App {

	public String name;
	public int xPos;
	public int yPos;
	public BufferedImage icon;
	
	public App(String pName, int x, int y, BufferedImage pic){
		name = pName;
		yPos = y;
		xPos = x;
		icon = pic;
	}
	
	public static BufferedImage LOGOUT(){
		
		 try {
			return ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/Other/phoneApps/exitCapsule.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static BufferedImage STATS(){
		 try {
				return ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/Other/phoneApps/stats.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
	}
	public static BufferedImage SETTINGS(){
		 try {
				return ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/Other/phoneApps/settings.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
	}
	
	
}
