package rendering;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import erstesSpiel.Coordinate;
import objects.*;
import other.FileReader;

public class ScreenCreator{

	BufferedImage normalWall;
	BufferedImage medic;
	BufferedImage playerDown;
	BufferedImage playerUp;
	BufferedImage playerLeft;
	BufferedImage playerRight;
	BufferedImage enemy;
	BufferedImage info;
	BufferedImage shopkeeper;
	BufferedImage waifu;
	BufferedImage sand;
	BufferedImage water;
	BufferedImage missingTexture;
	BufferedImage lava;
	BufferedImage itsMe;
	BufferedImage mapBoss;
	BufferedImage lowHealthWarn;
	//UI
	BufferedImage healthBar;
	BufferedImage chargeBar;
	public String plcHolder = "1234567890ABDEGHIJKLM";
	private Double zoom;
	
	public ScreenCreator(){
		zoom = 1.5;
		//Loading basic textures
		try {
			lowHealthWarn = ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/UI/connectionIssues.png"));
			normalWall = ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/Mapobjects/normalWall.png"));
			playerDown = ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/Players/player.png"));
			playerUp = ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/Players/playerBack.png"));
			playerLeft = ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/Players/playerLeft.png"));
			playerRight = ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/Players/playerRight.png"));
			shopkeeper = ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/Players/shopkeeper.png"));
			waifu = ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/Players/waifu.png"));
			//sand = ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/MapObjects/sand.png"));
			missingTexture = ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/MapObjects/missingTexture.png"));
			itsMe = ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/Players/heyThere.png"));
			healthBar = ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/UI/newHealthBar.png"));
			chargeBar = ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/UI/charge.png"));
			//medic = ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/Players/medic.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("KRITISCHER FEHLER: Konnte grundlegende Texturen nicht laden"+"\n"+"Wird der Pfad evt. nicht gefunden? - TERMINIERE");
			//System.out.println(new File(FileReader.path()+"NewGeeemu/Pictures/UI/connectionIssues.png").exists());
			System.exit(0);
		}	
	}
	public BufferedImage Render(String name, Player pPlayer, char[][] map, ArrayList<Texture> txtList, Texture backgroundTXT, String doNotUSe){
		BufferedImage img;
			//Loading Textures
			//System.out.println("New map: "+name);
			img = new BufferedImage(800, 1000, BufferedImage.TYPE_INT_RGB);
			if (backgroundTXT != null) {
				Graphics background = img.getGraphics();
				background.drawImage(backgroundTXT.getTexture(), 0, 0, new JLabel());
			}
			//System.out.println(pPlayer.getPos().stringCoordinate());
			//drawing world
			for(int i = 0; i < map[1].length; i++){
				for(int j = 0; j < map.length; j++){
					Graphics graphic = img.getGraphics();
					if(map[j][i] != 'P'){
						try {
							drawStuff(j, i, graphic, map, pPlayer.getPos(), txtList, 0, 0);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else{
						//Since the player class is only accessable in this method
						graphic.drawImage(playerDown, 9*40, 9*40,charIntoColor(map[j][i]), new JLabel());
					}
					
				}
			}
			//Drawing Text
			
			return img;
	}
	
	
	
	public BufferedImage renderEdited(Player pPlayer, char[][] originalMap, ArrayList<Texture> txtList, Texture backgroundTXT, ArrayList<GameObject> objects){
		BufferedImage img = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);		
		if (backgroundTXT != null) {
			Graphics background = img.getGraphics();
			background.drawImage(backgroundTXT.getTexture(), 0, 0, new JLabel());
		}
		for(int i = 0; i < originalMap[1].length; i++){
			for(int j = 0; j < originalMap.length; j++){
				Graphics graphic = img.getGraphics();
				
					try {
						drawStuff(j, i, graphic, originalMap, pPlayer.getPos(), txtList, pPlayer.getCorX(), pPlayer.getCorY());
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//System.out.println(co.stringCoordinate());
			}
		}
		//Draw PLAYER
		Graphics graphic = img.getGraphics();
		graphic.drawImage(pPlayer.display(), 9*40, 9*40, doubleToInt(pPlayer.display().getWidth()*zoom), doubleToInt(pPlayer.display().getHeight()*zoom), new JLabel());
		//Draw GAMEOBJECT
		for(int i = 0; i < objects.size(); i++){
			graphic.drawImage(objects.get(i).display(),
					doubleToInt(((objects.get(i).getPos().getX() - (pPlayer.getPos().getX() - 9)) * 40*zoom)+pPlayer.getCorX()*zoom+objects.get(i).getCorX()*zoom-360*(zoom-1)),
					doubleToInt(((objects.get(i).getPos().getY() - (pPlayer.getPos().getY() - 9)) * 40*zoom)+pPlayer.getCorY()*zoom+objects.get(i).getCorY()*zoom-objects.get(i).extraHeight*zoom-360*(zoom-1)),
					doubleToInt(objects.get(i).display().getWidth()*zoom),
					doubleToInt(objects.get(i).display().getHeight()*zoom),
					new JLabel());
			
			//Draw HealthBar for enemies and allies
			if((objects.get(i).getType().equals("ALLY") || objects.get(i).getType().equals("ENEMY")) && objects.get(i).healthPercentage() != 100){
				if(objects.get(i).healthPercentage() > 60){
					graphic.setColor(Color.green);
				}
				else if(objects.get(i).healthPercentage() > 20){
					graphic.setColor(Color.yellow);
				}
				else if(objects.get(i).healthPercentage() > 0){
					graphic.setColor(Color.red);
				}
				graphic.fillRect(doubleToInt(((objects.get(i).getPos().getX() - (pPlayer.getPos().getX() - 9)) * 40*zoom)+pPlayer.getCorX()*zoom+objects.get(i).getCorX()*zoom-360*(zoom-1)),
						doubleToInt(((objects.get(i).getPos().getY() - (pPlayer.getPos().getY() - 9)) * 40*zoom)+pPlayer.getCorY()*zoom+objects.get(i).getCorY()*zoom-objects.get(i).extraHeight*zoom-360*(zoom-1)-zoom*25),
						doubleToInt(zoom*40*objects.get(i).healthPercentage()/100), doubleToInt(zoom*10));
			}
		}
		
		//System.out.println("Returning img");
			return img;
	}
	public BufferedImage changeText(BufferedImage img, String text, Color color, boolean charge){
		//Painting text field white (overwrites old text)
		for(int h = img.getHeight()-200; h < img.getHeight(); h++){
			for (int w = 0; w < img.getWidth(); w++){
				img.setRGB(w, h, Color.black.getRGB());
			}
		}
		
		//Creating text
		Graphics textGraphic = img.getGraphics();
		if(Color.black.equals(color)){
			color = Color.white;
		}
		textGraphic.setColor(color);
		textGraphic.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
		if(text.contains("#NEXT#")){
			String[] textArray = text.split("#NEXT#");
			
			textGraphic.drawString(textArray[0], 0, 840);
			textGraphic.drawString(textArray[1], 0, 870);
		}
		else{
			textGraphic.drawString(text, 0, 840);
		}
		//Charge bar
				textGraphic.drawImage(chargeBar, 800, 0, new JLabel());
				textGraphic.setColor(Color.blue);
				textGraphic.setFont(new Font(Font.DIALOG, Font.BOLD, 40));
				if(charge){
					textGraphic.drawString("LADUNG", 810, 780);
				}
				else{
					textGraphic.drawString("???", 845, 780);
				}
		return img;
	}
	
	
	
	
	
	
	
	public BufferedImage updateHealth(BufferedImage img, Player player, boolean isDebug, int pFPS){
		//draws the health bar
		
		
		Graphics theDrawing = img.getGraphics();
		Graphics theHealth = img.getGraphics();
		Graphics theNumber = img.getGraphics();
		theDrawing.drawImage(healthBar, 0, 900, new JLabel());
		//draws the actual bar
		if(player.healthPercentage() > 60){
			theHealth.setColor(Color.green);
			theHealth.fillRect(12, 912, 776*player.healthPercentage()/100, 39);
		}
		else if(player.healthPercentage() > 15){
			theHealth.setColor(Color.yellow);
			theHealth.fillRect(12, 912, 776*player.healthPercentage()/100, 39);
		}
		else if(player.healthPercentage() > 0){
			theHealth.setColor(Color.red);
			theHealth.fillRect(12, 912, 776*player.healthPercentage()/100, 39);
			img.getGraphics().drawImage(lowHealthWarn, 350, 960, new JLabel());
		}
		else{
			theHealth.setColor(Color.pink);
			theHealth.fillRect(12, 912, 776, 39);
		}
		
		//drawing health information
		theNumber.setColor(Color.orange);
		theNumber.drawString("Health: "+player.getHealth()+"/"+player.getMaxHealth(), 650, 970);
		
		//FPS
		Graphics drawing = img.getGraphics();
		drawing.setColor(Color.yellow);
		drawing.drawString(Integer.toString(pFPS)+ " FPS", 755, 995);
		
		//Chargebar
		drawing.setColor(Color.black);
		if(player != null){
			//Fülle Chargebar mit Füllfarbe
			drawing.setColor(player.getWeapon().barColor);
			drawing.fillRect(834, 32, 92, 699);

			//Überdecke verbrauchte Energie (zeige aktuellen Energiestand an)
			drawing.setColor(Color.black);
			drawing.fillRect(834, 32, 92, 699- 699*player.getWeapon().getChargePercentage()/100);
			
			//Zeichne Ladeeinheiten
			drawing.setColor(Color.white);
			int chargeCount = player.getWeapon().maxCharge/player.getWeapon().requiredCharge;
			for(int i = 0; i < chargeCount; i++){
				drawing.fillRect(834, 32+(699* i)/chargeCount, 92, 4);
			}
		}
		
		if (isDebug) {
			//Debug
			Graphics debugText = img.getGraphics();
			debugText.setColor(Color.red);
			debugText.drawString("Pos: " + player.getPos().stringCoordinate(), 650, 985);
			debugText.drawString("DEBUG MODE", 550, 970);
			debugText.drawString("ZOOM: "+zoom, 550, 995);
			
		}
		
		return img;
	}
	
	private void drawStuff(int j, int i, Graphics graphic, char map[][], Coordinate pPlayer, ArrayList<Texture> txtList, int corX, int corY){
		if (map[j][i] != 'N' && map[j][i] != 'Q') {
			//System.out.println("X: "+picX+" Y: "+((j - (pPlayer.getY() - 9)) * 40)+corY);
			//System.out.println(corX+" "+corY);
			int picX = doubleToInt(((j - (pPlayer.getX() - 9)) * 40*zoom)+corX*zoom-360*(zoom-1));
			int picY = doubleToInt(((i - (pPlayer.getY() - 9)) * 40*zoom)+corY*zoom-360*(zoom-1));
			
			//picX = picX + corX;
			//picY = picY + corY;
			if (map[j][i] == '#') {
				graphic.drawImage(normalWall, picX, picY,
						doubleToInt(normalWall.getWidth()*zoom),doubleToInt(normalWall.getHeight()*zoom) ,new JLabel());
			} else if (map[j][i] == 'C') {
				graphic.setColor(Color.lightGray);
				graphic.fillRect(picX, picY, doubleToInt(40*zoom), doubleToInt(40*zoom));
			} else if (map[j][i] == 'M') {
				graphic.drawImage(water, picX, picY,
						doubleToInt(water.getWidth()*zoom), doubleToInt(water.getHeight()*zoom), new JLabel());
			} else if (map[j][i] == 'F') {
				graphic.drawImage(missingTexture, picX, picY,
						doubleToInt(missingTexture.getWidth()*zoom), doubleToInt(missingTexture.getHeight()*zoom), new JLabel());
			}
			
			//PLACEHOLDER
			else if (plcHolder.contains(String.valueOf(map[j][i])) && Texture.textureExists(txtList, map[j][i])) {
				//System.out.println("i: "+i+"; j: "+j);
				graphic.drawImage(Texture.getTXTfromList(map[j][i], txtList).getTexture(), picX, picY,doubleToInt(Texture.getTXTfromList(map[j][i], txtList).getTexture().getWidth()*zoom), doubleToInt(Texture.getTXTfromList(map[j][i], txtList).getTexture().getHeight()*zoom), new JLabel());
			} else {
				graphic.drawImage(missingTexture, picX, picY,
						doubleToInt(missingTexture.getWidth()*zoom), doubleToInt(missingTexture.getHeight()*zoom), new JLabel());
			} 
		}
	}
	
	/**
	 * 
	 * @param ch
	 * @return background color
	 */
	public static Color charIntoColor(char ch){
		if(ch =='C' || ch == 'S'|| ch == '#' || ch == 'P'){
			return Color.lightGray;
		}
		else if(ch == 'D' || ch == 'M'){
			return Color.blue;
		}
		else if(ch == 'W' || ch == 'B'){
			return Color.yellow;
		}
		else if(ch == 'E' || ch == 'L' || ch == 'Z'){
			return Color.orange;
		}
		else if(ch == 'I'){
			return Color.gray;
		}
		else{
			return Color.magenta;
		}
	}
	private BufferedImage getPlayerImg(char view){
		switch(view){
		case 'u':
			return playerUp;
		case 'd':
			return playerDown;
		case 'l':
			return playerLeft;
		case 'r':
			return playerRight;
		default:
			System.out.println("RENDERER - getPlayerIMG: DEFAULT CASE");
			return missingTexture;
		}
	}
	public Double getZoom(){
		return zoom;
	}
	
	public static int doubleToInt(Double value){
		return value.intValue();
	}
	/**
	 * Manipulates the current zoom factor.
	 * 1 = standard
	 * Zoom factor will not change, if value equals zero (since there would be nothing to render).
	 * @param value
	 */
	public void setZoom(Double value){
		if(value != 0){
			zoom = value;
		}
	}
	/**
	 * Shifts the current zoom value by the given value.
	 * Will skip zero, if the result is zero.
	 * @param value
	 */
	public void shiftZoom(Double value){
		if(zoom+value == 0){
			zoom = zoom+value+value;
		}
		else{
			zoom = zoom + value;
		}
	}
	public static BufferedImage getMissingTexture(){
		try {
			return ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/MapObjects/missingTexture.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("KRITISCHER FEHLER: Renderer konnte fehlende Textur nicht laden!");
			System.exit(0);
			return null;
		}
	}
}
