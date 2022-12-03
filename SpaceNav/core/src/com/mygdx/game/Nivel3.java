package com.mygdx.game;

import java.util.Random;
import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Nivel3 implements EnemyFactory{
	
	private int intervalo;
	private int intervaloInicial;
	
	public Nivel3(int intervalo) {
		this.intervalo = intervalo;
		this.intervaloInicial = intervalo;
	}
	
	
	//public IA createBoss() {
		
	//}

	public IA createEnemy() {
		Random r = new Random();
		
		if(intervalo == 0) {
			IA bb = new Enemy3(Gdx.graphics.getWidth()-100+r.nextInt(140),
		  	            50+r.nextInt((int)Gdx.graphics.getHeight()-50),
		  	            20+r.nextInt(10), -1, 2, 
		  	            new Texture(Gdx.files.internal("body_01.png")),6);	   

	  	    
	  	  intervalo = intervaloInicial;
	  	  
		  return bb;    
	  	    
			
		}else {
			intervalo--;
			return null;
		}
	}
	
	public EnemyFactory nextLevel() {
		Nivel4 n = new Nivel4(1);
		return n;
	}
	
}
