package repositories;

import java.util.ArrayList;
import java.util.Scanner;

import connection.Connection;
import main.Main;
import models.Team;
import models.User;

public abstract class Repository {
	static Scanner scan = Main.scan;
	protected static boolean condition = false;
	static ArrayList<Object> list = new ArrayList<>();
	
	private static void query(String context) {
		Boolean valid;
		String variable, operator, value, join = null, table = null;
		do {	
			valid = true;
			System.out.println("format : [variabel];[operator];[determine value];[Join Table];[Table Name]");
			System.out.println("example : name;=;kevin;true;Team");
			System.out.println("operator list:");
			System.out.println("1. '=' 	: equal");
			System.out.println("2. '$' 	: contain");
			System.out.println("3. '1' 	: only one");
			System.out.print("add condition, separate by semicolon. \n>> ");
			String query = scan.nextLine();
			String temp[] = query.split(";");
			variable = temp[0];
			operator = temp[1];
			value = temp[2];
			if (temp.length > 3) {
			    join = temp[3];
			}

			if(temp[3].equalsIgnoreCase("true")) {
				if (temp.length > 4) {
					table = temp[4];
				}
			}
			System.out.println(table);
			System.out.println("a");
			if(table == null) {
				
				System.out.println("null");
			}
			if (join.equalsIgnoreCase("False") && table != null) {
				System.out.println("Invalid input: If join is 'false', the table should be null.");
				valid = false;
			}
			if (variable == null && query == null && join == null && table == null) {
		        System.out.println("Error: All filter conditions are null.");
		        valid = false;
		    }
			if ((variable == null && query != null) || (variable != null && query == null)) {
		        System.out.println("Error: Filter condition mismatch - variable and query should either both be null or both not be null.");
		        valid = false;
		    }
			if ((table == null && join.equalsIgnoreCase("true")) || (table != null && join == null)) {
		        System.out.println("Error: Join condition mismatch - tableJoinName and join should either both be null or both not be null.");
		        valid = false;
		    }
			if (variable != null && variable.isEmpty()) {
				System.out.println("Error: Variable cannot be an empty string.");
			    valid = false;
			}
			if (query != null && query.length() == 0) {
		        System.out.println("Error: Query array cannot be an empty array.");
		        valid = false;
		    }
			if (table != null && table.isEmpty()) {
		        System.out.println("Error: TableJoinName cannot be an empty string.");
		        valid = false;
		    }
		}while(!valid);
		String[] queue = {operator, value};
		Boolean gabung = false;
		if(join.equalsIgnoreCase("True")) {
			gabung = true;
		}
		else if(join.equalsIgnoreCase("False")) {
			gabung = false;
		}
		if(context.equalsIgnoreCase("User")) {			
			list = (ArrayList<Object>) userRepository.find(variable, queue, gabung, table, null);
		} else {
			list = (ArrayList<Object>) teamRepository.find(variable, queue, gabung, table, null);
		}
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
				query(context);
				if (!list.isEmpty()) {
			        for (Object result : list) {
			            System.out.println(result);
			        }
			    } else {
			        System.out.println("No matching results found.");
			    }
				break;

			case 2:
				condition = false;
				
				if(context == "User") {
					list = (ArrayList<Object>) userRepository.find(null,null,null,null,null);					
				}else {
					list = (ArrayList<Object>) teamRepository.find(null, null, null, null, null);
				}
				if(list.size() == 0) {
					System.out.println("User is empty please add more user");
				}else {
					for (Object object : list) {
						if(object instanceof User) {
							User curr = (User) object;
							System.out.println(curr.getNama() + ' ' + curr.getNim() + ' ' + curr.getTeamID());
						}else {
							Team curr = (Team) object;
							System.out.println(curr.getId() + " " + curr.getNama());
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
