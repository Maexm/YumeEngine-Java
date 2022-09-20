package other;

import erstesSpiel.Coordinate;

public class PositionChecker {

	public static boolean canMove(Coordinate pos, char[][] map, boolean isDebug){
		if(!isDebug) {
			if (map[pos.getX()][pos.getY()] == '#' || map[pos.getX()][pos.getY()] == 'M'|| map[pos.getX()][pos.getY()] == 'W' || map[pos.getX()][pos.getY()] == 'S'|| map[pos.getX()][pos.getY()] == 'I' || map[pos.getX()][pos.getY()] == 'F' || map[pos.getX()][pos.getY()] == 'A') {
				return false;
			} else {
				return true;
			} 
		}
		else{
			return true;
		}
	}
	
	public static boolean isInDoor(Coordinate pos, char[][] map){
		if(map[pos.getX()][pos.getY()] == 'D'){
			return true;
		}
		else{
			return false;
		}
	}
	public static boolean isInLava(Coordinate pos, char[][] map){
		if(map[pos.getX()][pos.getY()] == 'L'){
			return true;
		}
		else{
			return false;
		}
	}
}
