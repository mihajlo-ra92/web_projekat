package beans.webshop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class SportObjectDAO {
	private HashMap<String, SportObject> sportObjects = new HashMap<String, SportObject>();
	private String path;
	private static Gson g = new Gson();
	private static final java.lang.reflect.Type SPORTOBJECTS_TYPE = new TypeToken<ArrayList<SportObject>>() {
	}.getType();
	
	public SportObjectDAO() {
		this(".");
	}
	public SportObjectDAO(String path) {
		this.path = path;
		
		try {
			File file = new File(path + "/resources/JSON/sportObjects.json");
			System.out.println(file.getCanonicalPath());
			JsonReader reader = new JsonReader(new FileReader(file));
			ArrayList<SportObject> sportObjectsList = g.fromJson(reader, SPORTOBJECTS_TYPE);
			
			
			//hashmap from arraylist
			//treba da bude posebna f-ja
			if (sportObjectsList != null) {
				for (SportObject soIt : sportObjectsList) {
					sportObjects.put(soIt.getName(), soIt);
				}
				updateIsOpen();
			}
			
//			System.out.println("sport object hashmap test");
//			System.out.println(sportObjects.toString());
//			System.out.println("sport object hashmap test");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String getNewId() {
		int largest = -1;
		for (SportObject objectIt: sportObjects.values()) {
			if (Integer.parseInt(objectIt.getId()) > largest) {
				largest = Integer.parseInt(objectIt.getId());
			}
		}
		return Integer.toString(largest + 1);
	}
	
	public Workout setContentToSportObject(String SOname,Workout workout) throws FileNotFoundException {
		workout.setSportObject(SOname);
		SportObject Sport = sportObjects.get(SOname);
	//	Sport.addContent(workout);
		toJSON(path + "/resources/JSON/sportObjects.json");
		return workout;
	}
	public Boolean addSportObjectsRequest(String req) throws FileNotFoundException {
		SportObject so = g.fromJson(req, SportObject.class);
		String id = getNewId();
		so.setId(id);
		
//		System.out.println("JSON PRINT add request sport object:");
//		System.out.println(so.toString());
		
		if (so.getName().equals("")) {
			return false;
		}
		for (SportObject objectIt : sportObjects.values()) {
			if (objectNamesAreSame(objectIt, so)) {
				return false;
			}
		}
		addSportObject(so);
		return true;
	}
	private Boolean objectNamesAreSame(SportObject so1, SportObject so2) {
		return (so1.getName().equals(so2.getName()));
	}
	public void addSportObject(SportObject sportObject) throws FileNotFoundException {
		sportObjects.put(sportObject.getName(), sportObject);
		toJSON(path + "/resources/JSON/sportObjects.json");
	}

	private void toJSON(String filename) throws FileNotFoundException {
		PrintWriter out = new PrintWriter(filename);
		//System.out.println(filename);
		out.printf(g.toJson(sportObjects.values()));
		out.close();
	}
	public void updateIsOpen() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		String timeStamp = dateFormat.format(Calendar.getInstance().getTime());
		for (SportObject objectIt : sportObjects.values()) {
			String [] hours = objectIt.getWorkHours().split("-");
			System.out.println(objectIt.getName() + " start: " + hours[0] + ", end: " + hours[1]);
			if (dateFormat.parse(timeStamp).after(dateFormat.parse(hours[0])) &&
					dateFormat.parse(hours[1]).after(dateFormat.parse(timeStamp))) {
				objectIt.setOpen(true);
			}
			else {
				objectIt.setOpen(false);
			}
		}
//		String strTime = "20:15:40";
//        DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
//        Date d = (Date) dateFormat.parse(strTime);
//        System.out.println("Resultant Date and Time = " + d);
	}
	
	public Collection<SportObject> getObjectsByNames(ArrayList<String> names){
		ArrayList<SportObject> retList = new ArrayList<SportObject>();
		for (String nameIt : names) {
			retList.add(sportObjects.get(nameIt));
		}
		return retList;
	}
	
	/** Vraca kolekciju proizvoda. */
	public Collection<SportObject> values() {
		return sportObjects.values();
	}

	/** Vraca kolekciju proizvoda. */
	public Collection<SportObject> getValues() {
		return sportObjects.values();
	}

	/** Vraca proizvod na osnovu njegovog id-a. */
	public SportObject getSportObject(String id) {
		return sportObjects.get(id);
	}
	
	public SportObject getSportObjectByName(String name) {
		for (SportObject sportIt : sportObjects.values()) {
			if (sportIt.getName().equals(name)) {
				return sportIt;
			}
		}
		return null;
	}
	public ArrayList<SportObject> getGyms() {
		ArrayList<SportObject> retVal = new ArrayList<SportObject>();
		for(SportObject soIt : sportObjects.values()) {
			if(soIt.getObjectType().equals("gym")) {
				retVal.add(soIt);
			}
		}
		return retVal;
	}
	
	public ArrayList<SportObject> getPools() {
		ArrayList<SportObject> retVal = new ArrayList<SportObject>();
		for(SportObject soIt : sportObjects.values()) {
			if(soIt.getObjectType().equals("pool")) {
				retVal.add(soIt);
			}
		}
		return retVal;
	}
	
	public ArrayList<SportObject> getDanceStudios() {
		ArrayList<SportObject> retVal = new ArrayList<SportObject>();
		for(SportObject soIt : sportObjects.values()) {
			if(soIt.getObjectType().equals("dance studio")) {
				retVal.add(soIt);
			}
		}
		return retVal;
	}
	
//	public void deleteContentofSportObject(String SOname,Workout workout) {
//		//System.out.println("ovaj treba da se brise: " + wo.toString());
//		for(String woIt : sportObjects.get(SOname).getContent()) {
//			//System.out.println("da li je ovaj isti?  " + woIt.toString());
//			if(woIt.equals(workout.getId())) {
//				sportObjects.get(SOname).deleteWorkoutInContent(workout);
//			}
//		}
//	}
}
