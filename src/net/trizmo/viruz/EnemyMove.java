/**
 * Author: Kyle Malinowski
 * Title: EnemyMove.java
 * Description: Actual enemies.  Gets spawn.
 */
package net.trizmo.viruz;

public class EnemyMove implements Cloneable{

	Enemy enemy;
	Hacker target;
	static SpawnPoint spawn;

	double xPos;
	double yPos;

	boolean attack;
	boolean server;

	int nextPos;
	int health;
	static int start;

//Constructor
	public EnemyMove(Enemy enemy1, SpawnPoint sp1) {
		this.enemy = enemy1;
		this.xPos = sp1.getX() * (int)Screen.towerSize;
		this.yPos = sp1.getY() * (int)Screen.towerSize;
		this.nextPos = 0;
		this.attack = false;
		this.server = false;
		this.target = null;
		if(enemy1 != null) {
			this.health = enemy1.health;
		}
	}

	protected Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

//Checks if actual  enemy is still alive.
	public EnemyMove update() {
		EnemyMove currentEnemy = this;

		if(currentEnemy.health <= 0) {
			return null;
		}

		return currentEnemy;
	}


}