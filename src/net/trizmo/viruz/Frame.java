/**
 * Author: Kyle Malinowski
 * Title: Frame.java
 * Description: Makes the frame for the game. 
 */
package net.trizmo.viruz;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Frame extends JFrame{

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){

			public void run() {
				new Frame();				
			}


		});

	}

	public Frame() {
		new JFrame();

		this.setTitle("ViruZ! - Trizmo515 - Version 1.0");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		//Sets full screen
		this.setExtendedState(MAXIMIZED_BOTH);

		//Sets the size if full screen not wanted
		//this.setSize(920, 640);
		this.setUndecorated(true);
		this.setResizable(false);
		this.setVisible(true);

		Screen screen = new Screen(this);
		this.add(screen);
	}

}