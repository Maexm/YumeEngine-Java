package timer;

import javax.swing.Timer;

import gui.MainGUI;

public class TimerJob {

	private String job;
	private MainGUI parent;
	private int delay;
	private Timer timer;
	
	/**
	 * Konstruktor.
	 * Timer startet automatisch beim Erstellen.
	 * @param pJob
	 * @param pDelay
	 * @param pParent
	 */
	public TimerJob(String pJob, int pDelay, MainGUI pParent){
		job = pJob;
		delay = pDelay;
		parent = pParent;
		timer = new Timer(delay, new TimerListener(this));
		timer.start();
	}
	
	public void setDelay(int value){
		delay = value;
		stop();
		timer.setDelay(delay);
		start();
	}
	public void setJob(String pJob){
		job = pJob;
	}
	/**
	 * Beinhaltet einen Neustart.
	 * @param value
	 * @param pJob
	 */
	public void fullChange(int value, String pJob){
		delay = value;
		job = pJob;
		timer.stop();
		timer.setDelay(value);
		timer.start();
	}
	/**
	 * Stoppt den Timer
	 */
	public void stop(){
		timer.stop();
	}
	public void start(){
		timer.start();
	}
	public int getDelay(){
		return delay;
	}
	/**
	 * Stoppt den Timer UND entfernt ihn aus der Liste.
	 */
	public void fullStop(){
		timer.stop();
		timer.removeActionListener(timer.getActionListeners()[0]);
		timer = null;
		parent.timeJobList.remove(this);
		this.parent = null;
	}
	public void tick(){
		parent.tick(job);
	}
	public String getJob(){
		return job;
	}
}
