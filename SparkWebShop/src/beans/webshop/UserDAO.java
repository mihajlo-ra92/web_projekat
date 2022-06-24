package beans.webshop;

import java.awt.Window.Type;
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

import enums.Gender;

public class UserDAO {
	private HashMap<String, User> users = new HashMap<String, User>();
	//private ArrayList<User> usersList = new ArrayList<User>();
	private User currentUser = new User();
	private String path;
	private static Gson g = new Gson();
	private static final java.lang.reflect.Type USERS_TYPE = new TypeToken<ArrayList<User>>() {
	}.getType();
	public UserDAO() {
		this(".");
	}
	
	public UserDAO(String path) {
		this.path = path;
		
		BufferedReader in = null;
		try {
			File file = new File(path + "/resources/JSON/users.json");
			System.out.println(file.getCanonicalPath());
			JsonReader reader = new JsonReader(new FileReader(file));
			ArrayList<User> usersList = g.fromJson(reader, USERS_TYPE);
//			System.out.println("list test");
//			System.out.println(usersList.toString());
//			System.out.println("list test");
			
			//fill hashmap from arraylist
			for (User userIt : usersList) {
				users.put(userIt.getId(), userIt);
			}
			
			System.out.println("hashmap test");
			System.out.println(users.toString());
			System.out.println("hashmap test");
			//System.out.println(getCurrentUser());
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
	
	public Boolean editUserRequest(String req) throws FileNotFoundException {
		
		User us = g.fromJson(req, User.class);
		
		System.out.println("JSON PRINT of edit request user object:");
		System.out.println(us.toString());
		
		for (User userIt : users.values()) {
			if (usernamesAreSameEdit(userIt, us)) {
				return false;
			}
		}
		editUser(us);
		return true;
	}
	private Boolean usernamesAreSameEdit(User us1, User us2) {
		return ((us1.getUsername().equals(us2.getUsername())) &
				!(us1.getId().equals(us2.getId())));
	}
		
	private void editUser(User user) throws FileNotFoundException {
		for (User userIt : users.values()) {
			if (userIt.getId().equals(user.getId())) {
				users.replace(userIt.getId(), user);
			}
		}
//		System.out.println("new hashmap:");
//		for (User userIt : users.values()) {
//			System.out.println(userIt.toString());
//		}
		toJSON(path + "/resources/JSON/users.json");
	}
	
	public Boolean addUserRequest(String req) throws FileNotFoundException {
		
		User us = g.fromJson(req, User.class);
		String id = Integer.toString(users.size()+1);
		us.setId(id);
		
		System.out.println("JSON PRINT of add request user object:");
		System.out.println(us.toString());
		
		for (User userIt : users.values()) {
			if (usernamesAreSame(userIt, us)) {
				return false;
			}
		}
		addUser(us);
		return true;
	}
	private Boolean usernamesAreSame(User us1, User us2) {
		return (us1.getUsername().equals(us2.getUsername()));
	}
	
	public User getCurrentUser() {
		return users.get("3");
	}
	
	public void addUser(User user) throws FileNotFoundException {
		users.put(user.getId(), user);
		toJSON(path + "/resources/JSON/users.json");
	}
	
	private void toJSON(String filename) throws FileNotFoundException {
		PrintWriter out = new PrintWriter(filename);
		out.printf(g.toJson(users.values()));
		out.close();
	}
	
	/** Vraca kolekciju proizvoda. */
	public Collection<User> values() {
		return users.values();
	}

	/** Vraca proizvod na osnovu njegovog id-a. */
	public User getUser(String id) {
		return users.get(id);
	}
}
