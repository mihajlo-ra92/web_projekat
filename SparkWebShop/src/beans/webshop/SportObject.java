package beans.webshop;

import java.util.ArrayList;

public class SportObject {
	private String id;
	private String name;
	private String objectType;//mozda bude enum
	private boolean isOpen;
	private Location location;
	//private String logo;
	private double avegareGrade;
	private String workHours;//mozda bude nesto drugo
	public SportObject() {
		// TODO Auto-generated constructor stub
	}
	
	public SportObject(String id, String name, String objectType,
			boolean isOpen, Location location, double averageGrade) {
		this.id = id;
		this.name = name;
		this.objectType = objectType;
		this.isOpen = isOpen;
		this.location = location;
		this.avegareGrade = averageGrade;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getObjectType() {
		return objectType;
	}
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	public boolean isOpen() {
		return isOpen;
	}
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public double getAvegareGrade() {
		return avegareGrade;
	}
	public void setAvegareGrade(double avegareGrade) {
		this.avegareGrade = avegareGrade;
	}
	public String getWorkHours() {
		return workHours;
	}
	public void setWorkHours(String workHours) {
		this.workHours = workHours;
	}
	@Override
	public String toString() {
		return "SportObject [id=" + id + ", name=" + name + ", objectType=" + objectType
				+ ", isOpen=" + isOpen + ", location=" + location + ", avegareGrade=" + avegareGrade + ", workHours="
				+ workHours + "]";
	}
	

}
