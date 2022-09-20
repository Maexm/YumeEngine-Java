package gameWorlds;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import erstesSpiel.Coordinate;
import gui.MainGUI;
import other.FileReader;
import rendering.Effect;
import rendering.Texture;
import rendering.TexturePack;

public class ShinshiGorakugai extends World{

	private boolean tonari;
	public ShinshiGorakugai(MainGUI pParent) {
		super(pParent);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		if(parent.getPlayer().getPos().is(39, 34)){
			stopMusic();
			stopAnimation();
			parent.clearEffects();
			parent.changeWorld(new MainArea(parent), new Coordinate(5,6));
			stop();
		}
		else if(parent.getPlayer().getPos().is(4, 31) && tonari){
			stopMusic();
			stopAnimation();
			parent.clearEffects();
			parent.changeWorld(new TonariSan(parent), new Coordinate(3,3));
			stop();
		}
	}

	@Override
	public void loadTextures() {
		// TODO Auto-generated method stub
		TexturePack.loadColorCenter(textureList, '0', 1000);
		TexturePack.loadColorTilesVertical(textureList, '1', 1000);
		TexturePack.loadColorTilesHorizontal(textureList, '2', 1000);
		TexturePack.loadColorTilesTUp(textureList, '7', 1000);
		TexturePack.loadColorTilesTDown(textureList, '8', 1000);
		TexturePack.loadColorTilesTRight(textureList, '9', 1000);
		TexturePack.loadColorTilesTLeft(textureList, 'A', 1000);
		TexturePack.loadColorTilesCurveLD(textureList, '6', 1000);
		TexturePack.loadColorTilesCurveLU(textureList, '4', 1000);
		TexturePack.loadColorTilesCurveRD(textureList, '5', 1000);
		TexturePack.loadColorTilesCurveRU(textureList, '3', 1000);
		TexturePack.loadColorTilesIntersection(textureList, 'G', 1000);
		
		try {
			textureList.add(new Texture('B', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/city/apaato.png")), "playersHouse",Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//RandBuild
		ArrayList<Texture> randBuild = new ArrayList<Texture>();
		try {
			randBuild.add(new Texture('D', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/city/randBuild5M10.png")), "randBuilding", Color.darkGray));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			randBuild.add(new Texture('D', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/city/randBuild5M10_2.png")), "randBuilding", Color.darkGray));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			randBuild.add(new Texture('D', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/city/randBuild5M10_3.png")), "randBuilding", Color.darkGray));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		textureList.add(new Texture('D', randBuild, "randBuildAnimated", Color.pink, 1000));
		
		//Otonari
		if(!tonari){
			try {
				textureList.add(new Texture('H', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/city/otonari.png")), "otonari", Color.pink));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			ArrayList<Texture> tonari = new ArrayList<Texture>();
			try {
				tonari.add(new Texture('H', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/city/otonari2.png")), "otonari2", Color.pink));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				tonari.add(new Texture('H', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/city/otonari3.png")), "otonari3", Color.pink));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			textureList.add(new Texture('H', tonari, "tonariAnimated", Color.pink, 1000));
		}
		
		//Ad
		
		ArrayList<Texture> ad = new ArrayList<Texture>();
		try {
			ad.add(new Texture('I', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/city/bigAd/tanoshiiKoohii2.png")), "adKoohii2", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ad.add(new Texture('I', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/city/bigAd/ISEokureru.png")), "adISE", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ad.add(new Texture('I', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/city/bigAd/tanoshiiKoohii.png")), "adKoohii", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(parent.getPlayer().getName().toLowerCase().equals("alina")){
			try {
				ad.add(new Texture('I', ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/city/bigAd/easterEgg.png")), "BTS", Color.pink));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		textureList.add(new Texture('I', ad, "ad", Color.pink, 5000));
	}

	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		Random rand = new Random();
		if(rand.nextInt(2) == 1){
			tonari = true;
			System.out.println("TRUE TONARI");
		}
		else{
			tonari = false;
		}
		startMusic("plaza_amb");
		mapFile = "ShinshiGorakugai";
		illegal = "0123456789ABDE";
		loadTextures();
		//parent.addEffect(new Effect(Effect.typeDark(), 80));
		
	}

	@Override
	public void tickAction() {
		// TODO Auto-generated method stub
		if(parent.getPlayer().looksAt().getX() == 40 && parent.getPlayer().looksAt().getY() == 34){
			if (!parent.getPlayer().isTalking()) {
				parent.getPlayer().setTalking(true);
				parent.setText(3, Color.pink);
			}
			else{
				parent.getPlayer().setTalking(false);
				parent.setText(-1, Color.black);
			}
		}
		else if(parent.getPlayer().getPos().is(4, 31) && !tonari){
			if (!parent.getPlayer().isTalking()) {
				parent.getPlayer().setTalking(true);
				parent.setText(4, Color.pink);
			}
			else{
				parent.getPlayer().setTalking(false);
				parent.setText(-1, Color.black);
			}
		}
		
		
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
		
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		// TODO Auto-generated method stub
		
	}

}
