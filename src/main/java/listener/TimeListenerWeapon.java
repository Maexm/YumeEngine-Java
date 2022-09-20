package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import weapons.Weapon;

public class TimeListenerWeapon implements ActionListener{
	
	Weapon parent;
	
	public TimeListenerWeapon(Weapon pParent){
		parent = pParent;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		parent.charge();
	}

}
