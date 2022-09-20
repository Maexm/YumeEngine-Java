package objects;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

import erstesSpiel.Coordinate;
import gui.MainGUI;
import listener.MobTimeListener;
import other.SoundPlayer;
import rendering.Effect;
import rendering.Texture;
import weapons.Weapon;

public abstract class GameObject{

	protected Coordinate pos;
	protected Coordinate oldPos;
	private int health;
	private int maxHealth;
	private boolean invincible;
	private boolean alive;
	private String name;
	private boolean canMove;
	private int attack;
	private String objectType;
	protected MainGUI parent;
	private char direction;
	private boolean frozen;
	private boolean moving;
	protected Timer moveSpeed;
	private int corX;
	private int corY;
	int speedo;
	protected Texture[] normalLook = new Texture[4];
	protected Texture[] secondLook = new Texture[4];
	public SoundPlayer cry;
	public int extraHeight;
	protected String deathSound;
	protected Timer acting;
	protected boolean alternative = false;
	protected Weapon equipped;
	protected String walkSound = "NONE";
	protected String attackSound = "NONE";
	protected String hurtSound = "NONE";
	
	
	public GameObject(Coordinate pPos, int pHealth, int pMaxHealth, String pName, int pAttack, String oType, MainGUI pParent, int movementSpeed, int pActingSpeed){
		speedo = 1;
		objectType = oType;
		direction = 'd';
		attack = pAttack;
		oldPos = new Coordinate(0,0);
		pos = pPos;
		canMove = true;
		moving = false;
		parent = pParent;
		cry = new SoundPlayer(parent);
		if(movementSpeed <= 0){
			movementSpeed = 1;
		}
		moveSpeed = new Timer(movementSpeed, new MobTimeListener(this, "MOVEMENT"));
		acting = new Timer(pActingSpeed, new MobTimeListener(this, "ACT"));
		if(objectType.equals("ENEMY")){
			acting.start();
		}
		corX = 0;
		corY = 0;
		if(pName == null){
			name = "UNNAMED";
		}
		else{
			name = pName;
		}
		if(pHealth == 0){
			health = 10;
			System.out.println("HINWEIS: Neues Objekt "+name+" hat 10 Leben, da 0 angegeben wurde");
		}
		else{
			health = pHealth;
		}
		if(pMaxHealth == 0){
			maxHealth = pHealth;
		}
		else{
			maxHealth = pHealth;
		}
		if(health > maxHealth){
			System.out.println("HINWEIS: "+name+" hat mehr Leben ("+health+") als erlaubt ("+maxHealth+")!");
		}
		
		invincible = false;
		alive = true;
		deathSound = "NPCfaint";
		loadNormal();
		System.out.println("Erstelle neues Objekt '"+name+"' vom Typ '"+objectType+"' mit "+health+" von "+maxHealth+" Leben, "+attack+" Angriff und "+movementSpeed+" Schnelligkeit, bei "+pos.stringCoordinate());
	}
	
	
	/**
	 * Allows to manipulate the objects health
	 * Health = 0 kills this object 
	 * @param value
	 */
	public void setHealth(int value){
		health = value;
		if(objectType.equals("PLAYER")){
			parent.tick("OBJECTTICKplayerChangedHealth");
		}
		if(health <= 0){
			kill();
		}
		else if(healthPercentage() <= 15 && objectType.equals("PLAYER")){
			parent.startLowHealthNoise();
		}
		else if(healthPercentage() >= 15 && objectType.equals("PLAYER")){
			parent.stopLowHealthNoise();
		}
	}
	
	/**
	 * Manipulates current health by adding or substracting value to/from the current health
	 * Health = 0 kills this object 
	 * @param value
	 */
	public void shiftHealth(int value){
		health = health + value;
		if(objectType.equals("PLAYER")){
			parent.tick("OBJECTTICKplayerChangedHealth");
		}
		if(health <= 0){
			kill();
		}
		else if(healthPercentage() <= 15 && objectType.equals("PLAYER")){
			parent.startLowHealthNoise();
		}
		else if(healthPercentage() > 15 && objectType.equals("PLAYER")){
			parent.stopLowHealthNoise();
		}
	}
	public void hurt(int value){
		if(!invincible || (objectType.equals(OBJECTTYPE_PLAYER()) && !parent.debugMode)){
			shiftHealth(-value);
			if(!hurtSound.equals("NONE")){
				cry.play(hurtSound);
			}
		}
	}
	
	/**
	 * Kills this object
	 */
	public void kill(){
		health = 0;
		alive = false;
		System.out.println(name+" ist gestorben!");
		moveSpeed.stop();
		if(objectType.equals("PLAYER")){
			parent.playerDied();
		}
		else{
			if(deathSound != "NONE"){
				parent.getSoundPlayer().play(deathSound);
			}
			stop();
		}
	}
	
