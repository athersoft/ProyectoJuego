package com.mygdx.game;

import java.util.ArrayList;

public interface EnemyFactory {
	public IA createEnemy(ArrayList<IA> enemy1, ArrayList<IA> enemy2);
	//public IA createBoss();
	public EnemyFactory nextLevel();
}