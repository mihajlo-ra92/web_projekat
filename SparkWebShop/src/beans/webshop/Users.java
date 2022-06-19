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

public class Users {
	private HashMap<String, User> users = new HashMap<String, User>();
	private ArrayList<User> usersList = new ArrayList<User>();
	String path;
	public Users() {
		this(".");
	}
	
	public Users(String path) {
		this.path = path;
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
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public void addUserRequest(String req) throws FileNotFoundException {
		//removes { } from request
		req = req.substring(1, req.length() - 1);
		
		String[] reqSplit = req.split(",");
		
		String username = reqSplit[0].split(":")[1];
		//removes first and last character
		username = username.substring(1, username.length() - 1);
		System.out.println("username: " + username);
		
		String password = reqSplit[1].split(":")[1];
		//removes first and last character
		password = password.substring(1, password.length() - 1);
		System.out.println("password: " + password);
		
		String fName = reqSplit[2].split(":")[1];
		//removes first and last character
		fName = fName.substring(1, fName.length() - 1);
		System.out.println("fName: " + fName);
		
		String id = Integer.toString(usersList.size()+2);
		User newUser = new User(id, username, password, fName);
		addUser(newUser);
	}
	public void addUser(User user) throws FileNotFoundException {
		users.put(user.getId(), user);
		usersList.add(user);
		toCSV(path + "/users_test.txt");
	}
	private void toCSV(String filename) throws FileNotFoundException {
		System.out.println(filename);
		PrintWriter out = new PrintWriter(filename);
		for (User userIt : users.values()) {
			out.printf(userIt.getId() + ";" + userIt.getUsername()
			+ ";" + userIt.getPassword() + ";" + userIt.getFirstName() + "\n");
		}
		out.close();
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
