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

import enums.Gender;

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
		String line, id = "", username = "", password = "",
				firstName = "", lastName = "", gender = "",
				birthDateStr = "";
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
					lastName = st.nextToken().trim();
					birthDateStr = st.nextToken().trim();
					gender = st.nextToken().trim();
				}
				User user = new User(id, username, password, firstName,
						lastName, birthDateStr, Gender.valueOf(gender));
				users.put(id, user);
				usersList.add(user);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
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
	//	User newUser = new User(id, username, password, firstName,
	//			lastName, birthDateStr, gender);
	//	addUser(newUser);
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
		
		String id = Integer.toString(usersList.size()+1);
		User newUser = new User(id, username, password, firstName,
				lastName, birthDateStr, gender);
		addUser(newUser);
	}
	public void addUser(User user) throws FileNotFoundException {
		users.put(user.getId(), user);
		usersList.add(user);
		toCSV(path + "/users.txt");
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
