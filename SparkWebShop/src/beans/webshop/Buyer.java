package beans.webshop;

import java.util.ArrayList;

public class Buyer extends User {
	//private Membership membership;
	private ArrayList<String> visitedSportObjectIds;
	private double points;
	private BuyerType buyerType;
	
	public ArrayList<String> getVisitedSportObjectIds() {
		return visitedSportObjectIds;
	}

	public void setVisitedSportObjectIds(ArrayList<String> visitedSportObjectIds) {
		this.visitedSportObjectIds = visitedSportObjectIds;
	}

	public double getPoints() {
		return points;
	}

	public void setPoints(double points) {
		this.points = points;
	}

	public BuyerType getBuyerType() {
		return buyerType;
	}

	public void setBuyerType(BuyerType buyerType) {
		this.buyerType = buyerType;
	}

	public Buyer() {
		// TODO Auto-generated constructor stub
	}

}
