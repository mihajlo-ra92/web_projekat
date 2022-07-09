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
	private int numberOfWorkouts; //how much workouts are allowed per day, can be inf
	private String buyer;
	public Membership() {
		// TODO Auto-generated constructor stub
	}
	
	public Membership(String id, MembershipType membershipType, String dateOfPayment, String startDate, String endDate,
			int price, boolean isActive, int numberOfWorkouts, String buyer) {
		super();
		this.id = id;
		this.membershipType = membershipType;
		this.dateOfPayment = dateOfPayment;
		this.startDate = startDate;
		this.endDate = endDate;
		this.price = price;
		this.isActive = isActive;
		this.numberOfWorkouts = numberOfWorkouts;
		this.buyer = buyer;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public MembershipType getMembershipType() {
		return membershipType;
	}
	public void setMembershipType(MembershipType membershipType) {
		this.membershipType = membershipType;
	}
	public String getDateOfPayment() {
		return dateOfPayment;
	}
	public void setDateOfPayment(String dateOfPayment) {
		this.dateOfPayment = dateOfPayment;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public int getNumberOfWorkouts() {
		return numberOfWorkouts;
	}
	public void setNumberOfWorkouts(int numberOfWorkouts) {
		this.numberOfWorkouts = numberOfWorkouts;
	}
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	@Override
	public String toString() {
		return "Membership [id=" + id + ", membershipType=" + membershipType + ", dateOfPayment=" + dateOfPayment
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", price=" + price + ", isActive=" + isActive
				+ ", numberOfWorkouts=" + numberOfWorkouts + ", buyer=" + buyer + "]";
	}

}
