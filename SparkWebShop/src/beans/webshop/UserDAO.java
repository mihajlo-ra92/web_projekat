package beans.webshop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import enums.Role;


public class UserDAO {
	private HashMap<String, User> users = new HashMap<String, User>();
	private HashMap<String, Buyer> buyers = new HashMap<String, Buyer>();
	private HashMap<String, Menager> menagers= new HashMap<String, Menager>();
	private HashMap<String, Trainer> trainers= new HashMap<String, Trainer>();
	
	private String path;
	private static Gson g = new Gson();
	private static final java.lang.reflect.Type USERS_TYPE = new TypeToken<ArrayList<User>>() {
	}.getType();
	private static final java.lang.reflect.Type BUYERS_TYPE = new TypeToken<ArrayList<Buyer>>() {
	}.getType();
	private static final java.lang.reflect.Type MENAGERS_TYPE = new TypeToken<ArrayList<Menager>>() {
	}.getType();
	private static final java.lang.reflect.Type TRAINERS_TYPE = new TypeToken<ArrayList<Trainer>>() {
	}.getType();
	public UserDAO() {
		this(".");
	}
	
	public UserDAO(String path) {
		this.path = path;
		
		//BufferedReader in = null;
		try {
			//reading users
			File usersFile = new File(path + "/resources/JSON/users.json");
			System.out.println(usersFile.getCanonicalPath());
			JsonReader usersReader = new JsonReader(new FileReader(usersFile));
			ArrayList<User> usersList = g.fromJson(usersReader, USERS_TYPE);
			
			//reading buyers
			File buyersFile = new File(path + "/resources/JSON/buyers.json");
			System.out.println(buyersFile.getCanonicalPath());
			JsonReader buyersReader = new JsonReader(new FileReader(buyersFile));
			ArrayList<Buyer> buyersList = g.fromJson(buyersReader, BUYERS_TYPE);
			
			//reading menagers
			File menagersFile = new File(path + "/resources/JSON/menagers.json");
			System.out.println(menagersFile.getCanonicalPath());
			JsonReader menagersReader = new JsonReader(new FileReader(menagersFile));
			ArrayList<Menager> menagersList = g.fromJson(menagersReader, MENAGERS_TYPE);
			
			//reading users
			File trainersFile = new File(path + "/resources/JSON/trainers.json");
			System.out.println(trainersFile.getCanonicalPath());
			JsonReader trainersReader = new JsonReader(new FileReader(trainersFile));
			ArrayList<Trainer> trainersList = g.fromJson(trainersReader, TRAINERS_TYPE);
			
//			System.out.println("trainers list test");
//			System.out.println(trainersList.toString());
//			System.out.println("trainers list test");
			
			//fill users hashmap from arraylist
			for (User userIt : usersList) {
				users.put(userIt.getId(), userIt);
			}
			
//			System.out.println("users hashmap test");
//			System.out.println(users.toString());
//			System.out.println("users hashmap test");
			
			//fill buyers hashmap from arraylist
			for (Buyer buyerIt : buyersList) {
				buyers.put(buyerIt.getId(), buyerIt);
			}
			
//			System.out.println("buyers hashmap test");
//			System.out.println(buyers.toString());
//			System.out.println("buyers hashmap test");
			
			//fill menagers hashmap from arraylist
			for (Menager menagerIt : menagersList) {
				menagers.put(menagerIt.getId(), menagerIt);
			}
			
//			System.out.println("menagers hashmap test");
//			System.out.println(menagers.toString());
//			System.out.println("menagers hashmap test");
			
			//fill users hashmap from arraylist
			for (Trainer trainerIt : trainersList) {
				trainers.put(trainerIt.getId(), trainerIt);
			}
			
//			System.out.println("trainers hashmap test");
//			System.out.println(trainers.toString());
//			System.out.println("trainers hashmap test");
		} catch (Exception e) {
			e.printStackTrace();
		}
//		finally {
//			if ( in != null ) {
//				try {
//					in.close();
//				}
//				catch (Exception e) { }
//			}
//		}
	}
	
	public Boolean editUserRequest(String req) throws FileNotFoundException {
		
		User user = g.fromJson(req, User.class);
		
		System.out.println("JSON PRINT of edit request user object:");
		System.out.println(user.toString());
		
		for (User userIt : users.values()) {
			if (usernamesAreSameEdit(userIt, user)) {
				return false;
			}
		}
		editUser(user);
		if (user.getRole() == Role.BUYER) {
			Buyer buyer = g.fromJson(req, Buyer.class);
			editBuyer(buyer);
		}
		
		if (user.getRole() == Role.MENAGER) {
			Menager menager = g.fromJson(req, Menager.class);
			editMenager(menager);
		}
		
		if (user.getRole() == Role.TRAINER) {
			Trainer trainer = g.fromJson(req, Trainer.class);
			editTrainer(trainer);
		}
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
		toJSON(path + "/resources/JSON/users.json", "USER");
	}
	
	private void editBuyer(Buyer buyer) throws FileNotFoundException {
		for (Buyer buyerIt : buyers.values()) {
			if (buyerIt.getId().equals(buyer.getId())) {
				buyers.replace(buyerIt.getId(), buyer);
			}
		}
		toJSON(path + "/resources/JSON/buyers.json", "BUYER");
	}
	
