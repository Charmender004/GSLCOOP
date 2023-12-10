package connection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import models.Team;
import models.User;

public class Connection {
	private static Connection connection_instance;
	
	private Connection() {
		
	}
	
	public static Connection get_instance() {
		if(connection_instance == null) {
			connection_instance = new Connection();
		}
		return connection_instance;
	}
	
	public void writeFileUser(String nim,String nama, Integer team) {
		try {
			BufferedWriter tulis = new BufferedWriter(new FileWriter("user.csv",true));
			tulis.write(String.format("%s,%s,%d\n", nim,nama,team));
			tulis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeFileTeam(Integer team,String nama) {
		try {
			BufferedWriter tulis = new BufferedWriter(new FileWriter("teams.csv",true));
			tulis.write(String.format("%d,%s\n", team,nama));
			tulis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void readFileUser(ArrayList<User> user) {
		BufferedReader baca;
		try {
			baca = new BufferedReader(new FileReader("user.csv"));
			String read;
			baca.readLine();
			while((read = baca.readLine())!=null) {
				String temp[] = read.split(",");
				String nama = temp[1];
				String nim = temp[0];
				Integer teamID = Integer.parseInt(temp[2]);
				user.add(new User(nama, nim, teamID));
			}
			baca.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void readFileTeam(ArrayList<Team> team) {
		BufferedReader baca;
		try {
			baca = new BufferedReader(new FileReader("teams.csv"));
			String read;
			baca.readLine();
			while((read = baca.readLine())!=null) {
				String temp[] = read.split(",");
				Integer id = Integer.parseInt(temp[0]);
				String nama = temp[1];
				team.add(new Team(id, nama));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
