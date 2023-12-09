package models;

public class Team extends Model{
	private Integer id;
	public Team(Integer id, String nama) {
		super(nama);
		this.id =id;
	}

	public synchronized Integer getId() {
		return id;
	}

	public synchronized void setId(Integer id) {
		this.id = id;
	}

	@Override
	public void print() {
		
	}	
}