	private void editMenager(Menager menager) throws FileNotFoundException {
		for (Menager menagerIt : menagers.values()) {
			if (menagerIt.getId().equals(menager.getId())) {
				menagers.replace(menagerIt.getId(), menager);
			}
		}
		toJSON(path + "/resources/JSON/menagers.json", "MENAGER");
	}
	
	private void editTrainer(Trainer trainer) throws FileNotFoundException {
		for (Trainer trainerIt : trainers.values()) {
			if (trainerIt.getId().equals(trainer.getId())) {
				trainers.replace(trainerIt.getId(), trainer);
			}
		}
		toJSON(path + "/resources/JSON/trainers.json", "TRAINER");
	}
	
	public Boolean addBuyerRequest(String req) throws FileNotFoundException {
		
		Buyer bu = g.fromJson(req, Buyer.class);
		//User us = g.fromJson(req, User.class);
		String id = Integer.toString(users.size()+1);
		bu.setId(id);
		
		System.out.println("JSON PRINT of add request buyer object:");
		System.out.println(bu.toString());
		
		for (User userIt : users.values()) {
			if (usernamesAreSame(userIt, bu)) {
				System.out.println("Username taken!");
				return false;
			}
		}
		User us = (User) bu;
		//System.out.println("BUYER CASTED TO USER:" + us);
		addUser(us);
		addBuyer(bu);
		return true;
	}
	
	public Boolean addMenagerRequest(String req) throws FileNotFoundException {
		
		Menager me= g.fromJson(req, Menager.class);
		//User us = g.fromJson(req, User.class);
		String id = Integer.toString(users.size()+1);
		me.setId(id);
		
		System.out.println("JSON PRINT of add request menager object:");
		System.out.println(me.toString());
		
		for (User userIt : users.values()) {
			if (usernamesAreSame(userIt, me)) {
				System.out.println("Username taken!");
				return false;
			}
		}
		User us = (User) me;
		//System.out.println("BUYER CASTED TO USER:" + us);
		addUser(us);
		addMenager(me);
		return true;
	}

	public Boolean addTrainerRequest(String req) throws FileNotFoundException {
		
		Trainer tr= g.fromJson(req, Trainer.class);
		//User us = g.fromJson(req, User.class);
		String id = Integer.toString(users.size()+1);
		tr.setId(id);
		
		System.out.println("JSON PRINT of add request trainer object:");
		System.out.println(tr.toString());
		
		for (User userIt : users.values()) {
			if (usernamesAreSame(userIt, tr)) {
				System.out.println("Username taken!");
				return false;
			}
		}
		User us = (User) tr;
		//System.out.println("BUYER CASTED TO USER:" + us);
		addUser(us);
		addTrainer(tr);
		return true;
	}
	
	private Boolean usernamesAreSame(User us1, User us2) {
		return (us1.getUsername().equals(us2.getUsername()));
	}
	
	public void addUser(User user) throws FileNotFoundException {
		users.put(user.getId(), user);
		System.out.println("PUTING USER: " + user);
		toJSON(path + "/resources/JSON/users.json", "USER");
	}
	
	public void addBuyer(Buyer buyer) throws FileNotFoundException {
		buyers.put(buyer.getId(), buyer);
		toJSON(path + "/resources/JSON/buyers.json", "BUYER");
	}
	
	public void addMenager(Menager menager) throws FileNotFoundException {
		menagers.put(menager.getId(), menager);
		toJSON(path + "/resources/JSON/menagers.json", "MENAGER");
	}
	
	public void addTrainer(Trainer trainer) throws FileNotFoundException {
		trainers.put(trainer.getId(), trainer);
		toJSON(path + "/resources/JSON/trainers.json", "TRAINER");
	}
	
	private void toJSON(String filename, String command) throws FileNotFoundException {
		PrintWriter out = new PrintWriter(filename);
		switch(command) {
		case "USER":
			//System.out.println("users to json: " + users);
			out.printf(g.toJson(users.values()));
			break;
		case "BUYER":
			System.out.println("buyers to json: " + buyers);
			out.printf(g.toJson(buyers.values()));
			break;
		case "MENAGER":
			System.out.println("menagers to json: " + menagers);
			out.printf(g.toJson(menagers.values()));
			break;
		case "TRAINER":
			System.out.println("trainers to json: " + trainers);
			out.printf(g.toJson(trainers.values()));
		default:
			//
		}
		out.close();
		
		
	}
	
	public Collection<Menager> getFreeMenagers(){
		ArrayList<Menager> collection = new ArrayList<Menager>();
		for (Menager menagerIt : menagers.values()) {
			if (menagerIt.getSportObject() == null) {
				collection.add(menagerIt);
			}
		}
		return collection;
	}
	
	/** Vraca kolekciju proizvoda. */
	public Collection<User> values() {
		return users.values();
	}

	/** Vraca proizvod na osnovu njegovog id-a. */
	public User getUser(String id) {
		return users.get(id);
	}
	
	public Buyer getBuyer(String id) {
		return buyers.get(id);
	}
	
	public Menager getMenager(String id) {
		return menagers.get(id);
	}
	
	public Trainer getTrainer(String id) {
		return trainers.get(id);
	}
	
	public User getUserByUsername(String username) {
		for (User userIt : users.values()) {
			if (userIt.getUsername().equals(username)) {
				return userIt;
			}
		}
		return null;
	}
}