	public boolean isAlive(){
		return alive;
	}
	
	public boolean isInvicible(){
		return invincible;
	}
	public String getName(){
		return name;
	}
	public void setPos(Coordinate cor){
		oldPos = pos;
		pos = cor;
	}
	public Coordinate getPos(){
		return pos;
	}
	public void movePos(int x, int y){
		if(!objectType.equals("PLAYER")){
			if(Math.abs(x) > Math.abs(y)){
				if(x < 0){
					turn('r');
				}
				else{
					turn('l');
				}
			}
			else{
				if(y < 0){
					turn('d');
				}
				else{
					turn('u');
				}
			}
		}
		if(moving){
			moveSpeed.stop();
			corX = 0;
			corY = 0;
		}
		if (parent.getWorld().legalMove(pos.getX()+x, pos.getY()+y)) {
			oldPos.setCoordinate(pos.getX(), pos.getY());
			pos.shift(x, y);
			setMoving(true);
			if(!walkSound.equals("NONE")){
				cry.play(walkSound);
			}
		}
	}
	public void shiftPos(int x, int y){
		pos.shift(x, y);
	}
	public void undoMove(){
		pos.setCoordinate(oldPos.getX(), oldPos.getY());;
	}
	public int getHealth(){
		return health;
	}
	public boolean getCanMove(){
		return canMove;
	}
	public void setMovable(boolean value){
		canMove = value;
	}
	public void setName(String newName){
		name = newName;
	}
	public int healthPercentage(){
		return 100*health/maxHealth;
	}
	public void setMaxHealth(int newMax){
		maxHealth = newMax;
	}
	public void repatch(){
		parent.stopLowHealthNoise();
		health = maxHealth;
		alive = true;
	}
	public int getMaxHealth(){
		return maxHealth;
	}
	public int getAttack(){
		return attack;
	}
	public void setAttack(int value){
		attack = value;
	}
	public void shiftAttack(int value){
		attack = attack + value;
	}
	public String getType(){
		return objectType;
	}
	public void setInvincible(boolean isInvincible){
		invincible = isInvincible;
	}
	public char getDirection(){
		return direction;
	}
	public void turn(char newDirection){
		direction = newDirection;
	}
	public boolean isFrozen(){
		return frozen;
	}
	public void setFrozen(boolean value){
		frozen = value;
	}
	public boolean isMoving(){
		return moving;
	}
	public int getCorX(){
		return corX;
	}
	public int getCorY(){
		return corY;
	}
	public int getSpeed(){
		return speedo;
	}
	public void setSpeed(int value){
		speedo = value;
	}
	public void setMoving(boolean value){
		moving = value;
		if(moving && corX == 0 && corY == 0){
			switch(direction){
			case 'u':
				corY = -40;
				break;
			case 'd':
				corY = 40;
				break;
			case 'l':
				corX = -40;
				break;
			case 'r':
				corX = 40;
				break;
				default:
			}
			moveSpeed.start();
		}
		
	}
	/**
	 * 
	 * @return the coordinate, the object looks at
	 */
	public Coordinate looksAt(){
		switch(direction){
		case 'u':
			return new Coordinate(pos.getX(), pos.getY()-1);
		case 'd':
			return new Coordinate(pos.getX(), pos.getY()+1);
		case 'l':
			return new Coordinate(pos.getX()-1, pos.getY());
		case 'r':
			return new Coordinate(pos.getX()+1, pos.getY());
			default:
				return null;
		}
	}
	
	/**
	 * Triggers, when the objects has moved.
	 */
	public void tick(){//Adjusting move speed
		//System.out.println(corX+" "+corY);
		if(Math.abs(corX) < speedo && (direction == 'l' || direction == 'r') || Math.abs(corY) < speedo && (direction == 'u' || direction == 'd')){//Stopping move animation, when finished
			//System.out.println("NOT MOVING ANYMORE");
			setMoving(false);
			corX = 0;
			corY = 0;
			moveSpeed.stop();
			if(objectType.equals("PLAYER")){//Notifying world that the player has finished moving
				parent.getWorld().tick();
			}
		}
		if(corX < 0){//Adjusting correction variables
			corX = corX + speedo;
		}
		else if(corX > 0){
			corX = corX - speedo;
		}
		else if(corY < 0){
			corY = corY + speedo;;
		}
		else if(corY > 0){
			corY = corY - speedo;
		}
	}
	public BufferedImage display(){
		if(secondLook[0] != null){
			switch(direction){
			case 'u':
				return secondLook[1].getTexture();
			case 'd':
				return secondLook[0].getTexture();
			case 'l':
				return secondLook[2].getTexture();
			case 'r':
				return secondLook[3].getTexture();
				default:
					return null;
			}
		}
		else{
			switch(direction){
			case 'u':
				if(normalLook[1] != null){
					return normalLook[1].getTexture();
				}
			case 'd':
				return normalLook[0].getTexture();
			case 'l':
				if(normalLook[2] != null){
					return normalLook[2].getTexture();
				}
			case 'r':
				if(normalLook[3] != null){
					return normalLook[3].getTexture();
				}
				default:
					return normalLook[0].getTexture();
			}
		}
	}
	
