package models;

public class User extends Model{
	private String nim;
	private Integer teamID;
	public User(String nama,String nim, Integer teamID) {
		super(nama);
		this.nim = nim;
		this.teamID = teamID;
	}
	
	public synchronized String getNim() {
		return nim;
	}

	public synchronized void setNim(String nim) {
		this.nim = nim;
	}

	public synchronized Integer getTeamID() {
		return teamID;
	}

	public synchronized void setTeamID(Integer teamID) {
		this.teamID = teamID;
	}
	


	@Override
	public void print() {
		
	}	
	
}
