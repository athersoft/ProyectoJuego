package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;



public class Nave4 {
	
	private boolean destruida = false;
	private boolean escudo = true;
    private int vidas = 3;
    private float xVel = 0;
    private float yVel = 0;
    private Sprite spr;
    private Sound sonidoHerido;
    private Sound soundBala;
    private Texture txBala;
    private boolean herido = false;
    private int tiempoHeridoMax=50;
    private int tiempoHerido;
    private int vel = 3;
    private Arma arma;
    private int armaLvl = 1;
    
    public Nave4(int x, int y, Texture tx, Sound soundChoque, Texture txBala, Sound soundBala) {
    	sonidoHerido = soundChoque;
    	this.soundBala = soundBala;
    	this.txBala = txBala;
    	spr = new Sprite(tx);
    	spr.setPosition(x, y);
    	//spr.setOriginCenter();
    	spr.setBounds(x, y, 45, 45);
    	arma = new Arma1(1);

    }
    public boolean getShield() {
    	return this.escudo;
    }
    public void draw(SpriteBatch batch, PantallaJuego juego){
        float x =  spr.getX();
        float y =  spr.getY();
        if (!herido) {
	        // que se mueva con teclado
	        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) xVel-=vel;
	        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) xVel+=vel;
        	if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) yVel-=vel;     
	        if (Gdx.input.isKeyPressed(Input.Keys.UP)) yVel+=vel;
        	
	        if (x+xVel < 0 || x+xVel+spr.getWidth() > Gdx.graphics.getWidth())
	        	xVel*=-1;
	        if (y+yVel < 0 || y+yVel+spr.getHeight() > Gdx.graphics.getHeight())
	        	yVel*=-1;
	        
	        spr.setPosition(x+xVel, y+yVel);   
	        xVel = 0;
	        yVel = 0;
	        
 		    spr.draw(batch);
        } else {
           spr.setX(spr.getX()+MathUtils.random(-2,2));
 		   spr.draw(batch); 
 		  spr.setX(x);
 		   tiempoHerido--;
 		   if (tiempoHerido<=0) herido = false;
 		 }
        ////////////////////// disparo//////////////////////////////////////////
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {         
        	arma.disparo(spr.getX(), spr.getY() + 16, juego);
        	soundBala.play();
        }
        
        /////////////////Cambio de arma//////////////////
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.Z)) {  
        	arma = new Arma2(1);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.X)) {  
        	arma = new Arma1(1);
        }
        
       
    }
    
    public void changeShield() {
    	escudo = false;
    }
      
    /////////////////////////////////////////////////////////////////////////////////////////////
    public boolean checkCollisionEnemy(IA b) {
    	

        if(!herido && b.getArea().overlaps(spr.getBoundingRectangle())){
        	// rebote
        	if(escudo == false) {
	            if (xVel ==0) xVel += b.getXSpeed()/2;
	            if (b.getXSpeed() ==0) b.setXSpeed(b.getXSpeed() + (int)xVel/2);
	            xVel = - xVel;
	            b.setXSpeed(-b.getXSpeed());
	            
	            if (yVel ==0) yVel += b.getySpeed()/2;
	            if (b.getySpeed() ==0) b.setySpeed(b.getySpeed() + (int)yVel/2);
	            yVel = - yVel;
	            b.setySpeed(- b.getySpeed());
	
	        	//actualizar vidas y herir
	            if(b.getHit()) {
		            vidas--;
		            herido = true;
		  		    tiempoHerido=tiempoHeridoMax;
		  		    sonidoHerido.play();
		            if (vidas<=0) 
		          	    destruida = true; 
		            return true;
	            }
        	}else {
            	escudo = false;
            }
        }
        
        return false;
    }
    
    public boolean checkCollisionBullet(Bullet b) {
    	if(!herido && b.getArea().overlaps(spr.getBoundingRectangle())){
        	// rebote
    		
    		if(escudo) {
    			escudo = false;
    		}else {
            vidas--;
            herido = true;
  		    sonidoHerido.play();
            if (vidas<=0) 
          	    destruida = true; 
    		}
            return true;
        }
        
        return false;
    }
    
    public void setEscudo() {
    	this.escudo = true;
    }
    
    public boolean checkCollisionpack(paqueteAyuda b) {
    	

        if(b.getArea().overlaps(spr.getBoundingRectangle())){
            
        	//actualizar vidas y herir
            escudo = true;
            return true;
        }
        return false;
    }
	
    
    public boolean estaDestruido() {
       return !herido && destruida;
    }
    public boolean estaHerido() {
 	   return herido;
    }
    
    public void upgradeWeapon() {
    	armaLvl++;
    	if(armaLvl == 2) {
    		arma = new Arma2(1);
    	}
    	if(armaLvl == 3) {
    		arma = new Arma3(1);
    	}
    }
    
    public int getVidas() {return vidas;}
    //public boolean isDestruida() {return destruida;}
    public int getX() {return (int) spr.getX();}
    public int getY() {return (int) spr.getY();}
	public void setVidas(int vidas2) {vidas = vidas2;}
}