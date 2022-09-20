package listener;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import gui.MainGUI;
import other.App;
import other.FileReader;
import other.SoundPlayer;

public class SmartphoneListener implements KeyListener{

	private MainGUI parent;
	private String location;
	private ArrayList<App> appList;
	private int selected = 0;
	private BufferedImage frame;
	private String pushText;
	private boolean speed = false;
	
	
	public SmartphoneListener(MainGUI pParent){
		parent = pParent;
		pushText = "";
		parent.getPlayer().setFrozen(true);
		appList = new ArrayList<App>();
		 try {
				frame = ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/Other/phoneApps/select.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 if(parent.getPool().online){
			 try {
				parent.setOverPic(ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/OverPic/phoneOnline.png")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 location = "ONMENU";
		 }
		 else{
			 try {
					parent.setOverPic(ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/OverPic/phoneOffline.png")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					parent.getPlayer().setFrozen(false);
				}
				 location = "OFFMENU";
		 }
		 loadApps();
	}
	
	public SmartphoneListener(MainGUI pParent, String loc, String pushM){
		pushText = pushM;
		parent = pParent;
		parent.getPlayer().setFrozen(true);
		location = loc;
		appList = new ArrayList<App>();
		try {
			frame = ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/Other/phoneApps/select.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loadApps();
	}
	
	public void setLocation(String loc){
		location = loc;
	}
	public String getLocation(){
		return location;
	}
	
	private void loadApps(){
		switch(location){
		case"ONMENU":
			appList.add(new App("settings", 130, 768, App.SETTINGS()));
			appList.add(new App("logout", 245, 768, App.LOGOUT()));
			break;
		case"SETTINGS":
			break;
			default:
				
		}
		display();
	}
	
	private void display(){
		//reloading
		 if(parent.getPool().online && location.equals("ONMENU")){
			 try {
				parent.setOverPic(ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/OverPic/phoneOnline.png")));
				onMenuText();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 else if(location.equals("SETTINGS")){
			 try {
					parent.setOverPic(ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/OverPic/settings.png")));
					settingsScreen();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 }
		 else if(location.equals("PUSH")){
			 try {
					parent.setOverPic(ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/OverPic/phone.png")));
					Graphics txtMessage = parent.getOverPic().getGraphics();
					txtMessage.setColor(Color.black);
					txtMessage.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
					txtMessage.drawString("NEUE NACHRICHT:", 125, 110);
					if(pushText.contains("#NEXT#")){
						String[] textArray = pushText.split("#NEXT#");
						for(int i = 0; i < textArray.length; i++){
							txtMessage.drawString(textArray[i], 125, 135+(i*25));
						}
					}
					else{
					txtMessage.drawString(pushText, 125, 110);
					}
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 }
		for(int i = 0; i < appList.size(); i++){//Drawing Apps
			parent.getOverPic().getGraphics().drawImage(appList.get(i).icon, appList.get(i).xPos, appList.get(i).yPos, new JLabel());
		}
		if(appList.size()!=0){//Drawing select frame
			parent.getOverPic().getGraphics().drawImage(frame, appList.get(selected).xPos-10, appList.get(selected).yPos-10, new JLabel());
		}
	}
	
	private void onMenuText(){
		
			Graphics infoTxt = parent.getOverPic().getGraphics();
			infoTxt.setFont(new Font(Font.DIALOG, Font.BOLD, 25));
			infoTxt.setColor(Color.black);
			infoTxt.drawString("Angemeldet als: "+parent.getPlayer().getName(), 125, 110);
			//Verbindungsqualität
			if(parent.getPlayer().healthPercentage() > 60){
				infoTxt.setColor(Color.green);
				infoTxt.drawString("AUSGEZEICHNET", 350, 575);
			}
			else if(parent.getPlayer().healthPercentage() > 15){
				infoTxt.setColor(Color.orange);
				infoTxt.drawString("OKAY", 350, 575);
			}
			else{
				infoTxt.setColor(Color.red);
				infoTxt.drawString("INSTABIL", 350, 575);
			}
			//Kapselstatus
			infoTxt.setColor(Color.green);
			infoTxt.drawString("GUT - KEINE WARTUNG NÖTIG", 270, 605);
			
			//Offlinestatus
			infoTxt.setColor(Color.black);
			infoTxt.drawString("Niemand möchte dich stören", 270, 635);
			
			//Optimismus
			infoTxt.drawString(Integer.toString(parent.getPool().optimistic), 270, 665);
			//Pessimismus
			infoTxt.drawString(Integer.toString(parent.getPool().pessimistic), 270, 695);
			//Persönlichkeitsanalyse
			infoTxt.drawString("DEMO LÄUFT", 360, 720);
			//Aktuelle Stimmung
			if(parent.getPool().getDiff() > 0){
				infoTxt.setColor(Color.green);
				infoTxt.drawString("OPTIMISTISCH", 380, 752);
			}
			else if(parent.getPool().getDiff() == 0){
				infoTxt.setColor(Color.green);
				infoTxt.drawString("AUSGEGLICHEN", 380, 752);
			}
			else{
				infoTxt.setColor(Color.red);
				infoTxt.drawString("PESSIMISTISCH", 380, 752);
			}
			
	}
	
	private void settingsScreen(){
		Graphics sachen = parent.getOverPic().getGraphics();
		sachen.setColor(Color.green);
		sachen.fillRect(148+parent.refreshTick.getDelay(),146 , 10, 26);
		sachen.setFont(new Font(Font.DIALOG, Font.BOLD, 25));
		sachen.setColor(Color.black);
		sachen.drawString(1000/parent.refreshTick.getDelay()+" FPS", 290, 225);
		sachen.drawString(parent.refreshTick.getDelay()+" ms", 290, 250);
	}
	
	
	

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT){
			if(selected != appList.size()-1 && appList.size()!=0 && location.equals("ONMENU")){
				parent.getSoundPlayer().play("menu");
				selected++;
				display();
			}
			else if(location.equals("SETTINGS")){
				if(parent.refreshTick.getDelay() < 500){
					parent.refreshTick.stop();
					if(speed && parent.refreshTick.getDelay() < 495){
						parent.refreshTick.setDelay(parent.refreshTick.getDelay() + 5);
					}
					else{
						parent.refreshTick.setDelay(parent.refreshTick.getDelay() + 1);
					}
					parent.refreshTick.start();
					display();
				}
			}
		}
		else if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT){
			if(selected != 0 && appList.size()!= 0 && location.equals("ONMENU")){
				parent.getSoundPlayer().play("menu");
				selected--;
				display();
			}
			else if(location.equals("SETTINGS")){
				if(parent.refreshTick.getDelay() > 1){
					parent.refreshTick.stop();
					if(speed && parent.refreshTick.getDelay() > 5){
						parent.refreshTick.setDelay(parent.refreshTick.getDelay() - 5);
					}
					else{
						parent.refreshTick.setDelay(parent.refreshTick.getDelay() - 1);
					}
					parent.refreshTick.start();
					display();
				}
			}
			
		}
		else if(e.getKeyCode() == KeyEvent.VK_X){
			if (location.equals("ONMENU") || location.equals("OFFMENU")) {
				appList.clear();
				parent.setOverPic(null);
				parent.getPlayer().setFrozen(false);
				if (!parent.getSoundPlayer().isPlaying()) {
					parent.getSoundPlayer().play("equip");
				}
				parent.normalListener();
				parent = null;
			}
			else if(location.equals("SETTINGS") || location.equals("PUSH")){
				if (!parent.getSoundPlayer().isPlaying()) {
					parent.getSoundPlayer().play("talk");
				}
				location = "ONMENU";
				loadApps();
				display();
			}
		}
		else if(e.getKeyCode() == KeyEvent.VK_SPACE){
			if(appList.size()!=0){
				if(appList.get(selected).name.equals("logout")){
					if (!parent.getSoundPlayer().isPlaying()) {
						parent.getSoundPlayer().play("talk");
					}
					parent.getPlayer().setFrozen(false);
					parent.removeOverPic();
					parent.logout();
					parent.normalListener();
					parent = null;
				}
				else if(appList.get(selected).name.equals("settings")){
					if (!parent.getSoundPlayer().isPlaying()) {
						parent.getSoundPlayer().play("talk");
					}
					location = "SETTINGS";
					appList.clear();
					display();
				}
			}
		}
		else if(e.getKeyCode() == KeyEvent.VK_SHIFT){
			speed = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SHIFT){
			speed = false;
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	
}
