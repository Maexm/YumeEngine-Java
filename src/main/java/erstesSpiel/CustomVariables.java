package erstesSpiel;

public class CustomVariables {

	public boolean smartphone = false;
	public boolean war = false;
	public boolean online = false;
	public int optimistic  = 0;
	public int pessimistic = 0;
	public boolean musikNervt;
	public boolean cold = false;
	public boolean easterEggRoom = false;
	public boolean easterEggRoomRadio = false;
	public boolean easterEggBeach = false;
	public boolean battleKnown = false;
	
	public void shiftOptimistic(int value){
		optimistic = optimistic + value;
	}
	public void shiftPessimistic(int value){
		pessimistic = pessimistic + value;
	}
	public int getDiff(){
		return optimistic - pessimistic;
	}
	
}
