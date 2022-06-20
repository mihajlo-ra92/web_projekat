package beans.webshop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Locations {
	private HashMap<String, Location> locations = new HashMap<String, Location>();
	private ArrayList<Location> locationsList = new ArrayList<Location>();
	public Locations() {
		this(".");
	}
	public Locations(String path) {
		BufferedReader in = null;
		try {
			File file = new File(path + "/locations.txt");
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			readLocations(in);
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
	
	private void readLocations(BufferedReader in) {
		String line, id = "", geoLenght = "", geoWidth = "", address = "";
		StringTokenizer st;
		try {
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					id = st.nextToken().trim();
					geoLenght = st.nextToken().trim();
					geoWidth = st.nextToken().trim();
					address = st.nextToken().trim();
				}
				Location location = new Location(id, Double.parseDouble(geoLenght), Double.parseDouble(geoWidth), address);
				locations.put(id, location);
				locationsList.add(location);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public Location ReadById(String id) {
		return locations.get(id);
	}

}
