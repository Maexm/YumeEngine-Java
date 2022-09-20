package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import erstesSpiel.*;
import fight.BattleManager;
import gameWorlds.*;
import listener.*;
import mapClasses.*;
import objects.*;
import other.*;
import rendering.Effect;
import rendering.PlayerAnimation;
import rendering.ScreenCreator;
import rendering.Texture;
import text.TextGenerator;
import timer.TimerJob;
import timer.TimerListener;
import weapons.Weapon;

public class MainGUI{

	private JFrame window;
	private JPanel panel;
	private Border menuBo;
	public JLabel label;
	private BufferedImage img;
	private ImageIcon icon;
	private MainWindowListener tListener;
	//MenuBar
	private JMenuBar menBar;
	private JMenu runtime;
	private JMenuItem close;
	private JMenuItem debug;
	private DebugButtonListener debugListener;
	
	
	//Other
	private Player player;
	private String mapName;
	public ArrayList<GameObject> objectList;
	private ScreenCreator screenCreator;
	private char[][] blueprint;
	private FileReader mapGetter;
	private SoundPlayer music;
	private SoundPlayer soundPlyr;
	private SoundPlayer ambient;
	private String textContent;
	private Color textColor;
	public boolean longerDialog;
	public Timer timer;
	private World curWorld;
	private StartScreenListener sListener;
	private boolean start;
	private ArrayList<Effect> effectList;
	private Effect lowHealth;
	private Timer healthNoise;
	private SoundPlayer healthNoiseSound;
	private String language = "Deutsch";
	private CustomVariables pool;
	private BufferedImage overPic;
	public ArrayList<Timer> timerList;
	public ArrayList<TimerJob> timeJobList;
	public Timer refreshTick;
	private UniversalListener alwaysListener;
	private KeyListener currentListener;
	private UniversalStateListener stateListener;
	private int fps;
	private int fpsCount;
	private BattleManager battle;
	public boolean debugMode;
	public SoundPlayer UISound;
	public String lastMap;
	String oldMap;
	
	
	/**
	 * Constructor for this class.
	 * Initializes attributes and creates the main GUI.
	 */
	public MainGUI(){
		System.out.println("Lade wichtige Objekte...");
		//System.out.println(this.getClass().getResourceAsStream("resources/NewGeeemu").toString());
		fps = 0;
		debugMode = false;
		fpsCount = 0;
		lastMap = "";
		healthNoiseSound = new SoundPlayer(this);
		healthNoise = new Timer(5,new TimerListener(this, "HEALTHNOISE"));
		soundPlyr = new SoundPlayer(this);
		music = new SoundPlayer(this);
		ambient = new SoundPlayer(this);
		UISound = new SoundPlayer(this);
		oldMap = "";
		mapName = "MainArea";
		mapGetter  = new FileReader();
		objectList = new ArrayList<GameObject>();
		screenCreator = new ScreenCreator();
		textContent = "";
		textColor = Color.white;
		longerDialog = false;
		timer = new Timer(750, new TimerListener(this, "NORMAL")); //StartMenu
		refreshTick = new Timer(12, new TimerListener(this, "REFRESH"));
		effectList = new ArrayList<Effect>();
		timerList = new ArrayList<Timer>();
		pool = new CustomVariables();
		timeJobList = new ArrayList<TimerJob>();
		battle = new BattleManager(this);
		
		//Window size
				
				System.out.println("Erstelle Fenster...");
				//Setup window and Listener
				window = new JFrame();
				window.setSize(1400, 1200);
				window.setTitle("DEMOVERSION - Yume Engine");
				try {
					window.setIconImage(ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/icon.png")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				//Setup MenuBar
				System.out.println("Erstelle Fensterkomponenten...");
				menuBo = new LineBorder(Color.cyan);
				menBar = new JMenuBar();
				runtime = new JMenu("Laufzeit");
				close = new JMenuItem("BEENDEN");
				close.addActionListener(new CloseButtonListener());
				debug = new JMenuItem("Debug");
				debug.addActionListener(debugListener = new DebugButtonListener(this));
				runtime.add(close);
				runtime.add(debug);
				menBar.add(runtime);
				//menBar.setBorder(menuBo);
				
				//Window components
				panel = new JPanel();
				label = new JLabel();
				
				//Adding components to window
				//window.setLayout(new BoxLayout(window, BoxLayout.X_AXIS));
				window.setJMenuBar(menBar);
				panel.add(label);
				window.add(panel);
				window.setName("GEEMU");
				alwaysListener = new UniversalListener(this);
				window.addKeyListener(alwaysListener);
				tListener = new MainWindowListener(this);
				System.out.println("Oeffne Fenster...");
				window.setVisible(true);
				
				//Starting startScreen
				System.out.println("Starte Startscreen...");
				sListener = new StartScreenListener(this);
				currentListener = sListener;
				window.addKeyListener(sListener);
				stateListener = new UniversalStateListener(this);
				window.addWindowListener(stateListener);
				start = true;
				timer.start();
				System.out.println("SPIEL GESTARTET");
				
				
	}
	
	public void startGame(){
		timer.stop();
		timer.setDelay(1000);
		timer.start();
		start = false;
		curWorld = new MainArea(this);
		window.removeKeyListener(sListener);
		window.addKeyListener(tListener);
		currentListener = tListener;
		//Setup player from gameDate file
				try {
					player = mapGetter.setUpPlayer(this);
					player.equipWeapon(new Weapon(10, 20 ,50, player, 500, 10, Color.blue));
					player.getWeapon().active = true;
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("KRITISCHER FEHLER: gameData existiert nicht oder ist beschädigt - TERMINIERE");
					System.exit(0);
				}
				//Setting up MapArrays
				blueprint = mapGetter.readFile(mapName);
		setRenderImage();
		refresh();
		refreshTick.start();
	}
	/**
	 * Shows the rendered picture (img) in the GUI.
	 */
	public void refresh(){
		img = screenCreator.renderEdited(player, blueprint, curWorld.getTextures(), curWorld.getBackgound(), objectList);
		screenCreator.changeText(img, textContent, textColor,pool.battleKnown);//Drawing text box with text
		screenCreator.updateHealth(img, player, debugMode, fps);//Drawing health bar
		if(lowHealth != null){//LowHealth effect (noise)
			lowHealth.create(img);
		}
		if(overPic != null){
			for(int i = 0; i < effectList.size(); i++){//Creating effects
				effectList.get(i).create(img);
			}
			img.getGraphics().drawImage(overPic, 0, 0, new JLabel());
			label.setIcon(new ImageIcon(img));
		}
		else{
			for(int i = 0; i < effectList.size(); i++){//Creating effects
				effectList.get(i).create(img);
			}
			
			label.setIcon(new ImageIcon(img));
		}
		if(timer.isRunning()){
			fpsCount++;
		}
	}
	
	/**
	 * Adds an effect to the effect list
	 * @param pEffect
	 */
	public void addEffect(Effect pEffect){
		effectList.add(pEffect);	
	}
	public void clearEffects(){
		effectList.clear();
		/**
		if(timer.isRunning()){
			timer.stop();
		}
		**/
	}
	public void removeEffect(Effect pEffect){
		for(int i = 0; i < effectList.size(); i++){
			if(effectList.get(i).isSameAs(pEffect)){
				effectList.remove(i);
				break;
			}
		}
	}
	/**
	 * Returns the picture that is currently loaded.
	 * @return img
	 */
	public BufferedImage getRenderImage(){
		return img;
	}
	public void setCustomImage(BufferedImage pPic){
		img = pPic;
	}
	
	/**
	 * Returns the visible picture
	 * @return labelIcon
	 */
	public Icon getScreen(){
		return label.getIcon();
	}
	
	/**
	 * Renders the map
	 * @param map
	 */
	public void setRenderImage(){
		img = screenCreator.renderEdited(player, blueprint, curWorld.getTextures(), curWorld.getBackgound(), objectList);
	}
	
	/**
	 * Returns the player
	 * @return player
	 */
	public Player getPlayer(){
		return player;
	}
	
/**
 * THE MOVE METHOD
 * Rendering new screen after player movement
 */

	
	public void renderAnimation(){
		img = screenCreator.renderEdited(player, blueprint, curWorld.getTextures(), curWorld.getBackgound(), objectList);
		refresh();
	}
	
	public void changeMap(String newMap){
		lastMap = mapName.toLowerCase();
		mapName = newMap;
		setMapArray();
		System.out.println("WECHSLE WELT ZU '"+curWorld.getFile()+"' mit der Größe "+getMapSize());
		setRenderImage();
	}
	public String getMapSize(){
		return getMapArray().length+" x "+getMapArray()[1].length;
	}
	
	public char[][] getMapArray(){
		return blueprint;
	}
	
	/**
	 * Updates the blueprints during mapchanges
	 */
	private void setMapArray(){

		blueprint = mapGetter.readFile(mapName);
	}
	public void changeBluePrint(char[][] newMap){
		blueprint = newMap;
	}
	
	/**
	 * 
	 * @return current Map
	 */
	public String getMapName(){
		return mapName;
	}
	
	
	/**
	 * 
	 * @return debuglistener for debug purposes 
	 */
	public DebugButtonListener getDebug(){
		return debugListener;
	}
	
	public ArrayList<GameObject> getObjectList(){
		return objectList;
	}
	
	/**
	 * 
	 * @return blueprint, used for rendering
	 */
	public void playerDied(){
		player.setInvincible(true);
		timer.stop();
		music.stopMusic();
		soundPlyr.stopMusic();
		ambient.stopMusic();
		for(int i = 0; i < timerList.size(); i++){
			timerList.get(0).stop();
		}
		timerList.clear();
		timerList.add(new Timer(1900, new TimerListener(this, "KILLSCREEN")));
		timerList.get(0).start();
		while(!timeJobList.isEmpty()){
			timeJobList.get(0).fullStop();
		}
		timeJobList.clear();
		stopLowHealthNoise();
		pool.online = false;
		clearEffects();
		addEffect(new Effect(Effect.typeNoise(), 100));
		music.playTick("disconnect", "SOUNDDisconnect");
		player.setTalking(false);
		setTextCustomMute("", Color.black);
	}
	public void logout(){
		timer.stop();
		music.stopMusic();
		soundPlyr.stopMusic();
		ambient.stopMusic();
		timerList.clear();
		timerList.add(new Timer(1900, new TimerListener(this, "LOGOUT")));
		timerList.get(0).start();
		stopLowHealthNoise();
		pool.online = false;
		clearEffects();
		addEffect(new Effect(Effect.typeNoise(), 100));
		music.playTick("disconnect", "SOUNDDisconnect");
		player.setTalking(false);
		demoText(-1);
	}
	
	public ScreenCreator getRenderer(){
		return screenCreator;
	}
	
	public SoundPlayer getMusicPlayer(){
		return music;
	}
	
	public World getWorld(){
		return curWorld;
	}
	
	public void changeWorld(World travel, Coordinate spawn){
		battle.active = false;
		player.setPos(spawn);
		curWorld = travel;
		changeMap(travel.getFile());
		travel.unLoadTextures();
		travel.loadTextures();
	}
	public void tick(String source){
		if(start){
			sListener.tick();
		}
		if (!source.contains("TICK")) {//MainGUITick
			switch (source) {
			case "HEALTHNOISE":
				refresh();
				break;
			case "SOUNDDisconnect":
				removeOverPic();
				music.stopMusic();
				getWorld().stop();
				changeWorld(new MainArea(this), new Coordinate(9, 2));
				player.setMovable(true);
				timer.start();
				player.repatch();
				player.setInvincible(false);
				break;
			case "KILLSCREEN":
				System.out.println("KILLSCREEn");
				try {
					setOverPic(
							ImageIO.read(new File(FileReader.path() + "/NewGeeemu/Pictures/OverPic/disconnect.png")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				curWorld.stop();
				clearEffects();
				timerList.get(0).stop();
				timerList.clear();
				refresh();
				player.repatch();
				break;
			case "REFRESH":
				try {
					refresh();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case "NONE":
				break;
			case "LOGOUT":
				try {
					setOverPic(
							ImageIO.read(new File(FileReader.path() + "/NewGeeemu/Pictures/OverPic/logout.png")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				curWorld.stop();
				clearEffects();
				timerList.get(0).stop();
				timerList.clear();
				refresh();
				player.repatch();
				break;
			case "NORMAL":
				if(!start){
					fps = fpsCount;
					fpsCount = 0;
				}
				break;
			case "REMOVETEXT":
				setTextCustomMute("",Color.black);
				for(int i = 0;  i < timeJobList.size(); i++){
					if(timeJobList.get(i).getJob().equals("REMOVETEXT")){
						timeJobList.get(i).fullStop();
						break;
					}
				}
				break;
			default:
				System.out.println("UNERWARTETES ZEITEREIGNIS "+source);
			}
		}
		else if (source.contains("WORLDTICK")){//WORLDTICK
			if(source.contains("SOUND")){
				curWorld.soundTick(source.replace("WORLDTICKSOUND", ""));
			}
			else if(source.contains("TIMER")){
				curWorld.timeTick(source.replace("WORLDTICKTIMER", ""));
			}
			else{
				curWorld.timeTick(source.replace("WORLDTICK", ""));
			}
		}
		//OBJECTTICK
		else if(source.contains("OBJECTTICK")){
			for(int i= 0; i < objectList.size(); i++){
				objectList.get(i).tickAction(source.replace("OBJECTTICK", ""));
			}
		}
		else if(source.contains("BATTLETICK")){
			battle.tick(source.replace("BATTLETICK", ""));
		}
		else{
			System.out.println("UNERWARTETES ZEITEREIGNIS "+source);
		}
		
	}
	
	public void setText(int pContent, Color pColor){
		if(!soundPlyr.isPlaying()){
			soundPlyr.play("talk");
		}
		if(pContent < 0){
			textContent = "";
		}
		else{
			textContent = FileReader.getDialogue(pContent, language);
		}
		textColor = pColor;
		img = screenCreator.changeText(img, textContent, textColor,pool.battleKnown);
		refresh();
	}
	public void setTextCustom(String pContent, Color pColor){
		if(!soundPlyr.isPlaying()){
			soundPlyr.play("talk");
		}
		textContent = pContent;
		textColor = pColor;
		img = screenCreator.changeText(img, textContent, textColor,pool.battleKnown);
		refresh();
	}
	public void setTextCustomMute(String pContent, Color pColor){
		textContent = pContent;
		textColor = pColor;
		img = screenCreator.changeText(img, textContent, textColor,pool.battleKnown);
		refresh();
	}
	
	
	public void demoText(int pContent){
		if(!soundPlyr.isPlaying()){
			soundPlyr.play("talk");
		}
		if(pContent < 0){
			textContent = "";
		}
		else{
			textContent = FileReader.getDialogue(pContent, "DEMO");
			textContent = textContent.replace("#NAME#", player.getName());
		}
		textColor = Color.white;
		img = screenCreator.changeText(img, textContent, textColor, pool.battleKnown);
		refresh();
	}
	
	//Low health noise
	public void startLowHealthNoise(){
		if (!makesHealthNoise()) {
			player.setSpeed(4);
			lowHealth = new Effect(Effect.typeNoise(), 95);
			healthNoiseSound.playLooped("lowHealth");
			healthNoise.start();
		}
	}
	public void stopLowHealthNoise(){
		try {
			player.setSpeed(1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		lowHealth = null;
		healthNoiseSound.stopMusic();
		healthNoise.stop();
	}
	public boolean makesHealthNoise(){
		if(lowHealth != null){
			return true;
		}
		return false;
	}
	public String getLanguage(){
		return language;
	}
	public CustomVariables getPool(){
		return pool;
	}
	public void setOverPic(BufferedImage pPic){
		overPic = pPic;
	}
	public void removeOverPic(){
		overPic = null;
	}
	public void closeWindow(){
		window.setVisible(false);
	}
	public SoundPlayer getSoundPlayer(){
		return soundPlyr;
	}
	public SoundPlayer getAmbientPlayer(){
		return ambient;
	}
	public KeyListener getCurrentListener(){
		return currentListener;
	}
	public void changeListener(KeyListener lstnr){
		window.removeKeyListener(currentListener);
		window.addKeyListener(lstnr);
		currentListener = lstnr;
	}
	public void normalListener(){
		window.removeKeyListener(currentListener);
		window.addKeyListener(tListener);
		currentListener = tListener;
	}
	public BufferedImage getOverPic(){
		return overPic;
	}
	public void startDecision(String question, String ansA, String ansB){
		changeListener(new Decision(question, ansA, ansB, this));
		setTextCustom(question+"#NEXT#"+ansA+"        "+ansB, Color.black);
	}
	public void blackOverPic(){
		try {
			setOverPic(ImageIO.read(new File(FileReader.path()+"/NewGeeemu/Pictures/OverPic/Black.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void playStep(){
		if(!soundPlyr.isPlaying()){
			char tile = blueprint[player.getPos().getX()][player.getPos().getY()];
			if(tile == 'C'){
				soundPlyr.play("step");
			}
			else if(screenCreator.plcHolder.contains(String.valueOf(tile)) && Texture.textureExists(curWorld.getTextures(), tile)){
				if(!Texture.getTXTfromList(tile, curWorld.getTextures()).stepSound.equals("NONE")){
					soundPlyr.play(Texture.getTXTfromList(tile, curWorld.getTextures()).stepSound);
				}
			}
		}
	}
	public void startBattle(Coordinate pA, Coordinate pB){
		if(!battle.active){
			battle.startBattle(pA, pB);
		}
	}
	public boolean isBattling(){
		return battle.active;
	}
	public static int randomNumber(int pNextInt){
		Random randGen = new Random();
		return randGen.nextInt(pNextInt);
	}
	public GameObject getObjectForName(String pName){
		for(int i = 0 ; i < objectList.size(); i++){
			if(objectList.get(i).getName().equals(pName)){
				return objectList.get(i);
			}
		}
		return null;
	}
	public void stopGame(){
		System.out.println("STOPPE RELEVANTE OBJEKTE...");
		curWorld.stop();
		if (getMusicPlayer() != null) {
			getMusicPlayer().clearQueue();
			getMusicPlayer().stopMusic();
		}
		if (getSoundPlayer() != null) {
			getSoundPlayer().clearQueue();
			getSoundPlayer().stopMusic();
		}
		if (getAmbientPlayer() != null) {
			getAmbientPlayer().clearQueue();
			getAmbientPlayer().stopMusic();
		}
		if(UISound != null){
			UISound.clearQueue();
			UISound.stopMusic();
		}
		if (getWorld() != null) {
			getWorld().stop();
		}
		while(!objectList.isEmpty()){
			objectList.get(0).stop();
		}
		objectList.clear();
		for(int i = 0; i < timerList.size(); i++){
			timerList.get(0).stop();
		}
		timerList.clear();
		while(!timeJobList.isEmpty()){
			timeJobList.get(0).fullStop();
		}
		timeJobList.clear();
		timerList.clear();
		player.stop();
		System.out.println("STOPPE SPIEL...");
		System.exit(0);
	}
	
}