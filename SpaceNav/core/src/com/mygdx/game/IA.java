package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class IA {
	
	protected int hp = 2;
	protected int x;
	protected int y;
	protected int xSpeed;
	protected int ySpeed;
    protected Sprite spr;
    protected boolean Destroyed = false;
    
	public IA(int x, int y, int size, int xSpeed, int ySpeed, Texture tx) {
    	spr = new Sprite(tx);
    	this.x = x; 
 	
        //validar que borde de esfera no quede fuera
    	
    	if (x-size < 0) this.x = x+size;
    	if (x+size > Gdx.graphics.getWidth())this.x = x-size;
         
        this.y = y;
        //validar que borde de esfera no quede fuera
    	if (y-size < 0) this.y = y+size;
    	if (y+size > Gdx.graphics.getHeight())this.y = y-size;
    	
        spr.setPosition(x, y);
        this.setXSpeed(xSpeed);
        this.setySpeed(ySpeed);
    }
	
	 public Rectangle getArea() {
	    	return spr.getBoundingRectangle();
	    }
	    public void draw(SpriteBatch batch) {
	    	spr.draw(batch);
	    }
	    
		public int getXSpeed() {
			return xSpeed;
		}
		public void setXSpeed(int xSpeed) {
			this.xSpeed = xSpeed;
		}
		public int getySpeed() {
			return ySpeed;
		}
		public void setySpeed(int ySpeed) {
			this.ySpeed = ySpeed;
		}
		
		public void reducirVida(int atk) {
			this.hp -= atk;
			if(this.hp <= 0) {
				this.Destroyed = true;
			}
		}
		
		public Sprite getSpr() {
			return spr;
		}
		
		public void changeX(int x) {
			this.x += x;
		}
		
		public void changeY(int y) {
			this.y += y;
		}
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
		
		public void Destroy() {
			Destroyed = true;
		}
		
		public boolean isDestroyed() {
			return Destroyed;
		}
		
		public abstract void mover();
		public abstract void ataque();
		
		////////////////template method/////////////////
		public void actuar() {
			mover();
			ataque();
		}
		
		
}
