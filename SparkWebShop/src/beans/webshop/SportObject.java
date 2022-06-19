package beans.webshop;

import java.util.ArrayList;

public class SportObject {
	private String id;
	private String name;
	private String objectType;//mozda bude enum
	private ArrayList<String> content;
	private boolean isOpen;
	private Location location;
	//slika logo objekta
	private double avegareGrade;
	private String workHours;//mozda bude nesto drugo
	public SportObject() {
		// TODO Auto-generated constructor stub
	}
	public SportObject(String id, String name, String objectType,
			boolean isOpen, Location location) {
		this.id = id;
		this.name = name;
		this.objectType = objectType;
		this.isOpen = isOpen;
		this.location = location;
	}

}
