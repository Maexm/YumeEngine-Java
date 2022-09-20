package text;

import java.util.Random;

import gui.MainGUI;
import objects.Player;

public class TextGenerator {
	/**

	public static String waifu(MainGUI parent){
		Random random = new Random();
		int result = random.nextInt(5);
		if(result == 4){
			return "Hallöchen! :3";
		}
		else if(result == 3){
			return "Schön, dass du da bist, "+parent.getPlayer().getName()+"!";
		}
		else if(result == 2){
			return "Hai doumo! <3";
		}
		else if(result == 1){
			return "Na du ;)";
		}
		else if(result == 0){
			return "Heeeeey, "+parent.getPlayer().getName()+", du bist es!"; 
		}
		else{
			return "ERROR_WAIFU";
		}
	}
	
	public static String shopkeeper(Player player){
		Random random = new Random();
		int result = random.nextInt(3);
		
		if(result == 2){
			return "Was kann ich für dich tun?";
		}
		else if(result == 1){
			return "Willkommen! Hier gibts was fürs Geld!";
		}
		else if(result == 0){
			return "Irasshaimase! Ich hoffe, du hast viele Münzen dabei! Ich hab hier jedenfalls viel Ware!";
		}
		else{
			return "ERROR_SHOPKEEPER";
		}
	}
	
	public static String infoPerson(int count, MainGUI parent){
		if(count > 1){
			parent.playSound("beepClear");
		}
		if(parent.getMapName().equals("MainArea")){
			switch (count){
			case 1: 
				parent.incTextCount();
				return "Willkommen, "+parent.getPlayer().getName()+"!";
			case 2:
				parent.incTextCount();
				return "Das spiel wird derzeit entwickelt, weshalb noch kein richtiges Tutorial verfügbar ist!";
			case 3:
				parent.incTextCount();
				return "Jedenfalls... Nun ja...";
			case 4:
				parent.incTextCount();
				return "A...A...Also...links da drüben befindet sich DER SHOP";
			case 5:
				parent.incTextCount();
				return "Dieser ist......noch nicht funktionsfähig...";
			case 6:
				parent.incTextCount();
				return "A...Aber DER STRAND rechts von dir, der funktioniert!";
			case 7:
				parent.incTextCount();
				return "Und der Raum hier neben mir, DIE ARENA...";
			case 8:
				parent.incTextCount();
				return "...funktionert auch noch nicht...";
			case 9:
				parent.incTextCount();
				return "Aber, siehst du die weiße Person dort links von uns?";
			default:
				parent.resetCount();
				return "Das ist ein MEDIC. Sprich mit ihr und sie heilt dich!";
			}
			
		}
		if(count == 1){
			parent.resetCount();
			return "Hier wird gearbeitet!";
		}
		return "ERROR";
	}
	public static String medic(Player player){
		if(player.healthPercentage() == 100){
			player.repatch();
			return "Du bist schon geheilt, aber wir gehen nochmal auf Nummer sicher!";
		}
		else if(player.healthPercentage() > 90){
			player.repatch();
			return "Man sollte immer im PERFEKTEN Zustand sein, nicht wahr?";
		}
		else if(player.healthPercentage() > 15){
			player.repatch();
			return "Leben wieder hergestellt! Zurück in den Kampf mit dir!";
		}
		else{
			player.repatch();
			return "Du kommst zur Rechten Zeit! Niemals aufgeben!";
		}
	}
	**/
}
