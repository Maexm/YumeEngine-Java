package listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.imageio.ImageIO;

import gui.ErrorScreen;
import gui.MainGUI;
import other.FileReader;

public class UniversalListener implements KeyListener{
	
	private MainGUI parent;
	
	public UniversalListener(MainGUI pParent){
		parent = pParent;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_ESCAPE){
			parent.stopGame();
		}
		else if(arg0.getKeyCode() == KeyEvent.VK_F1){
			try {
				Thread.sleep(1200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			File ScreenShot = new File(System.getProperty("user.home")+"/Desktop/YUMEFiles");
			if(!ScreenShot.exists()){
				System.out.println("YUMEFiles Ordner existiert nicht. Wird erstellt...");
				if(ScreenShot.mkdir()){
					System.out.println("Verzeichnis "+ScreenShot.getPath()+" wurde erfolgreich erstellt!");
				}
				else{
					System.out.println("FEHLER: Konnte Verzeichnis "+ScreenShot.getPath()+" nicht erstellen!");
				}
			}
				//System.out.println(System.getProperty("user.home"));
				ScreenShot = new File(System.getProperty("user.home")+"/Desktop"+"/YUMEFiles"+"/screenshot_"+LocalDate.now().getYear()+"_"+LocalDate.now().getMonthValue()+"_"+LocalDate.now().getDayOfMonth()+"_"+LocalTime.now().getHour()+"_"+LocalTime.now().getMinute()+"_"+LocalTime.now().getSecond()+".png");
				try {
					ImageIO.write(parent.getRenderImage(), "png", ScreenShot);
					System.out.println("Screenshot erfolgreich gespeichert!");
					parent.UISound.play("photo");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("FEHLER BEIM SPEICHERN EINES SCREENSHOTS");
					parent.UISound.play("warn");
					//ErrorScreen.open("Konnte Screenshot nicht speichern!", "FEHLER!");
				}
			
		}
		//Printing map array
		else if(arg0.getKeyCode() == KeyEvent.VK_P){
					FileReader.printMap(parent.getMapArray());
				}
		else if(arg0.getKeyCode() == KeyEvent.VK_PLUS && parent.debugMode){
			parent.getRenderer().shiftZoom(0.1);
		}
		else if(arg0.getKeyCode() == KeyEvent.VK_MINUS && parent.debugMode){
			parent.getRenderer().shiftZoom(-0.1);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
