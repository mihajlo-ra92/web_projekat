package beans.webshop;

import java.time.LocalDate;

import enums.MembershipType;

public class Membership {
	private String id; //must be 10 chars
	private MembershipType membershipType;
	private LocalDate dateOfPayment;
	
	//datum i vreme vazenja
	//nz da li moze ovako
	private LocalDate startDate;
	private LocalDate endDate;
	
	private int price;
	private Buyer buyer;
	private boolean isActive;
	private int numberOfWorkouts; //can be inf
	public Membership() {
		// TODO Auto-generated constructor stub
	}

}
