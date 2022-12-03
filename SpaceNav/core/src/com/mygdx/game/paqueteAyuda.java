package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class paqueteAyuda{
	
	protected int hp = 2;
	protected int x;
	protected int y;
	protected int xSpeed;
	protected int ySpeed;
    protected Sprite spr1;
    protected boolean Destroyed = false;
    
	public paqueteAyuda(int x, int y, int size, int xSpeed, int ySpeed, Texture tx, int hp) {
    	spr1 = new Sprite(tx);
    	this.x = x; 
    	this.hp = hp;
 	
        //validar que borde de esfera no quede fuera
    	
    	if (x-size < 0) this.x = x+size;
    	if (x+size > Gdx.graphics.getWidth())this.x = x-size;
         
        this.y = y;
        //validar que borde de esfera no quede fuera
    	if (y-size < 0) this.y = y+size;
    	if (y+size > Gdx.graphics.getHeight())this.y = y-size;
    	
        spr1.setPosition(x, y);

    }
	
	private boolean escudo = false;
	private boolean arma = false;
	private boolean mejora = false;
	private boolean boost = false;
	private Sprite spr;

	
	public boolean getHit() {
		return false;
	}
	
	public Rectangle getArea() {
    	return spr1.getBoundingRectangle();
    }
	
	public void draw(SpriteBatch batch) {
    	spr1.draw(batch);
    }
	
	public void inicializarBoosts() {
		String[] boosts = {"escudo", "arma", "mejora", "boost"};
		java.util.Random random = new java.util.Random();
		int randomAux = random.nextInt(boosts.length);
		

		switch(boosts[randomAux]) {
			case "escudo":
				escudo = true;
			case "arma":
				arma = true;
			case "mejora":
				mejora = true;
			case "boost":
				boost = true;
		}
	}

}
