package main;

import java.util.Scanner;

import connection.Connection;
import repositories.teamRepository;
import repositories.userRepository;

public class Main {
	public static Scanner scan = new Scanner(System.in);
	int pick, pick2;
	Connection main_connection = Connection.get_instance();
	public void clearConsole() {
		for (int i = 0; i < 25; i++) {
			System.out.println();
		}
	}
	
	public void show() {
		Integer choices = 0;
		Boolean rightInput = false;
		do {
			System.out.print("Which table to show? 1. User, 2. Team.\n>> ");
			try {
				choices = scan.nextInt();
				scan.nextLine();
				switch (choices) {
				case 1:
					rightInput = true;
					userRepository.show("User");
					break;

				case 2:
					rightInput = true;
					userRepository.show("Team");
					break;
				}
				if(rightInput) {
					break;
				}
			} catch (Exception e) {
				System.out.println("Wrong Input");
				scan.nextLine();
			}
		} while (true);

	}
	
	public void insert() {
		do {
			pick2=-1;
			System.out.print("Which table to insert? 1. User, 2. Team.\n>> ");
			try {
				pick2 = scan.nextInt();
			} catch (Exception e) {
			}
			scan.nextLine();
			if(pick2==1) {
				String[] userAttribute = new String[3];
				do {
					System.out.print("add name: ");
					userAttribute[0] = scan.nextLine();			
				}while(userAttribute[0].isEmpty());
				do {
					System.out.print("add nim: ");
					userAttribute[1]=scan.nextLine();
				}while(!userAttribute[1].matches("\\d+"));
				do {
					System.out.print("add team: ");
					userAttribute[2] = scan.nextLine();					
				}while(userAttribute[2].isEmpty());
				userRepository.insert(userAttribute, main_connection);
			}else if(pick2==2) {
				String[] teamAttribute = new String[1];
				do {
					System.out.print("add name: ");
					teamAttribute[0] = scan.nextLine();			
				}while(teamAttribute[0].isEmpty());
				teamRepository.insert(teamAttribute, main_connection);
			}
		}while(pick2<1 && pick2>2);
		
	}
	
	public Main() {
		main_connection.readFileTeam(teamRepository.teams);
		main_connection.readFileUser(userRepository.users);
		do {
			clearConsole();
			pick=-1;
			System.out.println("1. Insert Data");
			System.out.println("2. Show Data");
			System.out.print("3. Exit\n>> ");
			try {
				pick= scan.nextInt();				
			} catch (Exception e) {
			}
			scan.nextLine();
			if(pick==1) {
				clearConsole();
				insert();
			}else if(pick==2) {
				clearConsole();
				show();
			}
		}while(pick!=3);
	}

	public static void main(String[] args) {
		new Main();
	}

}