	public void stop(){
		if(!objectType.equals(GameObject.OBJECTTYPE_PLAYER())){
			System.out.println("Lösche '"+name+"' vom Typ '"+objectType+"' aus der Objektliste");
		}
		else{
			System.out.println("Lösche '"+name+"' vom Typ '"+objectType+"'!");
		}
		cry.clearQueue();
		cry.stopMusic();
		if(normalLook[0] != null){
			normalLook[0].stopTimer();
		}
		if(normalLook[1] != null){
			normalLook[1].stopTimer();
		}
		if(normalLook[2] != null){
			normalLook[2].stopTimer();
		}
		if(normalLook[3] != null){
			normalLook[3].stopTimer();
		}
		acting.stop();
		moveSpeed.stop();
		if(equipped != null){
			unequip();
		}
		additionalStop();
		parent.getObjectList().remove(this);
		parent.tick("BATTLETICK"+name+"Died");
		parent = null;
	}
	
	/**
	 * Loads all textures for the object.
	 */
	public abstract void loadNormal();
	public abstract void additionalStop();
	/**
	 * Universal tick method for game objects.
	 * MainGUI will trigger this tick, if it receives a tick that contains "OBJECTTICK".
	 * @param action
	 */
	public abstract void tickAction(String action);
	
	public static String OBJECTTYPE_PLAYER(){
		return "PLAYER";
	}
	public static String OBJECTTYPE_OBJECT(){
		return "OBJECT";
	}
	public static String OBJECTTYPE_NPC(){
		return "NPC";
	}
	public static String OBJECTTYPE_ENEMY(){
		return "ENEMY";
	}
	public static String OBJECTTYPE_BATTLEOBJECT(){
		return "BATTLEOBJECT";
	}
	public static String OBJECTTYPE_ALLY(){
		return "ALLY";
	}
	public Weapon getWeapon(){
		return equipped;
	}
	public void equipWeapon(Weapon pWeapon){
		if(pWeapon != null){
			equipped = pWeapon;
		}
	}
	public void unequip(){
		equipped.destroy();
		equipped = null;
	}
	
