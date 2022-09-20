package other;

import java.io.File;
import java.util.AbstractQueue;
import java.util.ArrayDeque;
import java.util.concurrent.ArrayBlockingQueue;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;

import gameWorlds.World;
import gui.MainGUI;
import listener.MusicListener;


public class SoundPlayer {

	AudioInputStream inStream;
	String path;
	boolean isPlaying;
	Clip sClip;
	MusicListener listener;
	FloatControl vol;
	MainGUI parent;
	World worldParent;
	MusicListener mListener;
	ArrayDeque<MusicTrack> queue;
	public String name;
	
	public SoundPlayer(MainGUI pParent){
		isPlaying = false;
		name = "";
		parent = pParent;
		queue = new ArrayDeque<MusicTrack>();
		try {
			sClip = AudioSystem.getClip();
			mListener = new MusicListener(this,"NONE");
			sClip.addLineListener(mListener);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public SoundPlayer(World pParent, String pName){
		name = pName;
		isPlaying = false;
		worldParent = pParent;
		queue = new ArrayDeque<MusicTrack>();
		try {
			sClip = AudioSystem.getClip();
			mListener = new MusicListener(this,"NONE");
			sClip.addLineListener(mListener);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void play(String pPath){
		//vol = (FloatControl) sClip.getControl(FloatControl.Type.VOLUME);
		path = pPath;
		try{
			if(isPlaying){
				//System.out.println("Scheduling "+path+".wav");
				queue.add(new MusicTrack(path, "NONE", false, 0));
			}
			else{
				AudioInputStream sInStream = AudioSystem.getAudioInputStream(new File(FileReader.path()+"NewGeeemu/Sound/"+path+".wav"));
				sClip.open(sInStream);
				sClip.start();
				isPlaying = true;
			}
			
		}catch(Exception e){
			System.out.println("SoundPlayer - FEHLER: Konnte Datei "+path+".wav nicht abspielen!");
		}
	}
	public void playLooped(String pPath){
		path = pPath;
		mListener.tick = "NONE";
		try{
			if(isPlaying){
				//System.out.println("Scheduling "+path+".wav");
				queue.add(new MusicTrack(path, "NONE", true, 0));
			}
			AudioInputStream sInStream = AudioSystem.getAudioInputStream(new File(FileReader.path()+"NewGeeemu/Sound/"+path+".wav"));
			sClip.open(sInStream);
			sClip.loop(Clip.LOOP_CONTINUOUSLY);
			sClip.start();
			isPlaying = true;
		}catch(Exception e){
			System.out.println("SoundPlayer - FEHLER: Konnte Datei "+path+".wav nicht abspielen!");
			e.printStackTrace();
		}
	}
	public void playLooped(String pPath, int repeat){
		path = pPath;
		mListener.tick = "NONE";
		try{
			if(isPlaying){
				//System.out.println("Scheduling "+path+".wav");
				queue.add(new MusicTrack(path, "NONE", true, repeat));
			}
			AudioInputStream sInStream = AudioSystem.getAudioInputStream(new File(FileReader.path()+"NewGeeemu/Sound/"+path+".wav"));
			sClip.open(sInStream);
			sClip.setLoopPoints(repeat, -1);
			sClip.loop(Clip.LOOP_CONTINUOUSLY);
			sClip.start();
			isPlaying = true;
		}catch(Exception e){
			System.out.println("SoundPlayer - FEHLER: Konnte Datei "+path+".wav nicht abspielen!");
			e.printStackTrace();
		}
	}
	public void playTick(String pPath, String job){
		path = pPath;
		mListener.tick = job;
		try{
			if(sClip.isActive()){
				//System.out.println("Setze "+path+".wav in die Warteschlange");
				queue.add(new MusicTrack(path, job, false, 0));
			}
			AudioInputStream sInStream = AudioSystem.getAudioInputStream(new File(FileReader.path()+"NewGeeemu/Sound/"+path+".wav"));
			sClip.open(sInStream);
			sClip.start();
			isPlaying = true;
		}catch(Exception e){
			System.out.println("SoundPlayer - FEHLER: Konnte Datei "+path+".wav nicht abspielen!");
			e.printStackTrace();
		}
	}
	public void playQueue(){
		if(!queue.isEmpty()){
			MusicTrack pTrack = queue.removeFirst();
			if(pTrack.loop){
				if(pTrack.loopPoint != 0 ){
					playLooped(pTrack.path);
				}
				else{
					playLooped(pTrack.path, pTrack.loopPoint);
				}
				
			}
			else if(pTrack.job.equals("NONE")){
				//System.out.println("Spiele Track aus Warteschlange "+pTrack.path+".wav");
				play(pTrack.path);
			}
			else{
				//System.out.println("Spiele tick-Track aus Warteschlange "+pTrack.path+".wav");
				playTick(pTrack.path, pTrack.job);
			}
		}
	}
	public void stopMusic(){
		if(sClip.isRunning()){
			sClip.stop();
			sClip.close();
			System.out.println("MUSIK "+path+ " GESTOPPT");
		}
	}
	public void setPlaying(boolean value){
		isPlaying = value;
	}
	public boolean isPlaying(){
		return isPlaying;
	}
	public String currentTitle(){
		return path;
	}
	public Clip getClip(){
		return sClip;
	}
	public void setVolume(float v){
		vol.setValue(v);
	}
	public float getVolume(){
		return sClip.getLevel();
	}
	public MainGUI getParent(){
		return parent;
	}
	public World getWorld(){
		return worldParent;
	}
	public void clearQueue(){
		queue.clear();
	}
}
