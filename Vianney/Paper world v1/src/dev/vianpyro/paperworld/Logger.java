package dev.vianpyro.paperworld;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
	
	private static String date = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss-ms").format(new Date());;
	private static String time = new SimpleDateFormat("hh:mm:ss").format(new Date());;
	private static File logFile, gameMainFolder;
	
	public static void initialisation() {
		System.out.println(date);
		logFile = new File("C://Program Files (x86)/Paper game/" + date + ".log");
		gameMainFolder = new File("C://Program Files (x86)/Paper game/");
		
		if(!gameMainFolder.exists()) {
			gameMainFolder.mkdir();
		}
		
		if(!logFile.exists()) {
			try {
				logFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
	}

	public static void log(String info) {
		System.out.println("Logs class called");
		
		try {
			FileWriter writer = new FileWriter(logFile);
			BufferedWriter bw = new BufferedWriter(writer);
			bw.write("[" + time + "] System info : " + info + "\n");
			bw.close();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
