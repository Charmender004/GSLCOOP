package repositories;

import java.util.ArrayList;
import java.util.Scanner;

import connection.Connection;
import main.Main;
import models.Team;

public class teamRepository extends Repository{
	
	public static ArrayList<Team> teams = new ArrayList<>();
	static Scanner scan = Main.scan;
	public static void find() {
	    	
    }
	public static void findOne() {
	    	
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