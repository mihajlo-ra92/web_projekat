package rest;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.Gson;

import beans.webshop.Buyer;
import beans.webshop.Comment;
import beans.webshop.CommentDAO;
import beans.webshop.MembershipDAO;
import beans.webshop.Menager;
import beans.webshop.SportObject;
import beans.webshop.SportObjectDAO;
import beans.webshop.Trainer;
import beans.webshop.TrainingHistoryDAO;
import beans.webshop.TrainingSession;
import beans.webshop.User;
import beans.webshop.UserDAO;
import beans.webshop.Workout;
import beans.webshop.WorkoutDAO;
import enums.Role;

public class SparkWebShopMain {
	private static SportObjectDAO sportObjectDAO = new SportObjectDAO();
	private static WorkoutDAO workoutDAO = new WorkoutDAO();
	private static MembershipDAO membershipDAO = new MembershipDAO();
	private static TrainingHistoryDAO trainingHistoryDAO = new TrainingHistoryDAO();
	private static CommentDAO commentDAO = new CommentDAO();
	private static UserDAO userDAO = new UserDAO();
	private static Gson g = new Gson();
	private static final DecimalFormat df = new DecimalFormat("0.00");


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
		
		post("/rest/proizvodi/getTrainingHistory", (req, res) -> {
			res.type("application/json");
			System.out.println("TRAINING HISTORY REQ: " + req.body());
			User user= g.fromJson(req.body(), User.class);
			System.out.println("SENT USER: " + user);
			ArrayList<TrainingSession> retVal = new ArrayList<TrainingSession>();
			for (TrainingSession tsIt : trainingHistoryDAO.values()) {
				if (user.getUsername().equals(tsIt.getBuyer())) {
					System.out.println("FOUND TRAININGSESSION:" + tsIt.getWorkout());
					retVal.add(tsIt);
				}
			}
			if (retVal.isEmpty()) {	
				return "EMPTY";
			}
			else {				
				return g.toJson(retVal);
			}
		});
		post("/rest/getUserById", (req, res) -> {
			res.type("application/json");
			System.out.println("REQ: " + req.body());
			return g.toJson(userDAO.getUser(req.body()));
		});
		
		
		//SPORTOBJECT GET REQUESTS:
		get("/rest/proizvodi/getJustSportObjects", (req, res) -> {
			updateObjectsGrades();
			res.type("application/json");
			return g.toJson(sportObjectDAO.values());
		});
		
