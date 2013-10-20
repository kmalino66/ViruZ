/**
 * Author: Kyle Malinowski
 * Title: Hacker.java
 * Description: Initializes all the 'hackers' that can be placed by the player.
 */
package net.trizmo.viruz;

import java.awt.Image;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

public class Hacker implements Cloneable{

	public String textureFile = "";
	public Image texture;
	public Image fireTexture;
	public EnemyMove target;

	public static int towerID = 0;


	public int id;
	public int cost;
	public int health;
	public int range;

	public double damage;
	public double maxAttackTime;
	public double maxAttackDelay;
	public double attackDelay;
	public double attackTime;

//Tower array
	public static final Hacker [] hackerList = new Hacker[200];

// Tower Creator									// 								id			cost	health	range	damage	attackTime	attackDelay
	public static final Hacker enthusiastHacker = new HackerEnthusiast(				towerID++, 	100, 	20, 	14, 	1, 		1.0, 		3.0).getTextureFile("EnthusiastHacker");
	public static final Hacker professionalHacker = new HackerProfessional(			towerID++, 	250, 	45, 	14, 	5, 		1.0, 		2.5).getTextureFile("ProfessionalHacker");
	public static final Hacker beginnerProgrammer = new HackerBeginnerProgrammer(	towerID++, 	130, 	25, 	14, 	1.5, 	1.0, 		3.1).getTextureFile("BeginnerProgrammer");
	public static final Hacker advancedProgrammer = new HackerAdvancedProgrammer(	towerID++, 	300, 	50, 	14, 	6.5, 	1.0, 		2.3).getTextureFile("AdvancedProgrammer");
	public static final Hacker firewall = new HackerFirewall(						towerID++, 	150, 	85, 	0, 		0.0, 	0.0, 		0.0).getTextureFile("firewall");

	public Hacker(int id, int cost, int health, int range, double damage, double maxAttackTime, double maxAttackDelay) {
		if(hackerList[id] != null) {
		// Called if there are two 'hackers' with the same id.
			System.out.println("[Failed] Tower Initialization - Two or more towers with the same id.");
		}else {
		// If all towers can be initialized, it initializes them.
			hackerList[id] = this;
			this.id = id;
			this.cost = cost;
			this.health = health;
			this.range = range;
			this.target = null;
			this.damage = damage;
			this.maxAttackTime = maxAttackTime;
			this.maxAttackDelay = maxAttackDelay;
		}
	}

	public EnemyMove calculateEnemy(EnemyMove[] enemies, int x, int y) {
	//What is the first enemy?
		EnemyMove[] enemiesInLine = new EnemyMove[enemies.length];

		for(int par1 = 0; par1 < enemies.length; par1++) {
			if(enemies[par1] != null) {
				if(enemies[par1].yPos == y * (int)Screen.towerSize) {
					enemiesInLine[par1] = enemies[par1];
				}
			}
		}
		int close = -1;
		int enemyNum = 0;
		for(int par2 = 0; par2 < enemiesInLine.length; par2++) {
			if(enemiesInLine[par2] != null && enemiesInLine[par2].xPos > close) {
				close = (int) enemiesInLine[par2].xPos;
				enemyNum = par2;
				System.out.println(enemiesInLine[par2].yPos);
				System.out.println(y + "ss" + y * (int)Screen.towerSize);
			}
		}
		if(enemies[enemyNum] != null && enemies[enemyNum].yPos == y * (int)Screen.towerSize) {
			return enemies[enemyNum];
		}else{
			return null;
		}
	}

	public static int getHackerClicked(MouseEvent e, boolean mouseDown, int hand, Hacker[] hacker, int towerSize, User user) {
		if(mouseDown && hand == 0) {

			for(int xP = 0; xP < 10; xP++){
				for(int yP = 0; yP < 2; yP++) {
					if(
							e.getXOnScreen() >= ((int)towerSize) + (2 * towerSize) + ((xP) * (towerSize / 2))
							&& e.getXOnScreen() <= (int)towerSize + (2 * towerSize) + ((xP + 1) * (towerSize / 2)) 
							&& e.getYOnScreen() >= 75 + (5 * towerSize) + ((yP) * (towerSize / 2))
							&& e.getYOnScreen() <= 75 + (5 * towerSize) + ((yP + 1) * (towerSize / 2))){
						int par1 = (xP * 2) + yP;
						if(user.player.money >= Hacker.hackerList[par1].cost) {
							return par1 + 1;
						}

					}
				}
			}


		}
		return 0;
	}

	protected Object clone(){
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		return null;
	}

//Gets the texture file for the towers
	public Hacker getTextureFile(String str) {
		this.textureFile = str;
		this.texture = new ImageIcon("res/textures/hacker/" + this.textureFile + ".png").getImage();
		this.fireTexture = new ImageIcon("res/textures/hacker/fire/" + this.textureFile + ".png").getImage();
		return null;
	}

}