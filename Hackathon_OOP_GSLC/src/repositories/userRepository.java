package repositories;

import java.util.ArrayList;
import java.util.Scanner;

import connection.Connection;
import main.Main;
import models.Team;
import models.User;

public class userRepository extends Repository{
	static ArrayList<Team> teams  = teamRepository.teams;
	public static ArrayList<User> users = new ArrayList<>();
	static Scanner scan = Main.scan;
	
	public static void find() {
		if(!condition) {
			for (User user : users) {
				System.out.println(user.getNama());
			}
		}
	}
	
	public static void findOne() {
	    	
	}
	public static void insert(String[] userAttribute, Connection ci) {
		String name = userAttribute[0];
		String nim = userAttribute[1];
	    String team = userAttribute[2];
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
			System.out.println("Error: Team full.\nPress enter to continue...");
			scan.nextLine();
		}else {
			ci.writeFileUser(nim, name, teamID);									
		}
	}
}
