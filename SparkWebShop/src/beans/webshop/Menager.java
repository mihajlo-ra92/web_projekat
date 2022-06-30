package beans.webshop;

public class Menager extends User {
	private String sportObject;
	public Menager() {
		// TODO Auto-generated constructor stub
	}
	public String getSportObject() {
		return sportObject;
	}
	public void setSportObject(String sportObject) {
		this.sportObject = sportObject;
	}
	@Override
	public String toString() {
		return "Menager [sportObject=" + sportObject + "]";
	}
	
}
