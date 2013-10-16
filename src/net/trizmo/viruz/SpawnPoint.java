/**
 * Author: Kyle Malinowski
 * Title: SpawnPoint.java
 * Description: Sets where the enemy will spawn.
 */
package net.trizmo.viruz;

public class SpawnPoint {

	private int x;
	private int y;

	public SpawnPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}