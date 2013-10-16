/**
 * Author: Kyle Malinowski
 * Title: Shot.java
 * Description: Tracks a graphic for when a tower "shoots", may not be in final game.
 */
package net.trizmo.viruz;

public class Shot implements Cloneable{

	public int x;
	public int y;

	public double speed;
	public double hackerDamage;

	public Shot(int x, int y, double speed, double hackerDamage) {
		this.x = x;
		this.y = y;
		this.speed = 6.2D;
		this.hackerDamage = hackerDamage;
	}

	public static void spawnShot(Hacker hacker, int par1X, int par2Y) {
		int xPos;
		int yPos;
		xPos = (par1X * (int)Screen.towerSize) + (250/2);
		yPos = (par2Y * (int)Screen.towerSize) + (int)Screen.towerSize;

	}

}