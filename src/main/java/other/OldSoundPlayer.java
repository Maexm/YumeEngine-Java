package other;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineEvent.Type;
import javax.sound.sampled.LineListener;

import listener.MusicListener;

public class OldSoundPlayer{
	/**
	Clip clip;
	private boolean isPlaying;
	private int waifu;
	
	public OldSoundPlayer(){
		isPlaying = false;
		waifu = 0;
	}
	
	public void play(String path){
		try{
			isPlaying = true;
			clip = AudioSystem.getClip();
			//MusicListener listener = new MusicListener(this);
			//clip.addLineListener(listener);
			AudioInputStream sInStream = AudioSystem.getAudioInputStream(new File("/NewGeeemu/Sound/"+path+".wav"));
			clip.open(sInStream);
			clip.start();
		}catch(Exception e){
			System.out.println("SoundPlayer: Could not play normal sound "+path);
		}
	}
	
	public void playLooped(String path){
		try{
			clip = AudioSystem.getClip();
			AudioInputStream sInStream = AudioSystem.getAudioInputStream(new File("/NewGeeemu/Sound/"+path+".wav"));
			clip.open(sInStream);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.start();
			isPlaying = true;
		}catch(Exception e){
			System.out.println("CRITICAL SOUND ERROR: COULD NOT PLAY "+path+".wav - TERMINATING");
			System.exit(0);
		}
	}
	public void waifu(){
		try{
			isPlaying = true;
			lvlUpWaifu();
			clip = AudioSystem.getClip();
			//MusicListener waifuListener = new MusicListener(this);
			//clip.addLineListener(waifuListener);
			AudioInputStream sInStream = AudioSystem.getAudioInputStream(new File("/NewGeeemu/Sound/WaifuFun.wav"));
			clip.open(sInStream);
			clip.start();
		}catch(Exception ourException){
			System.out.println("SoundPlayer: Could not play waifu");
			ourException.printStackTrace();
		}
	}
	public void waifuEnd(){
		try{
			clip = AudioSystem.getClip();
			//MusicListener waifuListener = new MusicListener(this);
			//clip.addLineListener(waifuListener);
			AudioInputStream sInStream = AudioSystem.getAudioInputStream(new File("/NewGeeemu/Sound/hackbuzzer.wav"));
			clip.open(sInStream);
			clip.start();
		}catch(Exception dontGetStopped){
			System.out.println("SoundPlayer: Could not play ending waifu");
		}
	}
	public Clip getClip(){
		return clip;
	}
	public boolean getIsPlaying(){
		return isPlaying;
	}
	public void stop(){
		clip.stop();
		//clip.close();
		isPlaying = false;
	}
	public int getWaifu(){
		return waifu;
	}
	public void lvlUpWaifu(){
		waifu++;
	}
	public void setIsPlaying(boolean value){
		isPlaying = value;
	}
**/
}
