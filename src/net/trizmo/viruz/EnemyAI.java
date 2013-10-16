/**
 * Author: Kyle Malinowski
 * Title: EnemyAI.java
 * Description: Handles the AI for the enemies.
 */
package net.trizmo.viruz;

public class EnemyAI {

	public EnemyAI(int id) {
		super();
	}

	//This handles how much and if the enemy should move.
	public static void move(EnemyMove enemy) {
		if(enemy != null){
			int gridY = (int)(enemy.yPos / Screen.towerSize);
			enemy.nextPos = Screen.firstHit[((int) ((int)enemy.yPos / Screen.towerSize))] - 1;
			enemy.target = null;
			enemy.server = false;
			enemy.attack = false;

			if(enemy.xPos < (int)(enemy.nextPos * Screen.towerSize) && enemy.attack == false) {
				enemy.xPos += enemy.enemy.speed / 24;
				enemy.target = null;
				enemy.server = false;
				enemy.attack = false;
			}else{
				if(Screen.firstHit[gridY] == 14) {
					enemy.target = null;
					enemy.server = true;
					enemy.attack = true;
				}else{
					enemy.target = Screen.firstHacker[gridY];
					enemy.attack = true;
				}

			}
		}

	}

}