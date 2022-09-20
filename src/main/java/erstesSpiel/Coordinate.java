package erstesSpiel;

public class Coordinate {
	private int x;
	private int y;
	
	/**
	 * Creates a new coordinate on the position xValue and yValue.
	 * @param xValue
	 * @param yValue
	 */
	public Coordinate(int xValue, int yValue){
		x = xValue;
		y = yValue;
	}
	/**
	 * 
	 * @return Returns the current x value
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * 
	 * @return Returns the current y value
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * Exchanges the current coordinate with a new coordinate
	 * @param xValue exchanges the current x value with a new x value
	 * @param yValue exchanges the current y value with a new y value
	 */
	public void setCoordinate(int xValue, int yValue){
		x = xValue;
		y = yValue;
	}
	
	/**
	 * Shifts the currrent coordinate in a certain direction
	 * @param xValue x-direction
	 * @param yValue y-direction
	 */
	public Coordinate shift(int xValue,  int yValue){
		x = x + xValue;
		y = y + yValue;
		return this;
	}
	
	/**
	 * Returns a String with this coordinate's x and y value in the following form: "x;y".
	 * This might be usefull, if you want to print out this coordinate's position quickly.
	 * @return
	 */
	public String stringCoordinate(){
		return x+";"+y;
	}
	
	/**
	 * Compares whether or not this coordinate has the same position as the given compare object.
	 * @param compareObject
	 * @return
	 */
	public boolean isSameAs(Coordinate compareObject){
		if(x == compareObject.getX() && y == compareObject.getY()){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Compares whether or not the given values match this coordinate's position.
	 * @param xCompare
	 * @param yCompare
	 * @return
	 */
	public boolean is(int xCompare, int yCompare){
		if(x == xCompare && y == yCompare){
			return true;
		}
		else{
			return false;
		}
	}
	/**
	 * Returns the direction from THIS coordinate to a DESTINATION coordinate.
	 * Do not mix up destination coordinate and current coordinate, or else you might get weird results!
	 * @param destination
	 * @return the direction
	 */
	public Coordinate getDirection(Coordinate destination){
		return new Coordinate(destination.getX()-x, destination.getY()-y);
	}
	
	/**
	 * Compares which ABSOLUTE value (x or y) is the biggest.
	 * @return x if x is bigger, y if y is bigger, n if both are the same
	 */
	public char getDominating(){
		if(Math.abs(x) > Math.abs(y)){
			return 'x';
		}
		else if(Math.abs(y) > Math.abs(x)){
			return 'y';
		}
		else{
			return 'n';
		}
	}
	
	/**
	 * Returns wether or not this coordinate is in the given x range.
	 * @param pStart
	 * @param pEnd
	 * @return
	 */
	public boolean xInRange(int pStart, int pEnd){
		if(x >= pStart && x <= pEnd){
			return true;
		}
		return false;
	}
	
	/**
	 * Returns wether or not this coordinate is in the given y range.
	 * @param pStart
	 * @param pEnd
	 * @return
	 */
	public boolean yInRange(int pStart, int pEnd){
		if(y >= pStart && y <= pEnd){
			return true;
		}
		return false;
	}
	
	/**
	 * Creates and returns a copy of this coordinate.
	 * @return
	 */
	public Coordinate copy(){
		return new Coordinate(x, y);
	}
}
