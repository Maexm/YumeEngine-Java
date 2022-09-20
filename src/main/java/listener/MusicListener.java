package listener;

import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineEvent.Type;
import javax.sound.sampled.LineListener;

import other.SoundPlayer;

public class MusicListener implements LineListener{

	SoundPlayer player;
	public String tick;
	public MusicListener(SoundPlayer parent, String pTick){
		player = parent;
		tick = pTick;
	}
	
	@Override
	public void update(LineEvent event) {
		event.getType();
		if(event.getType() == Type.STOP){
		
			player.getClip().close();
			//System.out.println("Closed clip '"+player.currentTitle()+"'");
			player.setPlaying(false);
			player.playQueue();
			if(!tick.equals("NONE")){
				if(player.getParent() != null){
					player.getParent().tick(tick);
				}
				else{
					player.getWorld().soundTick(tick);
				}
				tick = "NONE";
			}
		}
		
	}

}
