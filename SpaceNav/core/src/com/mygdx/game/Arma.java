package com.mygdx.game;

public abstract class Arma {
	private int atk;
	private int xVel;
	private int yVel;
	private int lvl;
	
	public Arma(int atk, int xVel, int yVel) {
		this.lvl = 0;
		this.xVel = xVel;
		this.yVel = yVel;
		this.atk = atk;
	}
	
	public abstract void disparo();
}
