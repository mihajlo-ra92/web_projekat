package beans.webshop;

public class Menager extends User {
	private SportObject sportObject;
	public Menager() {
		// TODO Auto-generated constructor stub
	}
	
	public SportObject getSportObject() {
		return sportObject;
	}
	public void setSportObject(SportObject sportObject) {
		this.sportObject = sportObject;
	}
	@Override
	public String toString() {
		return "Menager [sportObject=" + sportObject + ", getSportObject()=" + getSportObject() + ", toString()="
				+ super.toString() + ", getId()=" + getId() + ", getUsername()=" + getUsername() + ", getPassword()="
				+ getPassword() + ", getFirstName()=" + getFirstName() + ", getLastName()=" + getLastName()
				+ ", getGender()=" + getGender() + ", getBirthDate()=" + getBirthDate() + ", getRole()=" + getRole()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
}
