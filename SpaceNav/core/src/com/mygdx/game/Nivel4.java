package com.mygdx.game;

import java.util.Random;
import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Nivel4 implements EnemyFactory{
	
	private int intervalo;
	private int intervaloInicial;
	
	public Nivel4(int intervalo) {
		this.intervalo = intervalo;
		this.intervaloInicial = intervalo;
	}
	
	
	//public IA createBoss() {
		
	//}

	public IA createEnemy() {
		Random r = new Random();
		
		if(intervalo == 0) {
			IA bb = new Enemy4(Gdx.graphics.getWidth()-300,
		  	            Gdx.graphics.getHeight()/2,
		  	            20+r.nextInt(10), 0, 2, 
		  	            new Texture(Gdx.files.internal("boss.png")), 30);	   

	  	    
	  	  intervalo = -1;
	  	  
		  return bb;    
	  	    
			
		}else {
			intervalo--;
			return null;
		}
	}
	
	public EnemyFactory nextLevel() {
		Nivel2 n = new Nivel2(15);
		return n;
	}
	
}
