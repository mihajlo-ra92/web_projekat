package beans.webshop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.StringTokenizer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class SportObjects {
	private HashMap<String, SportObject> sportObjects = new HashMap<String, SportObject>();
	private ArrayList<SportObject> sportObjectsList = new ArrayList<SportObject>();
	private String path;
	private static Gson g = new Gson();
	private static final java.lang.reflect.Type SPORTOBJECTS_TYPE = new TypeToken<ArrayList<SportObject>>() {
	}.getType();
	public SportObjects(Locations locations) {
		this(".", locations);
	}
	public SportObjects(String path, Locations locations) {
		this.path = path;
		BufferedReader in = null;
		try {
			File file = new File(path + "/sportObjects.json");
			System.out.println(file.getCanonicalPath());
			JsonReader reader = new JsonReader(new FileReader(file));
			sportObjectsList = g.fromJson(reader, SPORTOBJECTS_TYPE);
			System.out.println("list test");
			System.out.println(sportObjectsList.toString());
			System.out.println("list test");
			
			for (SportObject soIt : sportObjectsList) {
				sportObjects.put(soIt.getId(), soIt);
			}
			System.out.println("hashmap test");
			System.out.println(sportObjects.toString());
			System.out.println("hashmap test");
			
			in = new BufferedReader(new FileReader(file));
			readSportObjects(in, locations);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if ( in != null ) {
				try {
					in.close();
				}
				catch (Exception e) { }
			}
		}
	}
	
	private void readSportObjects(BufferedReader in, Locations locations) {
		String line, id = "", name = "", objectType = "",
				isOpen = "", locationId = "", averageGrade = "";
		Location loadedLocation = new Location();
		StringTokenizer st;
		try {
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					id = st.nextToken().trim();
					name = st.nextToken().trim();
					objectType = st.nextToken().trim();
					isOpen = st.nextToken().trim();
					locationId = st.nextToken().trim();
					loadedLocation = locations.ReadById(locationId);
					averageGrade = st.nextToken().trim();
				}
				SportObject sportObject = new SportObject(id, name, objectType,
						Boolean.parseBoolean(isOpen), loadedLocation, Double.parseDouble(averageGrade));
				sportObjects.put(id, sportObject);
				sportObjectsList.add(sportObject);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void addSportObjectsRequest(String req) throws FileNotFoundException {
		SportObject so = g.fromJson(req, SportObject.class);
		String id = Integer.toString(sportObjectsList.size()+1);
		so.setId(id);
		System.out.println("JSON PRINT::");
		System.out.println(so.toString());
		addSportObject(so);
	}
	public void addSportObject(SportObject sportObject) throws FileNotFoundException {
		System.out.println("sportObjects::");
		System.out.println(g.toJson(sportObjects.values()));
		sportObjects.put(sportObject.getId(), sportObject);
		sportObjectsList.add(sportObject);
		toJSON(path + "/sportObjects.json");
	}

	private void toJSON(String filename) throws FileNotFoundException {
		PrintWriter out = new PrintWriter(filename);
		System.out.println(filename);
		out.printf(g.toJson(sportObjects.values()));
		out.close();
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

	/** Vraca listu proizvoda. */
	public ArrayList<SportObject> getSportObjectList() {
		return sportObjectsList;
	}
}
