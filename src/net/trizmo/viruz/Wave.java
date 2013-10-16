/**
 * Author: Kyle Malinowski
 * Title: Wave.java
 * Description: Handles when and what to spawn in different waves.  
 */
package net.trizmo.viruz;

public class Wave {

	Screen screen;
	SpawnEnemy spawnEnemy;

	int waveNumber = 0;

	int trojansPerRound = 1;
	int trojansPerRoundAfter = 2;
	int trojansThisRound = 0;
	boolean trojans = true;

	int wormsPerRound = 2;
	int wormsThisRound = 0;
	boolean worm = false;

	int virusPerRound = 4;
	int virusThisRound = 0;
	boolean virus = false;

	int rootkitPerRound = 2;
	int rookkitThisRound = 0;
	boolean rootkit = false;

	boolean waveSpawning;

	public Wave(Screen screen) {
		this.screen = screen;
	}

	public void nextWave() {
		this.waveNumber++;

		this.trojansThisRound = 0;
		this.wormsThisRound = 0;
		this.virusThisRound = 0;
		this.rookkitThisRound = 0;

		this.waveSpawning = true;

		System.out.println("[Wave] Wave " + this.waveNumber + " incomming!");

		for(int i = 0; i < this.screen.enemyMap.length; i++){
			this.screen.enemyMap[i] = null;
		}
	}

	private int currentDelay = 0;
	private int currentDelayWorm = 0;
	private int currentDelayVirus = 0;
	private int currentDelayRootkit = 0;
	private int spawnRate = 25;
	private int spawnRateWorm = 30;
	private int spawnRateVirus = 35;
	private int spawnRateRootkit = 40;

	public void spawnEnemies() {
		if(waveNumber >= 10) {
			spawnRate = 10;
		}
		if(waveNumber > 0) {
			trojans = true;
		}
		if(waveNumber > 5) {
			worm = true;
		}
		if(waveNumber >15) {
			virus = false;
		}
		if(waveNumber > 25) {
			rootkit = false;
		}
		if(waveSpawning){
			if(trojans){
				if(this.trojansThisRound < this.trojansPerRound * this.waveNumber + this.trojansPerRound * (this.waveNumber - 1)) {
					if(currentDelay < spawnRate) {
						currentDelay++;
					}else{
						currentDelay = 0;

						System.out.println("[Wave] Trojan Spawned.");
						this.trojansThisRound++;
						screen.spawn(Enemy.enemyList[0].id);
					}
				}else {
					trojans = false;
				}
			}

			if(worm){
				if(this.wormsThisRound < this.wormsPerRound * this.waveNumber) {
					if(currentDelayWorm < spawnRateWorm) {
						currentDelayWorm++;
					}else{
						currentDelayWorm = 0;

						System.out.println("[Wave] Worm Spawned");
						this.wormsThisRound++;
						screen.spawn(Enemy.enemyList[1].id);
					}
				}else{
					worm = false;
				}

			}



			if(!trojans) {
				if(!worm) {
					if(!virus) {
						if(!rootkit) {
							waveSpawning = false;
						}
					}
				}
			}
		}


		/*if(this.enemiesThisRound < this.waveNumber * enemiesPerRound){
			if(currentDelay < spawnRate) {
				currentDelay++;
				System.out.println(currentDelay);
			}else{
				currentDelay = 0;
				
				System.out.println("[Wave] Enemy Spawned");
				
				this.enemiesThisRound++;
				this.screen.spawnEnemy();
			}
		}else{
			this.waveSpawning = false;
			System.out.println("Wave over");
		}*/


	}

}