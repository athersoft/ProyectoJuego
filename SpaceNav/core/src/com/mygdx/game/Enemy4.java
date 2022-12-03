package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import java.util.Random;

public class Enemy4 extends IA {
	Random r = new Random();
	private int intervaloAtaque = 30;
	private int dirY = 1;
	private int yInicial;
	
	public Enemy4(int x, int y, int size, int xSpeed, int ySpeed, Texture tx, int hp) {
		super(x,y,size,xSpeed,ySpeed,tx,hp);
		Random r = new Random();
		dirY = 1*(r.nextBoolean() ? 1 : -1 );
		yInicial = y;
	}
	
	public void mover() {
		
		
		if(getY() > yInicial+300) {
			dirY = -1;
		}
		if(getY() <yInicial-300) {
			dirY = 1;
		}
		changeY(5*dirY);

        if (x+getXSpeed() < 0 || x+getXSpeed()+spr.getWidth() > Gdx.graphics.getWidth())
        	this.Destroyed = true;
        if (y+getySpeed() < 0 || y+getySpeed()+spr.getHeight() > Gdx.graphics.getHeight())
        	setySpeed(getySpeed() * -1);
        spr.setPosition(x, y);
		
	}
	
	public void ataque(int x, int y, PantallaJuego juego) {
		if(intervaloAtaque == 0) {
			Bullet b = new Bullet(this.x,this.y,-6,0,new Texture(Gdx.files.internal("bullet.png")));
			juego.agregarBalaEnemiga(b);
			
			b = new Bullet(this.x,this.y,-6,3,new Texture(Gdx.files.internal("bullet.png")));
			juego.agregarBalaEnemiga(b);
			
			b = new Bullet(this.x,this.y,-6,-3,new Texture(Gdx.files.internal("bullet.png")));
			juego.agregarBalaEnemiga(b);
			
			b = new Bullet(this.x,this.y,-6,6,new Texture(Gdx.files.internal("bullet.png")));
			juego.agregarBalaEnemiga(b);
			
			b = new Bullet(this.x,this.y,-6,-6,new Texture(Gdx.files.internal("bullet.png")));
			juego.agregarBalaEnemiga(b);
			
			b = new Bullet(this.x,this.y,-6,9,new Texture(Gdx.files.internal("bullet.png")));
			juego.agregarBalaEnemiga(b);
			
			b = new Bullet(this.x,this.y,-6,-9,new Texture(Gdx.files.internal("bullet.png")));
			juego.agregarBalaEnemiga(b);
			intervaloAtaque = 30;
			
		}else {
			intervaloAtaque--;
		}
	}
    
}
