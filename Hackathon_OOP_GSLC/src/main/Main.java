package main;

import java.util.Scanner;

public class Main {
	Scanner scan = new Scanner(System.in);
	int pick;
	public Main() {
		do {
			pick=-1;
			System.out.println("1. Insert Data");
			System.out.println("2. Show Data");
			System.out.println("3. Exit");
			try {
				pick= scan.nextInt();				
			} catch (Exception e) {
			}
			scan.nextLine();
		}while(pick==3);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

}
