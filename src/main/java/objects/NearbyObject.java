package objects;

import erstesSpiel.Coordinate;

public class NearbyObject {

	Coordinate pos;
	char identifier;
	
	public NearbyObject(Coordinate setPos, char what){
		pos = setPos;
		identifier = what;
	}
}
