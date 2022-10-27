package com.mygdx.game;

public abstract class paqueteAyuda {
	private boolean salud;
	private boolean ammo;
	private boolean shield;
	
	public paqueteAyuda(boolean salud, boolean ammo, boolean shield) {
		this.salud = false;
		this.ammo = false;
		this.shield = false;
	}
	public abstract void bonus();

}
