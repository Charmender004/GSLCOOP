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

public class teamRepository extends Repository{
	
	public static ArrayList<Team> teams = new ArrayList<>();
	public static ArrayList<User> users = userRepository.users;
	static Scanner scan = Main.scan;
	public static Object find(String variable, String[]query, Boolean join, String tableJoinName, Connection con) {
		if(!condition) {
			return teams;
		}
		ArrayList<String> contain = new ArrayList<String>();
		BufferedReader baca;
		try {
			baca = new BufferedReader(new FileReader("teams.csv"));
			String read;
			baca.readLine();
			String benchmark = null;
			while((read = baca.readLine())!=null) {
				String temp[] = read.split(",");
				String id = temp[0].trim();
				String nama = temp[1].trim();
				switch (variable) {
				case "id":
					benchmark = id;
					break;
				case "name":
					benchmark = nama;
					break;
				}
				switch (query[0]) {
				case "$":
					if(benchmark.toLowerCase().contains(query[1].toLowerCase())) {
						if(join == false) {							
							contain.add(id + ";" + nama);
						}
						else if(join == true) {
							if(variable.equalsIgnoreCase("id")) {		
								for (User user : users) {
									if (user.getTeamID().equals(Integer.parseInt(id))) {
										contain.add(id + ";" + nama + ";" + user.getNama() + ";" + user.getNim());
									}
								}
							}
							else {
								int teamIndex = -1;
					            for (int i = 0; i < teams.size(); i++) {
					                if (teams.get(i).getNama().equalsIgnoreCase(nama)) {
					                    teamIndex = i;
					                    for (User user : users) {
											if (user.getTeamID().equals(teamIndex)) {
												contain.add(id + ";" + nama + ";" + user.getNama() + ";" + user.getNim());
											}
										}
					                }
					            }
							}
						}
					}
					break;

				case "=":
					if(benchmark.toLowerCase().equals(query[1].toLowerCase())) {
						if(join == false) {							
							contain.add(id + ";" + nama);
						}
						else if(join == true) {
							if(variable.equalsIgnoreCase("id")) {		
								for (User user : users) {
									if (user.getTeamID().equals(Integer.parseInt(id))) {
										contain.add(id + ";" + nama + ";" + user.getNama() + ";" + user.getNim());
									}
								}
							}
							else {
								int teamIndex = -1;
					            for (int i = 0; i < teams.size(); i++) {
					                if (teams.get(i).getNama().equalsIgnoreCase(nama)) {
					                    teamIndex = i;
					                    for (User user : users) {
											if (user.getTeamID().equals(teamIndex)) {
												contain.add(id + ";" + nama + ";" + user.getNama() + ";" + user.getNim());
											}
										}
					                }
					            }
							}
						}
					}
					break;
				
				}
			}
			baca.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contain;
	}
	public static void insert(String []teamAtttribute, Connection ci) {
		String name = teamAtttribute[0];
		for(int i=0;i<teams.size();i++) {
			if(teams.get(i).getNama().equals(name)) {
				System.out.println("Team "+ name + " has already created!");
				return;
			}
		}
		ci.writeFileTeam(teams.size()+1, name);
	}
}