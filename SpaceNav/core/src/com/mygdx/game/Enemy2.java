package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Enemy2 extends Enemy{
	private int yy = 0;
	private int dirY = 1;
	
	public Enemy2(int x, int y, int size, int xSpeed, int ySpeed, Texture tx) {
		super(x, y, size, xSpeed, ySpeed, tx);

	}
	
	public void mover() {
		changeX(getXSpeed());
		if(getY() > 700) {
			dirY = -1;
		}
		if(getY() < 100) {
			dirY = 1;
		}
		changeY(4*dirY);

        if (getX()+getXSpeed() < 0 || getX()+getXSpeed()+getSpr().getWidth() > Gdx.graphics.getWidth())
        	this.Destroy();
        if (getY()+getySpeed() < 0 || getY()+getySpeed()+getSpr().getHeight() > Gdx.graphics.getHeight())
        	setySpeed(getySpeed() * -1);
        getSpr().setPosition(getX(), getY());
	}

}
