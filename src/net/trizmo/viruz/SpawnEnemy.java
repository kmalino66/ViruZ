/**
 * Author: Kyle Malinowski
 * Title: SpawnEnemy.java
 * Description: When a method in  here gets called, it spawns it's respective enemy.
 */
package net.trizmo.viruz;

public class SpawnEnemy {
	Screen screen;
	
	//Spawns a trojan enemy
	public void spawnTrojan() {
		for(int i = 0; i < screen.enemyMap.length; i++) {
			if(screen.enemyMap[i] == null) {
				screen.FindSpawn();
				screen.enemyMap[i] = (EnemyMove) new EnemyMove(Enemy.trojan, screen.spawn).clone();
				break;
			}
		}
	}

	//Spawns a worm enemy
	public void spawnWorm() {
		for(int i = 0; i < screen.enemyMap.length; i++) {
			if(screen.enemyMap[i] == null) {
				screen.FindSpawn();
				screen.enemyMap[i] = (EnemyMove) new EnemyMove(Enemy.worm, screen.spawn).clone();
			}
		}
	}
}