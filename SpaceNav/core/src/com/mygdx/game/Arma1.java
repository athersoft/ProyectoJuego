package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Arma1 extends Arma{
	private int vel = 15;
	public Arma1(int atk) {
		super(atk);
	}

	public void disparo(float x, float y, PantallaJuego juego) {
		Bullet b = new Bullet(x,y,vel,0,new Texture(Gdx.files.internal("Rocket2.png")));
		juego.agregarBala(b);
	}
	
}
