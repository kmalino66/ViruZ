/**
 * Author: Kyle Malinowski
 * Title: KeyHandler.java
 * Descrition: Handles key presses that occur in the game.
 */
package net.trizmo.viruz;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

	private Screen screen;
	private Screen.KeyTyped keyTyped;

	public KeyHandler(Screen screen) {
		this.screen = screen;
		this.keyTyped = this.screen.new KeyTyped();
	}

	public void keyPressed(KeyEvent arg0) {
		int keyCode = arg0.getKeyCode();
		System.out.println(keyCode);

	// Checks if the Enter key is pressed
		if(keyCode == 10) {
			this.keyTyped.keyEnter();
		}

	// Checks if the Escape key is pressed.
		if(keyCode == 27) {
			this.keyTyped.keyESC();
		}

	// Checks if the Space key is pressed.
		if(keyCode == 32) {
			this.keyTyped.keySPACE();
		}
		//Checks if the S key is pressed.
		if(keyCode == 83) {
			this.keyTyped.keyS();
		}
	}

	public void keyReleased(KeyEvent arg0) {

	}

	public void keyTyped(KeyEvent arg0) {

	}

}