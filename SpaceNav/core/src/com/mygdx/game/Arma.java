package com.mygdx.game;

public abstract class Arma {
	private int atk;
	private int lvl;
	
	public Arma(int atk) {
		this.lvl = 0;
		this.atk = atk;
	}
	
	public abstract void disparo(float x, float y, PantallaJuego juego);
}