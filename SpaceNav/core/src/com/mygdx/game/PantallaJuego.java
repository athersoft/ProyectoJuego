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
	private int progress;
	private int ronda;
	private int xFondo = 0;
	private Escudo escudo;
	
	private Nave4 nave;
	
	Instancias instancias = Instancias.getInstance();	
	private EnemyFactory factory = new Nivel1(60);
	

	public PantallaJuego(SpaceNavigation game, int ronda, int vidas, int score,  
			int velXAsteroides, int velYAsteroides, int cantAsteroides) {
		this.game = game;
		this.ronda = ronda;
		this.score = score;
		progress = score;
		batch = game.getBatch();
		camera = new OrthographicCamera();	
		camera.setToOrtho(false, 800, 640);
		//inicializar assets; musica de fondo y efectos de sonido
		backgroundTexture = new TextureRegion(new Texture(Gdx.files.internal("2background (1).jpg")), 0, 0, 1200,800);
		explosionSound = Gdx.audio.newSound(Gdx.files.internal("Damage.mp3"));
		explosionSound.setVolume(1,0.05f);
		gameMusic = Gdx.audio.newMusic(Gdx.files.internal("Torero.mp3")); //
		
		gameMusic.setLooping(true);
		gameMusic.setVolume(0.5f);
		gameMusic.play();
		
	    // cargar imagen de la nave,   
	    nave = new Nave4(10,Gdx.graphics.getHeight()/2-50,new Texture(Gdx.files.internal("MainShip3.png")),
	    				Gdx.audio.newSound(Gdx.files.internal("Damage.mp3")), 
	    				new Texture(Gdx.files.internal("Rocket2.png")), 
	    				Gdx.audio.newSound(Gdx.files.internal("shoot.mp3"))); 
        nave.setVidas(vidas);
        
        escudo = new Escudo(nave.getX(), nave.getY(), new Texture(Gdx.files.internal("shield.png")), 
        		Gdx.audio.newSound(Gdx.files.internal("hurt.ogg")));
        
        
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
          
        //Movimiento del fondo
          batch.draw(backgroundTexture, xFondo, 0);
          batch.draw(backgroundTexture, xFondo+1200, 0);
          xFondo--;
          if(xFondo <= -1200) {
        	  xFondo = 0;
          }
          
		  dibujaEncabezado();
		  
	      if (!nave.estaHerido()) {
	    	  
	    	 //Crear enemigos
	    	  instancias.addEnemy(factory.createEnemy());
  		                	  
		      // colisiones entre balas y asteroides y su destruccion  
	    	  int a = instancias.colBalaEnemigo(explosionSound);
	    	  progress += a;
	    	  score+= a;
	    	  
	    	  
	    	/////LLamado a IA de los enemigos////////////
	    	  instancias.updateEnemy(nave.getX(),nave.getY(), this);
		      
		      
	      }
	      
	      //Dibujar instancias
	      instancias.dibujar(batch);
	      nave.draw(batch, this);
	      
	      nave = instancias.colNavePaq(nave);
	      
	      if(nave.getShield()) {
	    	  escudo.draw(batch, this, nave);
	      } 
	      
	      //Colision entre balas y jugdor
	      nave = instancias.colNaveBala(nave);
	          
	      //Colision entre jugador y enemigos
	      nave = instancias.colNaveEnemy(nave);
	      
	      if (nave.estaDestruido()) {
	    	  instancias.reset();
  			if (score > game.getHighScore())
  				game.setHighScore(score);
	    	Screen ss = new PantallaGameOver(game);
  			ss.resize(1200, 800);
  			game.setScreen(ss);
  			dispose();
  		  }
	      batch.end();
	      
	      //Cambio de nivel
	      if (progress == 200) {
	    	  instancias.crearPaquete();
	    	  factory = factory.nextLevel();
	    	  progress = 0;    	  
		  }
	      
	      if(score == 500) {
	    	  nave.upgradeWeapon();
	      }
	      if(score == 500) {
	    	  nave.upgradeWeapon();
	      }
	    	 
	}
    
    public void agregarBala(Bullet bb) {
    	instancias.addBullet(bb);
    }
    
    public void agregarBalaEnemiga(Bullet bb) {
    	instancias.addBulletEnemy(bb);
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
