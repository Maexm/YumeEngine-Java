package erstesSpiel;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import gui.MainGUI;
import other.FileReader;

public class NewGameStart {

	public static void main(String[] args) {
		File Directory = new File("./YUMEFiles");
		if(!Directory.exists()){
			System.out.println("Spielverzeichnis existiert nicht. Erstelle Spielverzeichnis...");
			if(Directory.mkdir()){
				System.out.println("Spieleverzeichnis "+Directory.getPath()+" wurde erfolgreich erstellt!");
			}
			else{
				System.out.println("FEHLER: Konnte Verzeichnis "+Directory.getPath()+" nicht erstellen!");
			}
		}
		else{
			System.out.println("Spieleverzeichnis existiert schon! "+ Directory.getPath());
		}
		System.out.println("##############################"+"\n"+"Starte YUME ENGINE DEMO 1"+"\n"+"Das Spiel läuft auf der von Maxim Oleschenko programmierten YUME ENGINE"+"\n"+"Wenn du diesen Text hier ließt heißt das, dass du die Konsole geöffnet hast, was cool ist!"+"\n"+"##############################");
		MainGUI start = new MainGUI();

	}
	

}