		get("/rest/proizvodi/getVisitedSportObjects", (req, res) -> {
			res.type("application/json");
			User user = userDAO.getUser(req.session().attribute("logednUserId"));
			ArrayList<String> visitedNames = trainingHistoryDAO.getVisitedNames(user.getUsername());
			return g.toJson(sportObjectDAO.getObjectsByNames(visitedNames));
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
				SportObject retVal = sportObjectDAO.getSportObjectByName(userDAO.getMenagersSportObject(user.getUsername()));
				System.out.println("SPORTOBJECT: " + g.toJson(retVal));
				return g.toJson(retVal);
			}
		});
		
		//TRAINER GET REQUEST:
		get("rest/getAllTrainers",(req,res) ->{
			res.type("application/json");
			return g.toJson(userDAO.getTrainers());
		});
		
		//CONTENT GET REQUEST:
			get("/rest/contnentsForMenagersObject", (req,res) -> {
				res.type("application/json");
				User user = userDAO.getUser(req.session().attribute("logednUserId"));
				SportObject sportObject = sportObjectDAO.getSportObjectByName(userDAO.getMenagersSportObject(user.getUsername()));
				System.out.println(workoutDAO.getBySportObject(sportObject.getName()));
				return g.toJson(workoutDAO.getBySportObject(sportObject.getName()));
			});
			
			get("rest/proizvodi/getAllcontents", (req,res) ->{
				res.type("application/json");
				return g.toJson(workoutDAO.getValues());
			});
		
		//CONTENT POST REQUEST:
		post("rest/proizvodi/CreateContent",(req,res) -> {
			res.type("application/json");
			System.out.println("names0: " + req.body());
			User user = userDAO.getUser(req.session().attribute("logednUserId"));
			Workout wo = g.fromJson(req.body(), Workout.class);
			SportObject sportObject = sportObjectDAO.getSportObjectByName(userDAO.getMenagersSportObject(user.getUsername()));
			wo.setSportObject(sportObject.getName());
			workoutDAO.addWorkout(wo);
			System.out.println(wo.toString());
			return workoutDAO.getBySportObject(sportObject.getName());
			});
			
		post("rest/editContent", (req,res) -> {
			res.type("application/json");
			Workout newWorkout = g.fromJson(req.body(), Workout.class);
			workoutDAO.editWorkout(newWorkout);
			return "ok";
		});
		
		post("rest/AddTrainerToContent", (req,res) ->{
			res.type("application/json");
			String [] names = req.body().split("\\+");
			String NameWorkout = names[0];
			String reqTrainer = names[1];			
			System.out.println("nulti: " + NameWorkout);
			System.out.println("prvi: " + reqTrainer);
			User user = userDAO.getUser(req.session().attribute("logednUserId"));
			SportObject sportObject = sportObjectDAO.getSportObjectByName(userDAO.getMenagersSportObject(user.getUsername()));
			ArrayList<Workout> wo = workoutDAO.getBySportObject(sportObject.getName());
			workoutDAO.setTrainerToWorkout(wo,NameWorkout,reqTrainer);
			for(Workout woIt : wo) {
				if(woIt.getName().equals(NameWorkout)) {
					woIt.setTrainer(reqTrainer);
					break;
				}
			}
			return "OK";
		});
		
		post("rest/deleteContent" , (req,res) ->{
			res.type("application/json");
			User user = userDAO.getUser(req.session().attribute("logednUserId"));
			SportObject sportObject = sportObjectDAO.getSportObjectByName(userDAO.getMenagersSportObject(user.getUsername()));
			workoutDAO.DeleteContentByNameAndSportObject(sportObject.getName(),req.body());
			return "ok";
		});
		
		//TRAINING SESSION GET REQUEST:
		get("rest/TrainingsInSportObject" , (req,res) ->{
			res.type("application/json");
			User user = userDAO.getUser(req.session().attribute("logednUserId"));
			SportObject sportObject = sportObjectDAO.getSportObjectByName(userDAO.getMenagersSportObject(user.getUsername()));
			ArrayList<TrainingSession> retVal = trainingHistoryDAO.getTrainingsbySportObjectName(sportObject.getName());
			if(retVal == null) {
				return "403";
			}else {
				return g.toJson(retVal);
			}
		});
		
		get("rest/traingSessionsForTrainerPersonal", (req,res) ->{
			res.type("application/json");
			User user = userDAO.getUser(req.session().attribute("logednUserId"));
			return g.toJson(trainingHistoryDAO.getTrSessionsPersonalFromTrainer(user.getUsername()));
		});
		
		get("rest/traingSessionsForTrainerGroup", (req,res) ->{
			res.type("application/json");
			User user = userDAO.getUser(req.session().attribute("logednUserId"));
			System.out.println(trainingHistoryDAO.getTrSessionsGroupFromTrainer(user.getUsername()));
			if(g.toJson(trainingHistoryDAO.getTrSessionsGroupFromTrainer(user.getUsername())) != null) {				
				return g.toJson(trainingHistoryDAO.getTrSessionsGroupFromTrainer(user.getUsername()));
			}else {
				return "404";
			}
		});
		//TRAINING SESSION POST REQUEST:
		post("rest/deleteTraining", (req,res) ->{
			res.type("application/json");
			trainingHistoryDAO.deleteTrainingById(req.body());
			return "ok";
		});
		
		post("rest/startTraining", (req,res) ->{
			res.type("application/json");
			User user = userDAO.getUser(req.session().attribute("logednUserId"));
			System.out.println("REQ BODY: " + req.body());
			Workout workout = g.fromJson(req.body(), Workout.class);
			trainingHistoryDAO.addTrainingSessionRequest(workout, user.getUsername());
			return "ok";
		});
		
		//MEMBERSHIP POST REQUESTS:
		post("/rest/activate", (req, res) -> {
			res.type("application/json");
			System.out.println("REQ BODY:::");
			System.out.println(req.body());
			User user = userDAO.getUser(req.session().attribute("logednUserId"));
			
			Boolean isSuccessfull = membershipDAO.addMembershipRequest(req.body() + ";" + user.getUsername());
			
			return isSuccessfull;
		});
		
		//MEMBERSHIP GET REQUESTS:
		get("rest/check-membership", (req,res) ->{
			res.type("application/json");
			User user = userDAO.getUser(req.session().attribute("logednUserId"));
			return membershipDAO.checkMembership(user);
		});
		//COMMENT POST REQUESTS:
		post("/rest/submit-comment", (req, res) -> {
			res.type("application/json");
			System.out.println("REQ BODY:::");
			System.out.println(req.body());
			Comment comment = g.fromJson(req.body(), Comment.class);
			
			return commentDAO.submitComment(comment);
		});
		post("/rest/comments-for-object", (req, res) -> {
			res.type("application/json");
			//System.out.println(req.body());
			return g.toJson(commentDAO.getApprovedForObject(req.body()));
		});
		post("/rest/update-comment", (req, res) -> {
			res.type("application/json");
			System.out.println("REQ BODY:::");
			System.out.println(req.body());
			Comment comment = g.fromJson(req.body(), Comment.class);
			commentDAO.updateComment(comment);
			return "OK";
		});
		//COMMENT GET REQUESTS:
		get("rest/get-unapproved-comments", (req,res) ->{
			res.type("application/json");
			ArrayList<Comment> comments = commentDAO.getWaiting();
			return g.toJson(comments);
		});
		
		
	}
	//FUNCTIONS
	public static void updateObjectsGrades() {
		Double objectPoints;
		Integer objectGrades;
		for (SportObject objectIt : sportObjectDAO.values()) {
			objectPoints = 0.0;
			objectGrades = 0;
			for (Comment commIt : commentDAO.getApprovedForObject(objectIt.getName())){
				objectPoints += commIt.getGrade();
				objectGrades += 1;
			}
			if (objectGrades == 0) {
				
				objectIt.setAvegareGrade(0);
			}
			else {
				objectIt.setAvegareGrade(Double.parseDouble(df.format(objectPoints/objectGrades)));
			}
		}
	}
}
