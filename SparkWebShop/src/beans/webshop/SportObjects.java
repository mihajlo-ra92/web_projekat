package beans.webshop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.StringTokenizer;

public class SportObjects {
	private HashMap<String, SportObject> sportObjects = new HashMap<String, SportObject>();
	private ArrayList<SportObject> sportObjectsList = new ArrayList<SportObject>();
	public SportObjects() {
		this(".");
	}
	public SportObjects(String path) {
		BufferedReader in = null;
		try {
			File file = new File(path + "/sportObjects.txt");
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			readSportObjects(in);
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
	
	private void readSportObjects(BufferedReader in) {
		String line, id = "", name = "", type = "", isOpen = "";
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
					type = st.nextToken().trim();
					isOpen = st.nextToken().trim();
				}
				SportObject sportObject = new SportObject(id, name, type, Boolean.parseBoolean(isOpen));
				sportObjects.put(id, sportObject);
				sportObjectsList.add(sportObject);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
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