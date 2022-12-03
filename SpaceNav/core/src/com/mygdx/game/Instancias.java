package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Instancias {
	private  ArrayList<IA> enemy1 = new ArrayList<>();
	private  ArrayList<IA> enemy2 = new ArrayList<>();
	private  ArrayList<paqueteAyuda> paque = new ArrayList<>();
	private  ArrayList<Bullet> balas = new ArrayList<>();
	private  ArrayList<Bullet> balasEnemigas = new ArrayList<>();
	
	
	private static Instancias instance;

    private Instancias(){}

    public static Instancias getInstance() {
        if (instance == null) {
            instance = new Instancias();
        }
        return instance;
    }
    
    public void reset() {
    	enemy1 = new ArrayList<>();
    	enemy2 = new ArrayList<>();
    	paque = new ArrayList<>();
    	balas = new ArrayList<>();
    	balasEnemigas = new ArrayList<>();	
    }
    
    public void crearPaquete() {
        Random r = new Random();
    	paqueteAyuda sh = new paqueteAyuda(r.nextInt((int)Gdx.graphics.getWidth()),
  	            50+r.nextInt((int)Gdx.graphics.getHeight()-50),
  	            20+r.nextInt(10), 2+r.nextInt(4), 2+r.nextInt(4), 
  	            new Texture(Gdx.files.internal("package1.png")),1);
    	paque.add(sh);
    }
    
    public Nave4 colNavePaq(Nave4 nave) {
    	 for(int i = 0; i < paque.size(); i++) {
    		paqueteAyuda p = paque.get(i);
    		boolean a = nave.checkCollisionpack(p);
    		if(a) {
    			paque.remove(i);
    			//nave.setEscudo();
    		}	
	     } 
    	return nave;
    }
    
    public int colBalaEnemigo(Sound explosionSound) {
    	int ret = 0;
    	for (int i = 0; i < balas.size(); i++) {
            Bullet b = balas.get(i);
            b.update();
            for (int j = 0; j < enemy1.size(); j++) {    
              if (b.checkCollision(enemy1.get(j))) {          
            	 explosionSound.play();
            	 enemy1.get(j).reducirVida(b.getAtk());
            	 
            	 if(enemy1.get(j).isDestroyed()) {
            		 enemy1.remove(j);
			         enemy2.remove(j);
	            	 j--; 
	            	 ret = 10;
            	 }
 
              }   	  
  	        }
                
            if (b.isDestroyed()) {
                balas.remove(b);
                i--; //para no saltarse 1 tras eliminar del arraylist
            }
      }
    return ret;
    }
    
    public Nave4 colNaveBala(Nave4 nave) {
    	paqueteAyuda p = null;
	      if(!paque.isEmpty()) {
	    	   p = paque.get(0);
	      } 
    	for (int i = 0; i < balasEnemigas.size(); i++) {
	    	  Bullet b = balasEnemigas.get(i);
	    	  if(nave.getShield() == false) {
	    	    	if (nave.checkCollisionBullet(b)) {
		            //asteroide se destruye con el choque             
	            	 balasEnemigas.remove(i);
	            	 i--;
	    	    	}else {
		            	  if(p != null && nave.checkCollisionpack(p) && paque.isEmpty() == false) {
		            		  paque.remove(0);
		            		  i--;
		            	  }
	                }
	    	    }else {
	    	    	if (nave.checkCollisionBullet(b)) {
		    	    	balasEnemigas.remove(i);
		    	    	i--;
		    	    	nave.changeShield();	
	    	    	}
	    	    } 
	      }
    	return nave;
    }
    
    public Nave4 colNaveEnemy(Nave4 nave) {
    	paqueteAyuda p = null;
	      if(!paque.isEmpty()) {
	    	   p = paque.get(0);
	      } 
    	for (int i = 0; i < enemy1.size(); i++) {
    	    IA b = enemy1.get(i);
	          //perdió vida o game over
    	    if(nave.getShield() == false) {
    	    	if (nave.checkCollisionEnemy(b)) {
	            //asteroide se destruye con el choque             
            	 enemy1.remove(i);
            	 enemy2.remove(i);
            	 i--;
    	    	}else {
	            	  if(p != null && nave.checkCollisionpack(p) && paque.isEmpty() == false) {
	            		  paque.remove(0);
	            		  nave.setEscudo();
	            		  i--;
	            	  }
                }
    	    }else {
    	    	if(nave.checkCollisionEnemy(b)) {
    	    		enemy1.remove(i);
    	    		enemy2.remove(i);
    	    		i--;
    	    		nave.changeShield();
    	    	}
    	    } 
	       }
    	return nave;
    }
    
    public void dibujar(SpriteBatch batch) {
    	for (Bullet b : balas) {       
	          b.draw(batch);
	      }
	     for (paqueteAyuda p: paque) {
	    	 p.draw(batch);
	     }
	     for (Bullet p: balasEnemigas) {
	    	 p.draw(batch);
	     }
	     for (IA p: enemy1) {
	    	 p.draw(batch);
	     }   
	     
    }
    
    public void addBullet(Bullet b) {
    	balas.add(b);
    }
    
    public void addBulletEnemy(Bullet b) {
    	balasEnemigas.add(b);
    }
    
    public void addEnemy(IA e) {
    	if(e != null) {
    		enemy1.add(e);
  	    	enemy2.add(e);
    	}
    }
    
    public void updateEnemy(int x, int y, PantallaJuego p) {
    	for (IA ball : enemy1) {
	          ball.actuar(x,y,p);		          
	     }
    	
    	for (int i = 0; i < balasEnemigas.size(); i++) {
  		  Bullet b = balasEnemigas.get(i);
	          b.update();
  	  	}
    	
    }
    
}
