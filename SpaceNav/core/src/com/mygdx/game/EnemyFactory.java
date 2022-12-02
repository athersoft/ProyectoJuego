package com.mygdx.game;

import java.util.ArrayList;

public interface EnemyFactory {
	public IA createEnemy();
	//public IA createBoss();
	public EnemyFactory nextLevel();
}