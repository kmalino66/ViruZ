/**
 * Author: Kyle Malinowski
 * Title: Player.java
 * Description: Creates the player( in the game ) and gives it values for money and health.
 */
package net.trizmo.viruz;

public class Player {

	int health;
	int money;


	public Player(User user) {
		//Sets the starting money and starting health for the player.
		this.money = user.startingMoney;
		this.health = user.startingHealth;
	}

}