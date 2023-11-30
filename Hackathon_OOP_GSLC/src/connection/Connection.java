package connection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
	
	public void writeFileUser(Integer nim,String nama, Integer team) {
		try {
			BufferedWriter tulis = new BufferedWriter(new FileWriter("user.csv",true));
			tulis.write(String.format("%d,%s,%d\n", nim,nama,team));
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
	
	public void readFileUser() {
		BufferedReader baca;
		try {
			baca = new BufferedReader(new FileReader("user.csv"));
			String read;
			baca.readLine();
			while((read = baca.readLine())!=null) {
				String temp[] = read.split(",");
				
			}
			baca.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void readFileTeam() {
		
	}

}
