package beans.webshop;


import java.time.LocalDate;

import enums.Gender;

public class User {
	private String id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private Gender gender;
	private String birthDate; //zalio se kada je bio LocalDate
	//istorija treninga ako je TRENER
	//membershipFee ako je KUPAC
	//sportski objekat ako je MENADZER
	//poseceni objekat ako je KUPAC
	//broj sakupljenih bodova ako je KUPAC
	//tip kupca, naravno ako je KUPAC
	
	public User() {
		
	}
	public User(String id, String username,
			String password, String firstName, String lastName) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", gender=" + gender + ", birthDate=" + birthDate + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

}
