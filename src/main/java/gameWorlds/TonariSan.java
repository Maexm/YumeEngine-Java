package gameWorlds;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Timer;

import gui.MainGUI;
import other.FileReader;
import rendering.Effect;
import rendering.Texture;
import timer.TimerListener;

public class TonariSan extends World{

	public TonariSan(MainGUI pParent) {
		super(pParent);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadTextures() {
		// TODO Auto-generated method stub
		try {
			textureList.add(new Texture('1', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/tiles/darkWall.png")), "darkWall", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			textureList.add(new Texture('0', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/tiles/darkFloor.png")), "darkFloor", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		parent.getMusicPlayer().playTick("kuraiSora", "WORLDTICKSOUNDkuraiSora");
		mapFile = "TonariSan";
		illegal = "1";
		parent.getPlayer().turn('d');
		parent.getPlayer().setMovable(false);
		loadTextures();
		parent.timerList.add(new Timer(7, new TimerListener(parent, "WORLDTICKdamage")));
		//parent.addEffect(new Effect(Effect.typeDark(), 666));
		parent.refresh();
	}

	@Override
	public void tickAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getDialogue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void timeTick(String pTask) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void soundTick(String pTask) {
		// TODO Auto-generated method stub
		parent.getPlayer().kill();
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		// TODO Auto-generated method stub
		
	}

}
