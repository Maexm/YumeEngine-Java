package other;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import erstesSpiel.Coordinate;
import gui.MainGUI;
import objects.Player;

public class FileReader {

	private Scanner scanner;
	
	/**
	 * Opens a file
	 * @param fileName
	 */
	public static String path(){
		return "./YUMEFiles/";
	}
	private void openFile(String fileName){
		try{
			scanner = new Scanner(new File(fileName));
		}catch(Exception e){
			System.out.println("FileReader - FEHLER: Konnte Datei nicht öffnen: "+fileName);
		}
	}
	
	/**
	 * 
	 * @param fileName
	 * @return the map as a two-dimensional char array
	 */
	public char[][] readFile(String fileName){
		
		
		openFile(path()+"NewGeeemu/Maps/"+fileName+".txt");
		String content = "";
		int height = 0;
		int width = 0;
		
		//Reading from txt-file
		while(scanner.hasNextLine()){
			//width++;
			content = content + scanner.nextLine();
			height++;
		}
		width = content.length()/height;
		
		//Creating Map-Array
		char[][] map = new char[width][height];
		
		char[] tempMap = content.toCharArray();//Temporary array
		//System.out.println(content);
		//System.out.println("HEIGHT: "+height+ "WIDTH: "+width);
		
		//Converting into 2d array
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				//System.out.println(j+width*i);
				map[j][i] = tempMap[j+width*i];
			}
		}
		//printMap(map);
		closeFile();
		return map;
	}
	
	/**
	 * Closes the file
	 */
	private void closeFile(){
		scanner.close();
	}
	
	public String nextMap(Coordinate pos, String mapName){
		openFile(path()+"NewGeeemu/Maps/MapData/"+mapName+".txt");
		String nxtMp = "";
		while(scanner.hasNext()){
			if(scanner.next().equals(Integer.toString(pos.getX())) && scanner.next().equals(Integer.toString(pos.getY()))){
				nxtMp = scanner.next();
				break;
			}
			else{
				scanner.next();
				scanner.next();
			}
		}
		closeFile();
		return nxtMp;
	}
	public Coordinate getSpawnPos(String curMap, String oldMap){
		openFile(path()+"NewGeeemu/Maps/MapData/"+curMap+".txt");
		int help = 0;
		//Searching for oldMap in file
		while(scanner.hasNext()){
			if(scanner.next().equals(oldMap)){
				break;
			}
			else{
				help++;
			}	
		}
		closeFile();
		openFile(path()+"NewGeeemu/Maps/MapData/"+curMap+".txt");
		//Going to line
		while(help > 2){
			scanner.next();
			help = help - 1;
		}
		Coordinate newPos = new Coordinate(scanner.nextInt(), scanner.nextInt());
		closeFile();
		
		return newPos;
	}
	
	public Player setUpPlayer(MainGUI creator){
		Player bornPlayer = new Player(new Coordinate(4,4),1,20,null,10, "PLAYER", creator, 3, 1000);
		openFile(path()+"NewGeeemu/gameData.txt");
		//Name
		scanner.next();
		bornPlayer.setName(scanner.next());
		//Health
		scanner.next();
		bornPlayer.setHealth(scanner.nextInt());
		//MaxHealth
		scanner.next();
		bornPlayer.setMaxHealth(scanner.nextInt());
		//Money
		scanner.next();
		bornPlayer.setMoney(scanner.nextInt());
		//Level or stage
		scanner.next();
		bornPlayer.setStage(scanner.nextInt());
		//WaifuValue
		scanner.next();
		bornPlayer.setMetHer(scanner.nextBoolean());
		//Activate her or not
		scanner.next();
		bornPlayer.setMeyMeetHer(scanner.nextBoolean());
		//Must activate her or not
		scanner.next();
		bornPlayer.setMustMeetHer(scanner.nextBoolean());
		
		//printing stats and returning
		bornPlayer.printStats();
		return bornPlayer;
		
	}
	
	
	
	/**
	 * DEBUG METHOD - prints the map char into console
	 * @param map
	 * @param height
	 */
	public static void printMap(char[][] map){
		for(int i = 0; i < map[1].length; i++){
			for(int j = 0; j < map.length; j++){
				System.out.print(map[j][i]);
			}
			System.out.print("\n");
		}
	}
	
	public static String getDialogue(int index, String language){
		try {
			Scanner scan = new Scanner(new File(path()+"/NewGeeemu/Dialogue/"+language+".txt"));
			for(int i = 0; i <= index; i++){
				if(scan.hasNextLine()){
					if(i == index){
						String temp = scan.nextLine();
						scan.close();
						return temp;
					}
					else{
						scan.nextLine();
					}
				}
				else{
					scan.close();
					return "ERROR - #"+index+" NOT FOUND";
				}
			}
			scan.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "ERROR - #"+index+" NOT FOUND";
	}
}

