package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import rendering.Texture;

public class TimeListenerTexture implements ActionListener{
	
	Texture parent;
	public TimeListenerTexture(Texture pParent){
		parent = pParent;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			parent.tick();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Texture tick failed");
		}
		
	}

}
