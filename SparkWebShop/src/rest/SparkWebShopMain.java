package rest;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;
import java.io.File;
import com.google.gson.Gson;
import beans.webshop.SportObjectDAO;
import beans.webshop.User;
import beans.webshop.UserDAO;

public class SparkWebShopMain {
	private static SportObjectDAO sportObjects = new SportObjectDAO();
	private static UserDAO users = new UserDAO();
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
			User us = users.getUser(req.session().attribute("logednUserId"));
			if (us == null) {
				return "404";
			}
			System.out.println(us.toString());
			return g.toJson(us);
		});
		
		get("/rest/proizvodi/getJustUsers", (req, res) -> {
			res.type("application/json");
			return g.toJson(users.values());
		});
		
		//USER POST REQUESTS:
		post("/rest/proizvodi/log-in", (req, res) -> {
			res.type("application/json");
			//System.out.println("REQ BODY:::");
			//System.out.println(req.body());
			User testUs = users.getUser(req.session().attribute("logednUserId"));
			//System.out.println("loged user:");
			//System.out.println(testUs);
			if (testUs != null) {
				return "403";
			}
			User us = g.fromJson(req.body(), User.class);
			User user = users.getUserByUsername(us.getUsername());
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
			System.out.println(req.body());
			Boolean isSuccessful = users.addBuyerRequest(req.body());
			System.out.println("Register is successful: " + isSuccessful);
			return isSuccessful;
		});
		
		post("/rest/register-menager", (req, res) -> {
			res.type("application/json");
			System.out.println(req.body());
			Boolean isSuccessful = users.addMenagerRequest(req.body());
			System.out.println("Register is successful: " + isSuccessful);
			return isSuccessful;
		});
		
		post("/rest/edit-profile", (req, res) -> {
			res.type("application/json");
			System.out.println(req.body());
			Boolean isSuccessful = users.editUserRequest(req.body());
			System.out.println("Edit is successful: " + isSuccessful);
			return isSuccessful;
		});
		
		//SPORTOBJECT GET REQUESTS:
		get("/rest/proizvodi/getJustSportObjects", (req, res) -> {
			res.type("application/json");
			return g.toJson(sportObjects.values());
		});
		
		//SPORTOBJECT POST REQUESTS:
		post("/rest/register-sport-object", (req, res) -> {
			res.type("application/json");
			System.out.println(req.body());
			Boolean isSuccessful = sportObjects.addSportObjectsRequest(req.body());
			System.out.println("Register sport object is successful: " + isSuccessful);
			return isSuccessful;
		});
		//treba get za pretragu objekta na beku da napravim
	}
}
