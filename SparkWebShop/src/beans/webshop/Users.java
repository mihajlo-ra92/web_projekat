package beans.webshop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Users {
	private HashMap<String, User> users = new HashMap<String, User>();
	private ArrayList<User> usersList = new ArrayList<User>();
	public Users() {
		this(".");
	}
	
	public Users(String path) {
		BufferedReader in = null;
		try {
			File file = new File(path + "/users.txt");
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			readUsers(in);
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
	private void readUsers(BufferedReader in) {
		String line, id = "", username = "", password = "", firstName = "", isOpen = "";
		StringTokenizer st;
		try {
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					id = st.nextToken().trim();
					username = st.nextToken().trim();
					password = st.nextToken().trim();
					firstName = st.nextToken().trim();
				}
				User user = new User(id, username, password, firstName);
				users.put(id, user);
				usersList.add(user);
				System.out.println(user);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/** Vraca kolekciju proizvoda. */
	public Collection<User> values() {
		return users.values();
	}

	/** Vraca kolekciju proizvoda. */
	public Collection<User> getValues() {
		return users.values();
	}

	/** Vraca proizvod na osnovu njegovog id-a. */
	public User getUser(String id) {
		return users.get(id);
	}

	/** Vraca listu proizvoda. */
	public ArrayList<User> getUserList() {
		return usersList;
	}
}
