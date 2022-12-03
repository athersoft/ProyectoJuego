package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Arma3 extends Arma{
	private int vel = 15;
	public Arma3(int atk) {
		super(atk);
	}

	public void disparo(float x, float y, PantallaJuego juego) {
		Bullet b = new Bullet(x,y,vel,0,new Texture(Gdx.files.internal("bullet.png")));
		juego.agregarBala(b);
		
		Bullet b2 = new Bullet(x,y,vel,vel/2,new Texture(Gdx.files.internal("bullet.png")));
		juego.agregarBala(b2);
		
		Bullet b3 = new Bullet(x,y,vel,-vel/2,new Texture(Gdx.files.internal("bullet.png")));
		juego.agregarBala(b3);
	}
	
}