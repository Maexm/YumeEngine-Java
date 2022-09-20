package weapons;

import java.awt.Color;

import javax.swing.Timer;

import listener.TimeListenerWeapon;
import objects.GameObject;
import other.SoundPlayer;

public class Weapon {
	public int durability;
	public int damage;
	public int charge;
	public int maxCharge;
	GameObject owner;
	public Timer recharge;
	private SoundPlayer sound;
	public boolean active;
	public Color barColor;
	public int requiredCharge;
	
	public Weapon(int pDurability, int pDamage, int pCharge, GameObject pOwner, int pRechargeTime, int pRqrdChrg, Color pBarColor){
		durability = pDurability;
		damage = pDamage;
		maxCharge = pCharge;
		charge = maxCharge;
		owner = pOwner;
		active = false;
		sound = new SoundPlayer(owner.getParent());
		recharge = new Timer(pRechargeTime, new TimeListenerWeapon(this));
		barColor = pBarColor;
		if(barColor == null){
			barColor = Color.blue;
		}
		requiredCharge = pRqrdChrg;
	}
	public int doDamage(){
		if(durability > 0 && charge >= requiredCharge && active){
			charge = charge - requiredCharge;
			if(charge != maxCharge && !recharge.isRunning()){
				recharge.start();
			}
			sound.play("normalWeapon");
			return damage;
		}
		sound.play("nocharge");
		return 0;
	}
	public void destroy(){
		recharge.stop();
		sound.clearQueue();
		sound.stopMusic();
		owner = null;
	}
	public void fullRecharge(){
		recharge.stop();
		charge = maxCharge;
		sound.play("recharged");
	}
	public void charge(){
		//System.out.println("LADUNG: "+charge+" "+getChargePercentage());
		if(charge != maxCharge){
			charge++;
			if(charge % requiredCharge == 0){
				sound.play("recharged");
			}
		}
		else{
			recharge.stop();
		}
	}
	public int getChargePercentage(){
		return (charge*100)/maxCharge;
	}
}
