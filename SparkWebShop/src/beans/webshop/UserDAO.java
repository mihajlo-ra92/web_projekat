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
	private ArrayList<User> usersList = new ArrayList<User>();
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
			File file = new File(path + "/users.json");
			System.out.println(file.getCanonicalPath());
			JsonReader reader = new JsonReader(new FileReader(file));
			usersList = g.fromJson(reader, USERS_TYPE);
			System.out.println("list test");
			System.out.println(usersList.toString());
			System.out.println("list test");
			
			for (User userIt : usersList) {
				users.put(userIt.getId(), userIt);
			}
			System.out.println("hashmap test");
			System.out.println(users.toString());
			System.out.println("hashmap test");
			System.out.println(getCurrentUser());
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
	
	public void editUserRequest(String req) throws FileNotFoundException {
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
		
		String firstName = reqSplit[2].split(":")[1];
		//removes first and last character
		firstName = firstName.substring(1, firstName.length() - 1);
		System.out.println("firstName: " + firstName);
		
		String lastName = reqSplit[3].split(":")[1];
		//removes first and last character
		lastName = lastName.substring(1, lastName.length() - 1);
		System.out.println("lastName: " + lastName);
		
		String birthDateStr = reqSplit[4].split(":")[1];
		//removes first and last character
		birthDateStr = birthDateStr.substring(1, birthDateStr.length() - 1);
		System.out.println("birthDateStr: " + birthDateStr);
		
		String genderStr = reqSplit[5].split(":")[1];
		//removes first and last character
		genderStr = genderStr.substring(1, genderStr.length() - 1);
		System.out.println("genderStr: " + genderStr);
		Gender gender = Gender.valueOf(genderStr); 
		
		
		for (int i = 0; i < this.usersList.size(); i++ ) {
			User newUser = usersList.get(i);
			if(newUser.getUsername() == username) {
				newUser.setId(usersList.get(i).getId());
				newUser.setPassword(password);
				users.get(usersList.get(i).getId()).setPassword(password);
				newUser.setFirstName(firstName);
				users.get(usersList.get(i).getId()).setFirstName(firstName);
				newUser.setLastName(lastName);
				users.get(usersList.get(i).getId()).setLastName(lastName);
				newUser.setGender(gender);
				users.get(usersList.get(i).getId()).setGender(gender);
				newUser.setBirthDate(birthDateStr);
				users.get(usersList.get(i).getId()).setBirthDate(birthDateStr);
				usersList.set(i, newUser);
				
			}
			toCSV(path + "/users.txt");
		}
	}
		
	private void toCSV(String filename) throws FileNotFoundException {
			
		PrintWriter out = new PrintWriter(filename);
		for (User userIt : users.values()) {
			out.printf(userIt.getId() + ";" + userIt.getUsername()
			+ ";" + userIt.getPassword() + ";" + userIt.getFirstName()
			+ ";" + userIt.getLastName() + ";" + userIt.getBirthDate()
			+ ";" + userIt.getGender().toString()
			+ "\n");
		}
		out.close();
	}
	
	public Boolean addUserRequest(String req) throws FileNotFoundException {
		
		User us = g.fromJson(req, User.class);
		String id = Integer.toString(usersList.size()+1);
		us.setId(id);
		System.out.println("JSON PRINT::");
		System.out.println(us.toString());
		for (User userIt : users.values()) {
			if (userIt.getUsername().equals(us.getUsername())) {
				return false;
			}
		}
		addUser(us);
		return true;
	}
	public User getCurrentUser() {
		return users.get("1");
	}
	public void addUser(User user) throws FileNotFoundException {
		users.put(user.getId(), user);
		usersList.add(user);
		toJSON(path + "/users.json");
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
