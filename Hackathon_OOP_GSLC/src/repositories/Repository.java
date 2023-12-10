package repositories;

import java.util.ArrayList;
import java.util.Scanner;


import com.sun.tools.javac.code.Attribute.Array;

import connection.Connection;
import main.Main;
import models.User;

public abstract class Repository {
	static Scanner scan = Main.scan;
	protected static boolean condition = false;
	
	private static void query() {
		System.out.println("format : [variabel];[operator];[determine value];[Join Table];[Table Name]");
		System.out.println("example : name;=;kevin;true;Team");
		System.out.println("operator list:");
		System.out.println("1. '=' 	: equal");
		System.out.println("2. '$' 	: contain");
		System.out.println("3. '1' 	: only one");
		System.out.print("add condition, separate by semicolon. \n>> ");
		String query = scan.nextLine();
	}
	
	public static void show(String context) {
		condition = false;
		Integer choices = 0;
    	System.out.print("Want to filter by condition? 1. Yes, 2. No.\n>> ");
		try {
			choices = scan.nextInt();
			scan.nextLine();
			switch (choices) {
			case 1:
				condition = true;
				query();
				break;

			case 2:
				condition = false;
				ArrayList<Object> list;
				if(context == "User") {
					list = (ArrayList<Object>) userRepository.find(null,null,null,null);					
				}else {
					list = (ArrayList<Object>) teamRepository.find();
				}
				if(list.size() == 0) {
					System.out.println("User is empty please add more user");
				}else {
					for (Object object : list) {
						if(object instanceof User) {
							User curr = (User) object;
							System.out.println(curr.getNama() + ' ' + curr.getNim() + ' ' + curr.getTeamID());
						}else {
							
						}
					}
				}
				System.out.println("Press enter to continue...");
				scan.nextLine();
				break;
			}
		} catch (Exception e) {
			scan.nextLine();
			System.out.println("Wrong input!!\nPress enter to continue....");
			scan.nextLine();
		}			
    }
	
	public static void insert(String[] Attribute, Connection ci) {
		
	}
}
