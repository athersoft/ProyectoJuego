package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Enemy3 extends IA{
	private PantallaJuego juego;
	public Enemy3(int x, int y, int size, int xSpeed, int ySpeed, Texture tx) {
		super(x, y, size, xSpeed, ySpeed, tx);
		// TODO Auto-generated constructor stub
	}

	
	public void mover() {
		x += getXSpeed();

        if (x+getXSpeed() < 0 || x+getXSpeed()+spr.getWidth() > Gdx.graphics.getWidth())
        	this.Destroyed = true;
        if (y+getySpeed() < 0 || y+getySpeed()+spr.getHeight() > Gdx.graphics.getHeight())
        	setySpeed(getySpeed() * -1);
        spr.setPosition(x, y);
		
	}
	public void disparo(float x, float y, PantallaJuego juego) {
		BulletEnemy b = new BulletEnemy(x,y,0,0,new Texture(Gdx.files.internal("Rocket2.png")));
		juego.agregarBala(b);
	}

	public void ataque(Nave4 nave) {
		if(nave.getY() >= (this.y-5) || nave.getY() <= (this.y+5)) {
			disparo(this.x, this.y, juego);
		}
		
	}


	@Override
	public void ataque() {
		// TODO Auto-generated method stub
		
	}

}
