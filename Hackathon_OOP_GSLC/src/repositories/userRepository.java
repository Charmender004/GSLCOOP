package repositories;

import java.util.ArrayList;
import java.util.Scanner;

import main.Main;
import models.Team;
import models.User;

public class userRepository extends Repository{
	static ArrayList<Team> teams  = teamRepository.teams;
	public static ArrayList<User> users = new ArrayList<>();
	static Scanner scan = Main.scan;
	 public static void find() {
	    for (User user : users) {
			System.out.println(user.getNama());
		}
	    scan.nextLine();
    }
	public static void findOne() {
	    	
	}
	public static void insert() {
		String name,team, nim;
		do {
			System.out.print("add name: ");
			name = scan.nextLine();			
		}while(name.isEmpty());
		do {
			System.out.print("add nim: ");
			nim=scan.nextLine();
		}while(!nim.matches("\\d+"));
		do {
			System.out.print("add team: ");
			team = scan.nextLine();					
		}while(team.isEmpty());
		Integer teamID=-1;
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
}
