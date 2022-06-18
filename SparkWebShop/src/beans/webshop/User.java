package beans.webshop;


import java.time.LocalDate;

import enums.Gender;

public class User {
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private Gender gender;
	private LocalDate birthDate;
	//istorija treninga ako je TRENER
	//membershipFee ako je KUPAC
	//sportski objekat ako je MENADZER
	//poseceni objekat ako je KUPAC
	//broj sakupljenih bodova ako je KUPAC
	//tip kupca, naravno ako je KUPAC
	
	public User() {
		
	}

}
