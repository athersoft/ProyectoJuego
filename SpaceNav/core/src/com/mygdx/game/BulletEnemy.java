package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class BulletEnemy extends Bullet{

	private Sprite spr;
	public BulletEnemy(float x, float y, int xSpeed, int ySpeed, Texture tx) {
		super(x, y, xSpeed, ySpeed, tx);
		// TODO Auto-generated constructor stub
	}

	public boolean checkCollision(Nave4 b2) {
        if(spr.getBoundingRectangle().overlaps(b2.getArea())){
        	// Se destruyen ambos
            b2.estaHerido();
            return true;

        }
        return false;
    }
}
