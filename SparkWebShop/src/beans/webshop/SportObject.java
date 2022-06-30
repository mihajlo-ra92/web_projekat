package beans.webshop;

import java.util.ArrayList;

public class SportObject {
	private String id;
	private String name;
	private String objectType;//mozda bude enum
	private ArrayList<String> content;
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
	public void deleteWorkoutInContent(Workout wo) {
		System.out.println("Brisemo ga!");
		ArrayList<String> woList = new ArrayList<String>();
		for(String woIt : content) {
			if(woIt.equals(wo.getName()) == false) {
				woList.add(woIt);
			}
		}
		setContent(woList);
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
	public ArrayList<String> getContent() {
		return content;
	}
	
	public void setContent(ArrayList<String> content) {
		this.content = content;
	}
	public void addContent(Workout wo) {
		if(this.content != null) {
		this.content.add(wo.getId());
		}else {
			ArrayList<String> proba = new ArrayList<String>();
			proba.add(wo.getId());
			this.content = proba;
		}
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
		return "SportObject [id=" + id + ", name=" + name + ", objectType=" + objectType + ", content=" + content
				+ ", isOpen=" + isOpen + ", location=" + location + ", avegareGrade=" + avegareGrade + ", workHours="
				+ workHours + "]";
	}
	

}
