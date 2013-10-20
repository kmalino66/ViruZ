/**
 * Author: Kyle Malinowski
 * Title: Enemy.java
 * Description: Initializes all enemies.  Also sets the textures
 */
package net.trizmo.viruz;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Enemy {

	public static int enemyID = 0;

	public static final Enemy[] enemyList = new Enemy[200];
//															id			price	damage	health	speed	attackSpeed	currentAttack
	public static final Enemy trojan = new EnemyTrojan(		enemyID++, 	15, 	4, 		1, 		2.5, 	1.5, 		0.0).getTextureFile("trojan");
	public static final Enemy worm = new EnemyWorm(			enemyID++,	30,		5,		2,		2.4,	2.0,		0.0).getTextureFile("worm");
	public static final Enemy virus = new EnemyVirus(		enemyID++,	50,		11,		25,		5.5,	0.9,		0.0).getTextureFile("virus");
	public static final Enemy rootkit = new EnemyRootkit(	enemyID++,	100,	20,		50,		3.5,	0.5,		0.0).getTextureFile("rootkit");

	public String textureFile;
	public Image texture;

	public int id;
	public int price;
	public int damage;
	public int health;

	public double speed;
	public double attackSpeed;
	public double currentAttack;

	public Enemy(int id, int price, int damage, int health, double speed, double attackSpeed, double currentAttack) {

		if(enemyList[id] != null) {
		//Called if the initialization of enemies has failed.
			System.out.println("[Failed] Two enemies have the same id.");
		}else{
		//If enemy intitalization has not failed it creates the enemy
			this.id = id;
			this.price = price;
			this.damage = damage;
			this.health = health;
			this.speed = speed * Screen.towerSize / 50;
			this.attackSpeed = attackSpeed;
			this.currentAttack = currentAttack;
			enemyList[id] = this;
		}
	}
//Sets the texture for the enemy/
	public Enemy getTextureFile(String str) {
		this.textureFile = str;
		this.texture = new ImageIcon("res/textures/enemies/" + this.textureFile + ".png").getImage();

		return this;
	}

	public static void startup(){}

}