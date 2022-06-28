package beans.webshop;

public class Menager extends User {
	private String sportObjectId;
	public Menager() {
		// TODO Auto-generated constructor stub
	}
	public String getSportObjectId() {
		return sportObjectId;
	}
	public void setSportObjectId(String sportObjectId) {
		this.sportObjectId = sportObjectId;
	}
	@Override
	public String toString() {
		return "Menager [sportObjectId=" + sportObjectId + "]";
	}
	
}
