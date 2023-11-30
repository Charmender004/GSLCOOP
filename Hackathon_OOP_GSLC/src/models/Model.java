package models;

public abstract class Model {
	private String nama;
	public Model(String nama) {
		this.nama = nama;
	}
	
	public synchronized String getNama() {
		return nama;
	}

	public synchronized void setNama(String nama) {
		this.nama = nama;
	}

	public abstract void print();
}
