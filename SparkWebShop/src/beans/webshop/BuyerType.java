package beans.webshop;

public class BuyerType {
	private String TypeName;
	private double dicounut;
	private double requestedNumOfPoints;
	public BuyerType() {
		// TODO Auto-generated constructor stub
	}
	public String getTypeName() {
		return TypeName;
	}
	public void setTypeName(String typeName) {
		TypeName = typeName;
	}
	@Override
	public String toString() {
		return "BuyerType [TypeName=" + TypeName + ", dicounut=" + dicounut + ", requestedNumOfPoints="
				+ requestedNumOfPoints + "]";
	}
	public double getDicounut() {
		return dicounut;
	}
	public void setDicounut(double dicounut) {
		this.dicounut = dicounut;
	}
	public double getRequestedNumOfPoints() {
		return requestedNumOfPoints;
	}
	public void setRequestedNumOfPoints(double requestedNumOfPoints) {
		this.requestedNumOfPoints = requestedNumOfPoints;
	}
	public BuyerType(String typeName, double dicounut, double requestedNumOfPoints) {
		super();
		TypeName = typeName;
		this.dicounut = dicounut;
		this.requestedNumOfPoints = requestedNumOfPoints;
	}

}