	/**
	 * Greift alles, außer Objekte eigenen Types, auf der gegebenen Koordinate an.
	 * @param attackDestination
	 */
	public void attack(Coordinate attackDestination){
		if(parent.getPlayer().getPos().isSameAs(attackDestination) && parent.getPlayer().getHealth() > 0 && objectType != "PLAYER"){
			parent.getPlayer().hurt(attack);
			if(!attackSound.equals("NONE")){
				cry.play(attackSound);
			}
		}
		for(int i = 0; i < parent.objectList.size(); i++){
			if(parent.objectList.get(i).getPos().isSameAs(attackDestination) && parent.objectList.get(i) != this && parent.objectList.get(i).getHealth() > 0 && parent.objectList.get(i).getType() != getType()){
				parent.objectList.get(i).hurt(attack);
			}
		}
	}
	public MainGUI getParent(){
		return parent;
	}
	public int getActingDelay(){
		return acting.getDelay();
	}
	public void setActingDelay(int value){
		acting.stop();
		acting.setDelay(value);
		acting.start();
	}
	public void act(){
		int xPlayer = parent.getPlayer().getPos().getX();
		int yPlayer = parent.getPlayer().getPos().getY();
		if(pos.getDirection(parent.getPlayer().getPos()).getDominating() == 'x'){
			if(pos.getX() < xPlayer){//Spieler RECHTS von Objekt
				if(parent.getMapArray()[pos.getX()+1][pos.getY()] != '#' && parent.getWorld().legalMove(pos.getX()+1, pos.getY())){//Wenn rechts frei
					movePos(1, 0);
				}
				else if(parent.getMapArray()[pos.getX()][pos.getY()-1] != '#' && parent.getWorld().legalMove(pos.getX(), pos.getY()-1)){//Wenn Alternative nach oben frei
					movePos(0,1);
				}
				else if(parent.getMapArray()[pos.getX()][pos.getY()+1] != '#' && parent.getWorld().legalMove(pos.getX(), pos.getY()+1)){//Wenn Alternative nach unten frei
					movePos(0,-1);
				}
				else if(parent.getMapArray()[pos.getX()-1][pos.getY()] != '#' && parent.getWorld().legalMove(pos.getX()-1, pos.getY())){//Wenn Aternative nach links frei
					movePos(-1,0);
				}
				else{//Eingesperrt
					turn('r');
				}
			}
			else if(pos.getX() > xPlayer){//Spieler LINKS von Objekt
				if(parent.getMapArray()[pos.getX()-1][pos.getY()] != '#' && parent.getWorld().legalMove(pos.getX()-1, pos.getY())){//Wenn links frei
					movePos(-1, 0);
				}
				else if(parent.getMapArray()[pos.getX()][pos.getY()-1] != '#' && parent.getWorld().legalMove(pos.getX(), pos.getY()-1)){//Wenn Alternative nach oben frei
					movePos(0,1);
				}
				else if(parent.getMapArray()[pos.getX()][pos.getY()+1] != '#' && parent.getWorld().legalMove(pos.getX(), pos.getY()+1)){//Wenn Alternative nach unten frei
					movePos(0,-1);
				}
				else if(parent.getMapArray()[pos.getX()+1][pos.getY()] != '#' && parent.getWorld().legalMove(pos.getX()+1, pos.getY())){//Wenn Alternative nach rechts frei
					movePos(1,0);
				}
				else{//Eingesperrt
					turn('l');
				}
			}
			else{//Spieler auf gleicher X Koordinate
				if(pos.getY() < yPlayer && parent.getWorld().legalMove(pos.getX(), pos.getY()-1)){//Wenn Spieler UNTER Objekt
					movePos(0,1);
				}
				else if(parent.getWorld().legalMove(pos.getX(), pos.getY()+1)){//Wenn Spieler ÜBER Objekt
					movePos(0,-1);
				}
			}
		}
		else if(!pos.isSameAs(parent.getPlayer().getPos())){//Y oder BEIDE dominant, aber positionen sind nicht dominant
			if(pos.getY() < yPlayer){//Spieler UNTER Objekt
				if(parent.getMapArray()[pos.getX()][pos.getY()+1] != '#' && parent.getWorld().legalMove(pos.getX(), pos.getY()+1)){//Wenn unten frei
					movePos(0, 1);
				}
				else if(parent.getMapArray()[pos.getX()-1][pos.getY()] != '#' && parent.getWorld().legalMove(pos.getX()-1, pos.getY())){//Wenn Alternative nach links frei
					movePos(-1,0);
				}
				else if(parent.getMapArray()[pos.getX()+1][pos.getY()] != '#' && parent.getWorld().legalMove(pos.getX()+1, pos.getY())){//Wenn Alternative nach rechts frei
					movePos(1,0);
				}
				else if(parent.getMapArray()[pos.getX()][pos.getY()-1] != '#' && parent.getWorld().legalMove(pos.getX(), pos.getY()-1)){//Wenn Alternative nach oben frei
					movePos(0,-1);
				}
				else{//Eingesperrt
					turn('d');
				}
			}
			else if(pos.getY() > yPlayer){//Spieler ÜBER Objekt
				if(parent.getMapArray()[pos.getX()][pos.getY()-1] != '#' && parent.getWorld().legalMove(pos.getX(), pos.getY()-1)){//Wenn oben frei
					movePos(0, -1);
				}
				else if(parent.getMapArray()[pos.getX()-1][pos.getY()] != '#' && parent.getWorld().legalMove(pos.getX()-1, pos.getY())){//Wenn Alternative nach links frei
					movePos(-1,0);
				}
				else if(parent.getMapArray()[pos.getX()+1][pos.getY()] != '#' && parent.getWorld().legalMove(pos.getX()+1, pos.getY())){//Wenn Alternative nach rechts frei
					movePos(1,0);
				}
				else if(parent.getMapArray()[pos.getX()][pos.getY()+1] != '#' && parent.getWorld().legalMove(pos.getX(), pos.getY()+1)){//Wenn Alternative nach unten frei
					movePos(0,1);
				}
				else{//Eingesperrt
					turn('u');
				}
			}
			else{//Spieler auf gleicher Y Koordinate
				if(pos.getX() < xPlayer && parent.getWorld().legalMove(pos.getX()+1, pos.getY())){//Wenn Spieler RECHTS von Objekt
					movePos(1,0);
				}
				else if(parent.getWorld().legalMove(pos.getX()-1, pos.getY())){//Wenn Spieler LINKS von Objekt
					movePos(-1,0);
				}
			}
		}
		else{//Objekt auf Spieler = ANGRIFF
			attack(pos);
		}
		
	}
}
