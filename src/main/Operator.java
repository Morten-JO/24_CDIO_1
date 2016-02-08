package main;

public class Operator {
	private static int IDCounter = 0;
	private int ID;
	private String name;
	private String cpr;
	private String ini; //what is this for?
	private String password;

	public Operator(String name, String cpr){
		this.name = name;
		this.cpr = cpr;
		password = "a";
		ID = ++IDCounter;
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCpr() {
		return cpr;
	}
	public void setCpr(String cpr) {
		this.cpr = cpr;
	}
	public String getIni() {
		return ini;
	}
	public void setIni(String ini) {
		this.ini = ini;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
