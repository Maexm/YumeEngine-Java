package timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.MainGUI;

public class TimerListener implements ActionListener{
	
	private TimerJob parent;
	private MainGUI guiParent;
	private String task;
	private boolean primitive;
	
	public TimerListener(TimerJob pParent){
		parent = pParent;
		primitive = false;
	}
	public TimerListener(MainGUI pParent, String pTask){
		guiParent = pParent;
		task = pTask;
		primitive = true;
	}
	public String getTask(){
		if(primitive){
			return task;
		}
		else{
			return "NO_TASK";
		}
	}
	public boolean isPrimitive(){
		return primitive;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(primitive){
			guiParent.tick(task);
		}
		else{
			parent.tick();
		}
	}

}
