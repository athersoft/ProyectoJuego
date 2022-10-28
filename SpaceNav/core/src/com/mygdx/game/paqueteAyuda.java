package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class paqueteAyuda extends Enemy{
	
	private boolean escudo = false;
	private boolean arma = false;
	private boolean mejora = false;
	private boolean boost = false;
	
	public paqueteAyuda(int x, int y, int size, int xSpeed, int ySpeed, Texture tx) {
		super(x, y, size, xSpeed, ySpeed, tx);
		// TODO Auto-generated constructor stub
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
