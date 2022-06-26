package beans.webshop;

import enums.MembershipType;

public class Membership {
	private String id; //must be 10 chars
	private MembershipType membershipType;
	private String dateOfPayment;
	
	//datum i vreme vazenja
	//nz da li moze ovako
	private String startDate;
	private String endDate;
	
	private int price;
	private boolean isActive;
	private int numberOfWorkouts; //can be inf
	public Membership() {
		// TODO Auto-generated constructor stub
	}

}
