//Author: Kyle Malinowski
//Date Created: 8/23/2013
//Title: User.java
//Description: Manages the user.

package net.trizmo.viruz;

public class User {

	private Screen screen;

	Player player;

	int startingMoney = 300;
	int startingHealth = 100;

	public User(Screen screen) {
		this.screen = screen;


		this.screen.scene = 0;//Sets scene to main menu
	}
	//Creates Player
	public void createPlayer() {
		this.player = new Player(this);
	}

}