package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class PantallaJuego implements Screen {

	private SpaceNavigation game;
	private OrthographicCamera camera;	
	private SpriteBatch batch;
	private TextureRegion backgroundTexture;
	private Sound explosionSound;
	private Music gameMusic;
	private int score;
	private int ronda;
	private int velXAsteroides; 
	private int velYAsteroides; 
	private int cantAsteroides;
	private int packPrev;
	private int gluon = 1;
	private int intervalo = 0;
	private int xFondo = 0;
	
	private Nave4 nave;
	private Escudo escudo;
	private  ArrayList<IA> enemy1 = new ArrayList<>();
	private  ArrayList<IA> enemy2 = new ArrayList<>();
	private  ArrayList<paqueteAyuda> paque = new ArrayList<>();
	private  ArrayList<Bullet> balas = new ArrayList<>();
	


	public PantallaJuego(SpaceNavigation game, int ronda, int vidas, int score,  
			int velXAsteroides, int velYAsteroides, int cantAsteroides) {
		this.game = game;
		this.ronda = ronda;
		this.score = score;
		this.velXAsteroides = velXAsteroides;
		this.velYAsteroides = velYAsteroides;
		this.cantAsteroides = cantAsteroides;
		
		batch = game.getBatch();
		camera = new OrthographicCamera();	
		camera.setToOrtho(false, 800, 640);
		//inicializar assets; musica de fondo y efectos de sonido
		backgroundTexture = new TextureRegion(new Texture(Gdx.files.internal("2background (1).jpg")), 0, 0, 1200,800);
		explosionSound = Gdx.audio.newSound(Gdx.files.internal("explot.ogg"));
		explosionSound.setVolume(1,0.05f);
		gameMusic = Gdx.audio.newMusic(Gdx.files.internal("Torero.wav")); //
		
		gameMusic.setLooping(true);
		gameMusic.setVolume(0.5f);
		gameMusic.play();
		
	    // cargar imagen de la nave,   
	    nave = new Nave4(10,Gdx.graphics.getHeight()/2-50,new Texture(Gdx.files.internal("MainShip3.png")),
	    				Gdx.audio.newSound(Gdx.files.internal("hurt.ogg")), 
	    				new Texture(Gdx.files.internal("Rocket2.png")), 
	    				Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3"))); 
        nave.setVidas(vidas);
        
       //Crea el escudo
        
        escudo = new Escudo(nave.getX(), nave.getY(), new Texture(Gdx.files.internal("shield.png")), 
        		Gdx.audio.newSound(Gdx.files.internal("hurt.ogg")));
        
        
        
                
        Random r = new Random();

	    for( int i = 0; i < gluon; i++ ) {
	    	paqueteAyuda sh = new paqueteAyuda(r.nextInt((int)Gdx.graphics.getWidth()),
	  	            50+r.nextInt((int)Gdx.graphics.getHeight()-50),
	  	            20+r.nextInt(10), velXAsteroides+r.nextInt(4), velYAsteroides+r.nextInt(4), 
	  	            new Texture(Gdx.files.internal("package1.png")));
	    	paque.add(sh);
	    }
	}
    
	public void dibujaEncabezado() {
		CharSequence str = "Vidas: "+nave.getVidas()+" Ronda: "+ronda;
		game.getFont().getData().setScale(2f);		
		game.getFont().draw(batch, str, 10, 30);
		game.getFont().draw(batch, "Score:"+this.score, Gdx.graphics.getWidth()-150, 30);
		game.getFont().draw(batch, "HighScore:"+game.getHighScore(), Gdx.graphics.getWidth()/2-100, 30);
	}
	@Override
	public void render(float delta) {
		  Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
          batch.begin();
          batch.draw(backgroundTexture, xFondo, 0);
          batch.draw(backgroundTexture, xFondo+1200, 0);
          xFondo--;
          if(xFondo <= -1200) {
        	  xFondo = 0;
          }
          
		  dibujaEncabezado();
	      if (!nave.estaHerido()) {
	    	  
	    	//crear asteroides
	          if(intervalo == 0) {
	  	        Random r = new Random();
	  		    for (int i = 0; i < cantAsteroides/2; i++) {
	  		        Enemy bb = new Enemy(Gdx.graphics.getWidth()-100+r.nextInt(140),
	  		  	            50+r.nextInt((int)Gdx.graphics.getHeight()-50),
	  		  	            20+r.nextInt(10), -3, velYAsteroides+r.nextInt(4), 
	  		  	            new Texture(Gdx.files.internal("min1.png")));	   
	  		  	    enemy1.add(bb);
	  		  	    enemy2.add(bb);
	  		  	}
	  		    
	  		  for (int i = 0; i < cantAsteroides/2; i++) {
	  		        IA bb = new Enemy2(Gdx.graphics.getWidth()-100+r.nextInt(140),
	  		  	            50+r.nextInt((int)Gdx.graphics.getHeight()-50),
	  		  	            20+r.nextInt(10), -3, velYAsteroides+r.nextInt(4), 
	  		  	            new Texture(Gdx.files.internal("enemy2.png")));	   
	  		  	    enemy1.add(bb);
	  		  	    enemy2.add(bb);
	  		  	}
	  		    
	  		    for (int i = 0; i < packPrev; i++) {
	  		    	Enemy bb = new Enemy(r.nextInt((int)Gdx.graphics.getWidth()),
	  		  	            50+r.nextInt((int)Gdx.graphics.getHeight()-50),
	  		  	            20+r.nextInt(10), velXAsteroides+r.nextInt(4), velYAsteroides+r.nextInt(4), 
	  		  	            new Texture(Gdx.files.internal("min1.png")));	   
	  		  	    enemy1.add(bb);
	  		  	    enemy2.add(bb);
	  		  	}
	  		    intervalo = 180;
	          }else {
	        	  intervalo--;
	          }
	    	  
		      // colisiones entre balas y asteroides y su destruccion  
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
		            	 }
		            	 
		            	 score +=10;
		              }   	  
		  	        }
		                
		            if (b.isDestroyed()) {
		                balas.remove(b);
		                i--; //para no saltarse 1 tras eliminar del arraylist
		            }
		      }
		      //actualizar movimiento de asteroides dentro del area
	    	  for (int j = 0; j < enemy1.size(); j++) {    
	    		  IA b = enemy1.get(j);
	    		  if( b.isDestroyed()) {
			    		enemy1.remove(j);
			            enemy2.remove(j);
			            j--;
			    	 }
	    	  }
	    	  
	    	/////LLamado a IA de los enemigos////////////
		      for (IA ball : enemy1) {
		          ball.actuar();
		      }
		      
	      }
	      //dibujar balas
	     for (Bullet b : balas) {       
	          b.draw(batch);
	      }
	     for (paqueteAyuda p: paque) {
	    	 p.draw(batch);
	     }
	      nave.draw(batch, this);
	      if(nave.getShield()) {
	    	  escudo.draw(batch, this, nave);
	      }
	      //dibujar asteroides y manejar colision con nave
	      paqueteAyuda p = null;
	      if(!paque.isEmpty()) {
	    	   p = paque.get(0);
	      }
	      for (int i = 0; i < enemy1.size(); i++) {
	    	    IA b = enemy1.get(i);
	    	    
	    	    b.draw(batch);
	    	    /*try {
                    Thread.sleep(2000);
                }
                catch(InterruptedException ie){
                    ie.printStackTrace();
                }*/
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
		            		  i--;
		            	  }
	                }
	    	    }else {
	    	    	if(escudo.checkCollision(b)) {
	    	    		enemy1.remove(i);
	    	    		enemy2.remove(i);
	    	    		i--;
	    	    		nave.changeShield();
	    	    	}
	    	    } 
  	        }
	      
	      if (nave.estaDestruido()) {
  			if (score > game.getHighScore())
  				game.setHighScore(score);
	    	Screen ss = new PantallaGameOver(game);
  			ss.resize(1200, 800);
  			game.setScreen(ss);
  			dispose();
  		  }
	      batch.end();
	      //nivel completado
	      if (enemy1.size()==0) {
			Screen ss = new PantallaJuego(game,ronda+1, nave.getVidas(), score, 
					velXAsteroides+3, velYAsteroides+3, cantAsteroides+10);
			ss.resize(1200, 800);
			game.setScreen(ss);
			dispose();
		  }
	    	 
	}
    
    public boolean agregarBala(Bullet bb) {
    	return balas.add(bb);
    }
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		gameMusic.play();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		this.explosionSound.dispose();
		this.gameMusic.dispose();
	}
   
}
