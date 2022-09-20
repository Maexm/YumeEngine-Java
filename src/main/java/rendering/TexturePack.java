package rendering;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import gui.MainGUI;
import other.FileReader;

public class TexturePack {
	
	private static void loadTextureMissing(ArrayList<Texture> pList, char pId){
		try {
			pList.add(new Texture(pId, ImageIO.read(new File(FileReader.path()+"NewGeeemu/Pictures/MapObjects/missingTexture.png")), "centerTileB", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void loadColorCenter(ArrayList<Texture> list, char id, int delay){
		String path = FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/city/colorTiles/center/";
		ArrayList<Texture> center = new ArrayList<Texture>();
		
		try {
			center.add(new Texture(id, ImageIO.read(new File(path+"centerB.png")), "centerTileB", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(center, id);
		}
		try {
			center.add(new Texture(id, ImageIO.read(new File(path+"centerG.png")), "centerTileG", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(center, id);
		}
		try {
			center.add(new Texture(id, ImageIO.read(new File(path+"centerY.png")), "centerTileY", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(center,id);
		}
		try {
			center.add(new Texture(id, ImageIO.read(new File(path+"centerO.png")), "centerTileO", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(center,id);
		}
		try {
			center.add(new Texture(id, ImageIO.read(new File(path+"centerP.png")), "centerTileP", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(center,id);
		}
		try {
			center.add(new Texture(id, ImageIO.read(new File(path+"centerBP.png")), "centerTileBP", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(center,id);
		}
		try {
			center.add(new Texture(id, ImageIO.read(new File(path+"/centerDP.png")), "centerTileDP", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(center,id);
		}
		
		list.add(new Texture(id, center, "colorCenter", Color.pink, delay));
	}
	
	public static void loadColorTilesHorizontal(ArrayList<Texture> list, char id, int delay){
		String path = FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/city/colorTiles/horizontal/";
		ArrayList<Texture> tiles = new ArrayList<Texture>();
		
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"blue.png")), "colorTileHorizontalB", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"green.png")), "colorTileHorizontalG", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"yellow.png")), "colorTileHorizontalY", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"orange.png")), "colorTileHorizontalO", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"pink.png")), "colorTileHorizontalP", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"purple.png")), "colorTileHorizontalBP", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"darkPurple.png")), "colorTileHorizontalDP", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		list.add(new Texture(id, tiles, "colorTilesHorizontal", Color.pink, delay));
	}
	
	public static void loadColorTilesVertical(ArrayList<Texture> list, char id, int delay){
		String path = FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/city/colorTiles/vertical/";
		ArrayList<Texture> tiles = new ArrayList<Texture>();
		
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"blue.png")), "colorTileVerticalB", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"green.png")), "colorTileVerticalG", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"yellow.png")), "colorTileVerticalY", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"orange.png")), "colorTileVerticalO", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"pink.png")), "colorTileVerticalP", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"purple.png")), "colorTileVerticalBP", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"darkPurple.png")), "colorTileVerticalDP", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		list.add(new Texture(id, tiles, "colorTilesVertical", Color.pink, delay));
	}
	
	public static void loadColorTilesIntersection(ArrayList<Texture> list, char id, int delay){
		String path = FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/city/colorTiles/intersection/";
		ArrayList<Texture> tiles = new ArrayList<Texture>();
		
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"blue.png")), "colorTileIntersectionB", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"green.png")), "colorTileIntersectionG", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"yellow.png")), "colorTileIntersectionY", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"orange.png")), "colorTileIntersectionO", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"pink.png")), "colorTileIntersectionP", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"purple.png")), "colorTileIntersectionBP", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"darkPurple.png")), "colorTileIntersectionDP", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		list.add(new Texture(id, tiles, "colorTilesIntersection", Color.pink, delay));
	}
	
	public static void loadColorTilesTLeft(ArrayList<Texture> list, char id, int delay){
		String path = FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/city/colorTiles/tJunction/";
		ArrayList<Texture> tiles = new ArrayList<Texture>();
		
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"blueL.png")), "colorTileTJunctionLeftB", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"greenL.png")), "colorTileTJunctionLeftG", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"yellowL.png")), "colorTileTJunctionLeftY", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"orangeL.png")), "colorTileTJunctionLeftO", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"pinkL.png")), "colorTileTJunctionLeftP", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"purpleL.png")), "colorTileTJunctionLeftBP", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"darkPurpleL.png")), "colorTileTJunctionLeftDP", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		list.add(new Texture(id, tiles, "colorTilesTJunctionLeft", Color.pink, delay));
	}
	
	public static void loadColorTilesTDown(ArrayList<Texture> list, char id, int delay){
		String path = FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/city/colorTiles/tJunction/";
		ArrayList<Texture> tiles = new ArrayList<Texture>();
		
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"blueD.png")), "colorTileTJunctionDownB", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"greenD.png")), "colorTileTJunctionDownG", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"yellowD.png")), "colorTileTJunctionDownY", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"orangeD.png")), "colorTileTJunctionDownO", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"pinkD.png")), "colorTileTJunctionDownP", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"purpleD.png")), "colorTileTJunctionDownBP", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"darkPurpleD.png")), "colorTileTJunctionDownDP", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		list.add(new Texture(id, tiles, "colorTilesTJunctionDown", Color.pink, delay));
	}
	public static void loadColorTilesTRight(ArrayList<Texture> list, char id, int delay){
		String path = FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/city/colorTiles/tJunction/";
		ArrayList<Texture> tiles = new ArrayList<Texture>();
		
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"blueR.png")), "colorTileTJunctionRightB", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"greenR.png")), "colorTileTJunctionRightG", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"yellowR.png")), "colorTileTJunctionRightY", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"orangeR.png")), "colorTileTJunctionRightO", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"pinkR.png")), "colorTileTJunctionRightP", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"purpleR.png")), "colorTileTJunctionRightBP", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"darkPurpleR.png")), "colorTileTJunctionRightDP", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		list.add(new Texture(id, tiles, "colorTilesTJunctionRight", Color.pink, delay));
	}
	public static void loadColorTilesTUp(ArrayList<Texture> list, char id, int delay){
		String path = FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/city/colorTiles/tJunction/";
		ArrayList<Texture> tiles = new ArrayList<Texture>();
		
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"blueU.png")), "colorTileTJunctionUpB", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"greenU.png")), "colorTileTJunctionUpG", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"yellowU.png")), "colorTileTJunctionUpY", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"orangeU.png")), "colorTileTJunctionUpO", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"pinkU.png")), "colorTileTJunctionUpP", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"purpleU.png")), "colorTileTJunctionUpBP", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"darkPurpleU.png")), "colorTileTJunctionUpDP", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		list.add(new Texture(id, tiles, "colorTilesTJunctionUp", Color.pink, delay));
	}
	
	public static void loadColorTilesCurveRD(ArrayList<Texture> list, char id, int delay){
		String path = FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/city/colorTiles/curve/";
		ArrayList<Texture> tiles = new ArrayList<Texture>();
		
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"blueRD.png")), "colorTileCurveRD", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"greenRD.png")), "colorTileCurveRD", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"yellowRD.png")), "colorTileCurveRD", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"orangeRD.png")), "colorTileCurveRD", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"pinkRD.png")), "colorTileCurveRD", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"purpleRD.png")), "colorTileCurveRD", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"darkPurpleRD.png")), "colorTileCurveRD", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		list.add(new Texture(id, tiles, "colorTilesTileCurveRD", Color.pink, delay));
	}
	
	public static void loadColorTilesCurveRU(ArrayList<Texture> list, char id, int delay){
		String path = FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/city/colorTiles/curve/";
		ArrayList<Texture> tiles = new ArrayList<Texture>();
		
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"blueRU.png")), "colorTileCurveRU", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"greenRU.png")), "colorTileCurveRU", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"yellowRU.png")), "colorTileCurveRU", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"orangeRU.png")), "colorTileCurveRU", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"pinkRU.png")), "colorTileCurveRU", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"purpleRU.png")), "colorTileCurveRU", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"darkPurpleRU.png")), "colorTileCurveRU", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		list.add(new Texture(id, tiles, "colorTilesCurveRU", Color.pink, delay ));
	}
	
	public static void loadColorTilesCurveLU(ArrayList<Texture> list, char id, int delay){
		String path = FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/city/colorTiles/curve/";
		ArrayList<Texture> tiles = new ArrayList<Texture>();
		
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"blueLU.png")), "colorTileCurveLU", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"greenLU.png")), "colorTileCurveLU", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"yellowLU.png")), "colorTileCurveLU", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"orangeLU.png")), "colorTileCurveLU", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"pinkLU.png")), "colorTileCurveLU", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"purpleLU.png")), "colorTileCurveLU", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"darkPurpleLU.png")), "colorTileCurveLU", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		list.add(new Texture(id, tiles, "colorTilesCurveLU", Color.pink, delay));
	}
	
	public static void loadColorTilesCurveLD(ArrayList<Texture> list, char id, int delay){
		String path = FileReader.path()+"NewGeeemu/Pictures/PlaceHolders/city/colorTiles/curve/";
		ArrayList<Texture> tiles = new ArrayList<Texture>();
		
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"blueLD.png")), "colorTileCurveLD", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"greenLD.png")), "colorTileCurveLD", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"yellowLD.png")), "colorTileCurveLD", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"orangeLD.png")), "colorTileCurveLD", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"pinkLD.png")), "colorTileCurveLD", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"purpleLD.png")), "colorTileCurveLD", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		try {
			tiles.add(new Texture(id, ImageIO.read(new File(path+"darkPurpleLD.png")), "colorTileCurveLD", Color.pink));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loadTextureMissing(tiles, id);
		}
		list.add(new Texture(id, tiles, "colorTilesCurveLD", Color.pink, delay));
	}
}
