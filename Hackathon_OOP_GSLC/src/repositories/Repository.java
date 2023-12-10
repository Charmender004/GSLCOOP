package repositories;

import java.util.Scanner;

import main.Main;

public abstract class Repository {
	static Scanner scan = Main.scan;
	protected static boolean condition = false;
	
	private static void query() {
		
	}
	
	public static void show() {
		condition = false;
		Integer choices = 0;
    	System.out.print("Want to filter by condition? 1. Yes, 2. No.\n>> ");
    	do {
    		try {
    			choices = scan.nextInt();
    			switch (choices) {
				case 1:
					condition = true;
					break;

				case 2:
					condition = false;
					break;
				}
    		} catch (Exception e) {
    		}			
    		scan.nextLine();
		} while (true);
    }
	
    public static void find() {
    	
    }
    
	public static void findOne() {
	    	
	}
	
	public static void insert() {
		
	}
}
