package repositories;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
	
	
	public static Object find(String variable, String[]query,Boolean join, String tableJoinName, Connection con) {
		if(!condition) {
			return users;
		}
		ArrayList<String> contain = new ArrayList<String>();
		BufferedReader baca;
		try {
			baca = new BufferedReader(new FileReader("user.csv"));
			String read;
			baca.readLine();
			String benchmark = null;
			while((read = baca.readLine())!=null) {
				String temp[] = read.split(",");
				String nama = temp[1].trim();
				String nim = temp[0].trim();
				String teamID = temp[2].trim();
				switch (variable) {
				case "name":
					benchmark = nama;
					break;
				case "nim":
					benchmark = nim;
					break;
				case "teamId":
					benchmark = teamID;
					break;
				}
				switch (query[0]) {
				case "$":
					if(benchmark.toLowerCase().contains(query[1].toLowerCase())) {
						if(join == false) {							
							contain.add(nama + ";" + nim + ";" + teamID);
						}
						else if(join == true) {
							Team teamIndex = teams.get(Integer.parseInt(teamID));
							contain.add(nama + ";" + nim + ";" + teamID + ";" + teamIndex.getNama());
						}
					}
					break;

				case "=":
					if(benchmark.toLowerCase().equals(query[1].toLowerCase())) {
						if(join == false) {							
							contain.add(nama + ";" + nim + ";" + teamID);
						}
						else if(join == true) {
							Team teamIndex = teams.get(Integer.parseInt(teamID));
							contain.add(nama + ";" + nim + ";" + teamID + ";" + teamIndex.getNama());
						}
					}
					break;
				
				case "1":
					return users;
				
				}
			}
			baca.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contain;
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
