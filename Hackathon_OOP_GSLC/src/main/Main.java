package main;

import java.util.ArrayList;
import java.util.Scanner;

import connection.Connection;
import models.Team;
import models.User;

public class Main {
	public Connection ci =  Connection.get_instance();
	Scanner scan = new Scanner(System.in);
	int pick, pick2;
	ArrayList<Team> teams = new ArrayList<>();
	ArrayList<User> users = new ArrayList<>();
	public void show() {
		
	}
	public static boolean isNumeric(String str) {
        return str.matches("\\d+");
    }
	
	public void insertTeam() {
		String name;
		do {
			System.out.print("add name: ");
			name = scan.nextLine();			
		}while(name.isEmpty());
		ci.readFileTeam(teams);
		for(int i=0;i<teams.size();i++) {
			if(teams.get(i).getNama().equals(name)) {
				System.out.println("Team "+ name + " has already created!");
				return;
			}
		}
		ci.writeFileTeam(teams.size()+1, name);
	}
	
	public void insertUser() {
		String name,team, nim;
		do {
			System.out.print("add name: ");
			name = scan.nextLine();			
		}while(name.isEmpty());
		System.out.print("add nim: ");
		do {
			nim=scan.nextLine();
		}while(!isNumeric(nim));
		do {
			System.out.print("add team: ");
				team = scan.nextLine();					
		}while(team.isEmpty());
		Integer teamID=-1;
		ci.readFileTeam(teams);
		for(int i=0;i<teams.size();i++) {
			if(team.equals(teams.get(i).getNama())) {
				teamID=teams.get(i).getId();
			}
		}
		if(teamID==-1) {
			ci.writeFileTeam(teams.size()+1, team);
			ci.writeFileUser(nim, name, teams.size()+1);
			return;
		}
		int jumlah=0;
		for(int i=0;i<users.size();i++) {
			if(teamID==users.get(i).getTeamID()) {
				jumlah++;
			}
		}
		if(jumlah==3) {
			System.out.println("Error: Team full.");
		}else {
			ci.writeFileUser(nim, name, teamID);									
		}
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
				insertUser();
			}else if(pick2==2) {
				insertTeam();
			}
		}while(pick2<1 && pick2>2);
		
	}
	
	public Main() {
		do {
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
				insert();
			}else if(pick==2) {
				show();
			}
		}while(pick!=3);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

}
