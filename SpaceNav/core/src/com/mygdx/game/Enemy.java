package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import java.util.Random;

public class Enemy extends IA {
	Random r = new Random();
	private int intervaloAtaque = 150+r.nextInt(50);
	
	public Enemy(int x, int y, int size, int xSpeed, int ySpeed, Texture tx) {
		super(x,y,size,xSpeed,ySpeed,tx);
	}
	
	public void mover() {
		
		x += getXSpeed();

        if (x+getXSpeed() < 0 || x+getXSpeed()+spr.getWidth() > Gdx.graphics.getWidth())
        	this.Destroyed = true;
        if (y+getySpeed() < 0 || y+getySpeed()+spr.getHeight() > Gdx.graphics.getHeight())
        	setySpeed(getySpeed() * -1);
        spr.setPosition(x, y);
		
	}
	
	public void ataque(int x, int y, PantallaJuego juego) {
		if(intervaloAtaque == 0) {
			Bullet b = new Bullet(this.x,this.y,-6,0,new Texture(Gdx.files.internal("Rocket2.png")));
			intervaloAtaque = 150+r.nextInt(50);;
			juego.agregarBalaEnemiga(b);
		}else {
			intervaloAtaque--;
		}
	}
    
}
