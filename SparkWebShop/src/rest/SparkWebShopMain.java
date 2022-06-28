package rest;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;
import java.io.File;
import com.google.gson.Gson;

import beans.webshop.Buyer;
import beans.webshop.Menager;
import beans.webshop.SportObject;
import beans.webshop.SportObjectDAO;
import beans.webshop.Trainer;
import beans.webshop.User;
import beans.webshop.UserDAO;
import beans.webshop.WorkoutDAO;
import enums.Role;

public class SparkWebShopMain {
	private static SportObjectDAO sportObjectDAO = new SportObjectDAO();
	private static WorkoutDAO workoutDAO = new WorkoutDAO();
	private static UserDAO userDAO = new UserDAO();
	private static Gson g = new Gson();


	public static void main(String[] args) throws Exception {
		port(8080);
		
		staticFiles.externalLocation(new File("./static").getCanonicalPath()); 
		
		get("/test", (req, res) -> {
			return "Works";
		});
		
		//USER GET REQUESTS:
		get("/rest/getCurrentUser", (req, res) -> {
			res.type("application/json");
			User user = userDAO.getUser(req.session().attribute("logednUserId"));
			if (user == null) {
				return "404";
			}
			System.out.println(user.toString());
			switch (user.getRole()){
			case BUYER:
				Buyer buyer = userDAO.getBuyer(user.getId());
				//System.out.println("RETURNING BUYER");
				return g.toJson(buyer);
			case MENAGER:
				Menager menager = userDAO.getMenager(user.getId());
				//System.out.println("RETURNING MENAGER");
				return g.toJson(menager);
			case TRAINER:
				Trainer trainer = userDAO.getTrainer(user.getId());
				//System.out.println("RETURNING TRAINER");
				return g.toJson(trainer);
			default:
				//System.out.println("RETURNING USER");
				return g.toJson(user);
			}
		});
		
		get("/rest/proizvodi/getJustUsers", (req, res) -> {
			res.type("application/json");
			return g.toJson(userDAO.values());
		});
		
		get("/rest/proizvodi/getFreeMenagers", (req, res) -> {
			res.type("application/json");
			return g.toJson(userDAO.getFreeMenagers());
		});
		
		//USER POST REQUESTS:
		post("/rest/proizvodi/log-in", (req, res) -> {
			res.type("application/json");
			//System.out.println("REQ BODY:::");
			//System.out.println(req.body());
			User testUs = userDAO.getUser(req.session().attribute("logednUserId"));
			//System.out.println("loged user:");
			//System.out.println(testUs);
			if (testUs != null) {
				return "403";
			}
			User us = g.fromJson(req.body(), User.class);
			User user = userDAO.getUserByUsername(us.getUsername());
			req.session().attribute("logednUserId", user.getId());
			return g.toJson(us);
		});
		
		post("/rest/log-out", (req, res) -> {
			res.type("application/json");
			req.session().invalidate();
			return "OK";
		});
		
		post("/rest/register", (req, res) -> {
			res.type("application/json");
			//System.out.println(req.body());
			Boolean isSuccessful = userDAO.addBuyerRequest(req.body());
			//System.out.println("Register is successful: " + isSuccessful);
			return isSuccessful;
		});
		
		post("/rest/set-object-to-menager", (req, res) -> {
			res.type("application/json");
			System.out.println("SET SPORT OBJECT TO MENAGER REQUEST BODY:");
			System.out.println(req.body());
			String [] names = req.body().split("\\+");
			System.out.println("names0: " + names[0]);
			System.out.println("names1: " + names[1]);
			SportObject sportObject = sportObjectDAO.getSportObjectByName(names[0]);
			Menager menager = userDAO.getMenagerByUsername(names[1]);
			userDAO.setSportObjectToMenager(sportObject, menager);
			
			return "OK";
		});
		
		post("/rest/register-menager", (req, res) -> {
			res.type("application/json");
			//System.out.println(req.body());
			Boolean isSuccessful = userDAO.addMenagerRequest(req.body());
			//System.out.println("Register is successful: " + isSuccessful);
			return isSuccessful;
		});
		
		post("/rest/register-trainer", (req, res) -> {
			res.type("application/json");
			//System.out.println(req.body());
			Boolean isSuccessful = userDAO.addTrainerRequest(req.body());
			//System.out.println("Register is successful: " + isSuccessful);
			return isSuccessful;
		});
		
		post("/rest/edit-profile", (req, res) -> {
			res.type("application/json");
			//System.out.println(req.body());
			Boolean isSuccessful = userDAO.editUserRequest(req.body());
			//System.out.println("Edit is successful: " + isSuccessful);
			return isSuccessful;
		});
		
		//SPORTOBJECT GET REQUESTS:
		get("/rest/proizvodi/getJustSportObjects", (req, res) -> {
			res.type("application/json");
			return g.toJson(sportObjectDAO.values());
		});
    
		//SPORTOBJECT POST REQUESTS:
		post("/rest/register-sport-object", (req, res) -> {
			res.type("application/json");
			//System.out.println(req.body());
			Boolean isSuccessful = sportObjectDAO.addSportObjectsRequest(req.body());
			//System.out.println("Register sport object is successful: " + isSuccessful);
			return isSuccessful;
		});
		
		//MENAGER GET REQUEST:
		get("/rest/MenagersSportObject", (req,res) -> {
			res.type("application/json");
			User user = userDAO.getUser(req.session().attribute("logednUserId"));
			if (user == null) {
				return "404";
			}else {				
				return g.toJson(userDAO.getMenagersSportObject(user.getUsername()));
			}
		});
	}
}
