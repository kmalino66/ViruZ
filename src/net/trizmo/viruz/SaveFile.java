package net.trizmo.viruz;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SaveFile {
	
	public static void save(Hacker[][] hackerMap, User user, int waveNumber){
		FileWriter file;
		PrintWriter printer;
		
		
		
		try {
			file = new FileWriter("res/save/saveFile.txt");
			printer = new PrintWriter(file);

			printer.println(user.player.money);
			printer.println(user.player.health);
			printer.println(waveNumber);
			
			for(int x = 0; x < 15; x++){
				for(int y = 0; y < 5; y++){
					if(hackerMap[x][y] != null){
						printer.println(x);
						printer.println(y);
						printer.println(hackerMap[x][y].id);
						printer.println(hackerMap[x][y].health);
					}
				}
			}
			
			printer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
