/**
 * Author: Kyle Malinowski
 * Title: Screen.java
 * Description: Makes the screen and draws what the player sees.
 */
package net.trizmo.viruz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Screen extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;

	Thread thread = new Thread(this);
	Frame frame;
	Wave wave;
	User user;

	public SpawnPoint spawn;

	@SuppressWarnings("unused")
	private int fps = 0;
	private int sync_fps = 0;

	public int scene = 0;
	public int hand = 0;
	public int handXPos = 0;
	public int handYPos = 0;
	public int frameHeight;
	public int frameWidth;
	public int finalH;
	public int finalW;

	public boolean running = false;

	public static double towerSize;

	public int[][] map = new int[15][5];
	public Hacker[][] hackerMap = new Hacker[15][5];
	public Image[] terrain = new Image[100];
	public EnemyMove[] enemyMap = new EnemyMove[200];
	public static int[] firstHit = new int[5];
	public static Hacker[] firstHacker = new Hacker[5];

	public Hacker selectedHacker;

	public Screen(Frame frame) {
		this.frame = frame;

		this.frame.addKeyListener(new KeyHandler(this));
		this.frame.addMouseListener(new MouseHandler(this));
		this.frame.addMouseMotionListener(new MouseHandler(this));

		//frameHeight = (this.frame.getWidth()) / 16*9;
		frameWidth = this.frame.getWidth();
		frameHeight = this.frame.getHeight();

		finalH = frameHeight / (16/9);
		finalW = frameWidth / (16/9);

		towerSize = finalW / 17;


		thread.start();
	}

	public void paintComponent(Graphics g){
		g.clearRect(0, 0, this.frame.getWidth(), this.frame.getHeight());

		if(scene == 0) {
			g.setColor(Color.blue);
			g.fillRect(0, 0, this.frame.getWidth(), this.frame.getHeight());
		}else if (scene == 1) {
			//Back
			g.setColor(Color.green);
			g.fillRect(0, 0, this.frame.getWidth(), this.frame.getHeight());

			//Grid
			g.setColor(Color.gray);
			for(int x = 0; x < 15; x++) {
				for(int y = 0; y < 5; y++) {

					if(x == 14) {
						g.drawImage(new ImageIcon("res/textures/Server.png").getImage(), (int)towerSize + (x * (int)towerSize), 50 + (y * (int)towerSize), (int)towerSize, (int)towerSize, null);
					}else {
						g.drawImage(terrain[map[x][y]], (int)towerSize + (x * (int)towerSize), 50 + (y * (int)towerSize), (int)towerSize, (int)towerSize, null);
					}
					g.drawRect((int)towerSize + (x * (int)towerSize), 50 + (y * (int)towerSize), (int)towerSize, (int)towerSize);
				}
			}
			//9
			//Upgrade Menu
			g.setColor(Color.GRAY);
			g.drawRect((int)(10 * towerSize), (int)(5 * towerSize) + 75 , (int)towerSize * 5, (int)towerSize * 3);
			g.drawRect((int)towerSize * 10, (int)towerSize * 5 + 75, (int)towerSize, (int)towerSize);
			if(selectedHacker != null) {
				g.drawImage(Hacker.hackerList[selectedHacker.id].texture, (int)(10 * towerSize), (int)(5 * towerSize) + 75, (int)towerSize, (int)towerSize, null);
			}else{
				g.drawImage(new ImageIcon("res/textures/Server.png").getImage(), (int)(10 * towerSize), (int)(5 * towerSize) + 75, (int)towerSize, (int)towerSize, null);
			}
			//Wave, money, health
			for (int x = 0; x < 3; x++) {
				g.drawRect((int)towerSize, 50 + 25 + (5 * (int)towerSize) + (x * ((int)towerSize / 3)), (int)towerSize, (int)towerSize / 3);
			}

			//Sets the text inside the boxes
			int fontSize = (int)towerSize / 8;
			Font font = new Font("Arial", Font.PLAIN, fontSize);
			g.setFont(font);
			g.drawString("Health :" + user.player.health, (int) ((int)towerSize + (((int)towerSize) / 4) - (((int)towerSize) / 4.5)), 50 + 25 + (5 * (int)towerSize) + (0 * (int)towerSize / 3) + (((int)towerSize / 3) / 2));
			g.drawString("Money :" + user.player.money, (int)towerSize + (((int)towerSize) / 4) - (int)((int)towerSize / 4.5), 50 + 25 + (5 * (int)towerSize) + (1 * (int)towerSize / 3) + (((int)towerSize / 3) / 2));
			g.drawString("Wave :" + wave.waveNumber, (int)towerSize + (((int)towerSize) / 4) - (int)((int)towerSize / 4.5), 50 + 25 + (5 * (int)towerSize) + (2 * (int)towerSize / 3) + (((int)towerSize / 3) / 2));

			//left scroll
			g.drawRect((int)towerSize + (int)towerSize + ((int)towerSize / 2), 50 + 25 + (5 * (int)towerSize), ((int)towerSize / 2), (int)towerSize);

			//right scroll
			g.drawRect((int)towerSize + (int)towerSize + ((int)towerSize) + (10 * ((int)towerSize / 2)) - 1, 50 + 25 + (5 * (int)towerSize) + (0 * ((int)towerSize / 2)), ((int)towerSize / 2), (int)towerSize);

			//10*2 with height half the size of the box
			for(int x = 0; x < 10; x++) {
				for(int y = 0; y < 2; y++) {
					g.drawRect((int)towerSize + (int)towerSize + ((int)towerSize) + (x * ((int)towerSize / 2)) - 1, 50 + 25 + (5 * (int)towerSize) + (y * ((int)towerSize / 2)), ((int)towerSize / 2), (int)towerSize / 2);

				}
			}
			//tower list
			for(int x = 0; x < 10; x++) {
				for(int y = 0; y < 2; y++) {
					if(Hacker.hackerList[x * 2 + y] != null) {
						g.drawImage(Hacker.hackerList[x * 2 + y].texture, (int)towerSize + (int)towerSize + ((int)towerSize) + (x * ((int)towerSize / 2)) - 1, 50 + 25 + (5 * (int)towerSize) + (y * ((int)towerSize / 2)), (int)towerSize / 2, (int) towerSize / 2, null);
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
			//hackers on grid
			for(int x = 0; x < 15; x++) {
				for(int y = 0; y < 5; y++) {
					if(hackerMap[x][y] != null) {
						if(selectedHacker == hackerMap[x][y]){
							g.setColor(new Color(245, 0, 61, 100));
							g.fillRect((int)((int)towerSize + (x * towerSize)),(int)( 50 + (y * towerSize)), (int)towerSize, (int)towerSize);
						}
						g.drawImage(Hacker.hackerList[hackerMap[x][y].id].texture, (int)towerSize + (x * (int)towerSize), 50 + (y * (int)towerSize), (int)towerSize, (int)towerSize, null);
						if(hackerMap[x][y].attackDelay < 0.35) {
							g.drawImage(Hacker.hackerList[hackerMap[x][y].id].fireTexture, (int)towerSize + (x * (int)towerSize), 50 + (y * (int)towerSize), (int)towerSize, (int)towerSize, null);

						}
					}
				}
			}

			//hand
			if(hand != 0 && Hacker.hackerList[hand - 1] != null) {
				g.drawImage(Hacker.hackerList[hand - 1].texture, this.handXPos - (int)towerSize / 2, this.handYPos - (int)towerSize / 2, (int)towerSize, (int)towerSize, null);
			}
			//Enemies
			for(int i = 0; i < enemyMap.length; i++) {
				if(enemyMap[i] != null){
					g.drawImage(enemyMap[i].enemy.texture, (int)towerSize + (int)enemyMap[i].xPos, 50 + (int)enemyMap[i].yPos, (int)towerSize, (int)towerSize, null);
				}
			}

		}

	}

	public void loadGame() {
		user = new User(this);
		wave = new Wave(this);
		for(int y = 0; y<10; y++){
			for(int x = 0; x < 10; x++){
				terrain[x + (y * 10)] = new ImageIcon("res/textures/floor/off.png").getImage();
				terrain[x + (y * 10)] = createImage(new FilteredImageSource(terrain[x + (y * 10)].getSource(), new CropImageFilter(x *25, y*25, 25 , 25)));
			}
		}

		running = true;
	}

	public void startGame(User user) {
		user.createPlayer();
		System.out.println(towerSize);
		this.scene = 1;
		this.wave.waveNumber = 0;
	}

	public void run() {
		loadGame();

		long lastFrame = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			double time = (double)System.currentTimeMillis() / 1000;
			int timeMilli = (int)(Math.round((time - (int)time) * 1000));

			repaint();
			enemyUpdate();

			frames++;
			//fps
			if(System.currentTimeMillis() - 1000 >= lastFrame) {
				fps = frames;
				frames = 0;
				lastFrame = System.currentTimeMillis();
			}
			findFirst();

			if(timeMilli > sync_fps * 1000 / 25){
				sync_fps++;
				update();
				for(int par1 = 0; par1 < enemyMap.length; par1++){
					enemyAttackCheck(enemyMap[par1]);
				} 

				if(sync_fps >= 1000/25) {
					sync_fps = 0;
				}
			}

			if(timeMilli + 1000/ 25 < sync_fps * 1000 /25) {
				sync_fps = 0;
			}

			try{
				Thread.sleep(1);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.exit(0);
	}

	public class KeyTyped {
		public void keyESC() {
			running = false;
		}
		public void keySPACE() 
		{
			startGame(user);
		}
		public void keyEnter()
		{
			if(!wave.waveSpawning){
				wave.nextWave();
			}
		}

	}

	public class MouseHeld {
		boolean mouseDown = false;

		public void mouseMoved(MouseEvent e) {
			handXPos = e.getXOnScreen();
			handYPos = e.getYOnScreen();
		}
		Hacker[] hackerL = Hacker.hackerList;
		public void updateMouse(MouseEvent e) {
			if(scene == 1) {
				hand = Hacker.getHackerClicked(e, mouseDown, hand, hackerL, (int)towerSize, user);
				System.out.println(hand);
				//				
			}
		}

		public void mouseDown(MouseEvent e) {
			mouseDown = true;

			if(hand != 0) {
				//Place hacker
				placeHacker(e.getXOnScreen(), e.getYOnScreen());

				hand = 0;
			}else{
				selectTower(e.getX(), e.getY());
			}
			updateMouse(e);
		}

		private void selectTower(int x, int y) {
			int xPos = (x - (int)towerSize) / (int)towerSize;
			int yPos = (y - 50) / (int)towerSize;

			if(xPos >= 0 && xPos <= 13 && yPos < 5 && yPos >= 0) {
				selectedHacker = hackerMap[xPos][yPos];
			}else{
				selectedHacker = null;
			}

		}
	}

	public void placeHacker(int x, int y) {

		int xPos = (x - (int)towerSize) / (int)towerSize;
		int yPos = (y - 50) / (int)towerSize;

		if(xPos >= 0 && xPos <= 13 && yPos <= 5 && yPos >= 0) {
			if(hackerMap[xPos][yPos] == null){
				user.player.money -= Hacker.hackerList[hand - 1].cost;
				hackerMap[xPos][yPos] = (Hacker) Hacker.hackerList[hand - 1].clone();
				selectedHacker = hackerMap[xPos][yPos];
			}
		}
	}

	public void FindSpawn() {
		Random rand = new Random();
		int start = rand.nextInt(5);
		spawn = new SpawnPoint(0, start);	
	}

	public void update() {
		findFirst();
		hackerUpdate();

		if(wave.waveSpawning) {
			wave.spawnEnemies();
		}

	}

	public void enemyUpdate(){
		for(int i = 0; i < enemyMap.length; i++) {
			EnemyAI.move(enemyMap[i]);
		}
		for(int i = 0; i < enemyMap.length; i++) {
			isEnemyAlive(enemyMap[i], i);//Is the enemy alive?
		}

	}

	public void towerUpdate() {
		for(int x = 0; x < 15; x++) {
			for(int y = 0; y < 5; y++) {
				if(hackerMap[x][y].health <= 0) {
					hackerMap[x][y] = null;
				}
			}
		}
	}

	public void hackerUpdate(){
		for(int x = 0; x < 15 ; x++) {
			for(int y = 0; y < 5; y++){
				if(hackerMap[x][y] != null && hackerMap[x][y].health <= 0) {
					hackerMap[x][y] = null;
				}

				if(hackerMap[x][y] != null) {
					towerAttack(x, y);
				}
			}
		}
	}

	public void towerAttack(int x, int y) {
		if(this.hackerMap[x][y].target == null) {
			if(this.hackerMap[x][y].attackDelay > this.hackerMap[x][y].maxAttackDelay) {
				EnemyMove currentEnemy  = this.hackerMap[x][y].calculateEnemy(enemyMap, x, y);

				if(currentEnemy != null) {
					currentEnemy.health -= (int)this.hackerMap[x][y].damage;

					this.hackerMap[x][y].target = currentEnemy;
					this.hackerMap[x][y].attackTime = 0;
					this.hackerMap[x][y].attackDelay = 0;

				}

			}else{
				this.hackerMap[x][y].attackDelay += 0.1;

			}
		}else{
			if(this.hackerMap[x][y].attackTime < this.hackerMap[x][y].maxAttackTime ) {
				this.hackerMap[x][y].attackTime += 0.1;
			}else{
				this.hackerMap[x][y].target = null;
			}
		}
	}

	public void enemyAttackCheck(EnemyMove enemy) {

		if(enemy != null) {
			if(enemy.target != null) {
				if(enemy.attack){
					enemyAttackHacker(enemy);
				}

			}else if(enemy.server) {
				if(enemy.attack){
					enemyAttackServer(enemy);
				}

			}
		}
	}

	public void enemyAttackHacker(EnemyMove currentEnemy) {
		if(currentEnemy.enemy.currentAttack >= currentEnemy.enemy.attackSpeed) {
			currentEnemy.target.health -= currentEnemy.enemy.damage;

			currentEnemy.enemy.currentAttack = 0;
		}else{
			currentEnemy.enemy.currentAttack += 0.1;
		}
	}

	public void enemyAttackServer(EnemyMove currentEnemy) {
		if(currentEnemy.enemy.currentAttack >= currentEnemy.enemy.attackSpeed) {
			user.player.health -= currentEnemy.enemy.damage;

			currentEnemy.enemy.currentAttack = 0;
			currentEnemy.health = 0;
		}else{
			currentEnemy.enemy.currentAttack += 0.1;
		}
	}

	public void findFirst(){
		for(int i = 0; i < 5; i++) {
			firstHit[i] = 14;
		}
		for(int y = 0; y < 5; y++) {
			for(int x = 0; x < 15; x++){

				if(hackerMap[x][y] != null) {
					firstHit[y] = x;
					firstHacker[y] = hackerMap[x][y];

					break;
				}
			}
		}
	}

	public void isEnemyAlive(EnemyMove enemy, int index) {
		if(enemyMap[index] != null ) {
			if(enemy.health <= 0) {
				if(!enemy.server) {
					user.player.money += enemyMap[index].enemy.price;
				}
				enemyMap[index] = null;

			}

		}
	}

	public void spawn(int par1EnemyId) {
		for(int i = 0; i < enemyMap.length; i++) {
			if(enemyMap[i] == null) {
				FindSpawn();
				enemyMap[i] = (EnemyMove) new EnemyMove(Enemy.enemyList[par1EnemyId], spawn).clone();
				break;
			}
		}
	}

	public void spawnWorm() {
		for(int i = 0; i < enemyMap.length; i++) {
			if(enemyMap[i] == null) {
				FindSpawn();
				enemyMap[i] = (EnemyMove) new EnemyMove(Enemy.worm, spawn).clone();
			}
		}
	}

}