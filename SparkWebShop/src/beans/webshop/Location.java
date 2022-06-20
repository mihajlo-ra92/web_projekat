package beans.webshop;

public class Location {
	private String id;
	private double geoLenght;
	private double geoWidth;
	private String address;
	
	public Location() {	
		
	}
	public Location(String id, double geoLenght,
			double geoWidth, String address) {
		this.id = id;
		this.geoLenght = geoLenght;
		this.geoWidth = geoWidth;
		this.address = address;
	}

}
