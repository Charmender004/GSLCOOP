package models;

public class User extends Model{
	private Integer nim,teamID;
	public User(String nama,Integer nim, Integer teamID) {
		super(nama);
		this.nim = nim;
		this.teamID = teamID;
	}

	public synchronized Integer getNim() {
		return nim;
	}

	public synchronized void setNim(Integer nim) {
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
