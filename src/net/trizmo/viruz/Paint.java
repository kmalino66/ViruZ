package net.trizmo.viruz;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Paint {
	//640*200
	public static ImageIcon logo = new ImageIcon("res/textures/logo.png");
	
	public static void paintMenu(int towerSize, int frameW, int frameH, Graphics g){
		g.setColor(Color.gray);
		g.fillRect(0, 0, frameW, frameH);
		//g.drawImage(new ImageIcon("res/textures/logo.png").getImage(), (frameW / 2) - (640 / 2), 50, towerSize * 15, (200/640) * (towerSize * 15), null);
		g.drawImage(logo.getImage(), (frameW / 2) - (640 / 2), 12, null);
		g.setColor(Color.black);
		g.drawRect((frameW / 2) - ((640 / 3) / 2), 12 + 200 + (towerSize / 3), 640 / 3, 200 / 3);
	}
}
