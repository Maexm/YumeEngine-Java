package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import objects.GameObject;

public class MobTimeListener implements ActionListener{

private GameObject parent;
private String type;

public MobTimeListener(GameObject pParent, String pType){
	parent = pParent;
	type = pType;
}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(type.equals("MOVEMENT")){
			try {
				parent.tick();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(!parent.isMoving()){
			parent.act();
		}
	}

}
