package de.joshizockt.cloud.api;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
	
	public static void log(String arg) {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		
		
		System.out.println("[" + dtf.format(now) + "] " + CloudColor.GREEN + arg + CloudColor.RESET);
	}
	
	public static void err(String err) {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		
		
		System.out.println("[" + dtf.format(now) + "] " + CloudColor.RED_BOLD + "[ERROR] " + CloudColor.RESET + CloudColor.RED + err + CloudColor.RESET);
	}
	
	public static void warn(String warn) {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		
		
		System.out.println("[" + dtf.format(now) + "] " + CloudColor.YELLOW_BOLD + "[WARNING] " + CloudColor.RESET + CloudColor.YELLOW + warn + CloudColor.RESET);
	}

}
