package rest;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import java.io.File;

import com.google.gson.Gson;

import beans.webshop.Locations;
import beans.webshop.ProductToAdd;
import beans.webshop.Products;
import beans.webshop.ShoppingCart;
import beans.webshop.SportObjectDAO;
import beans.webshop.UserDAO;
import spark.Request;
import spark.Session;

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
		get("/rest/getCurrentUser", (req, res) -> {
			res.type("application/json");
			return g.toJson(users.getCurrentUser());
		});
		
		get("/rest/proizvodi/getJustSportObjects", (req, res) -> {
			res.type("application/json");
			return g.toJson(sportObjects.values());
		});
		
		get("/rest/proizvodi/getJustUsers", (req, res) -> {
			res.type("application/json");
			return g.toJson(users.values());
		});
		
		get("rest/proizvodi/getUser", (req, res) -> {
			res.type("application/json");
			return g.toJson(users.getUser("1"));
		});
		
		post("/rest/proizvodi/editUser", (req, res) -> {
			res.type("application/json");
			System.out.println(req.body());
			users.editUserRequest(req.body());
			return "OK";
		});
		
		post("/rest/register", (req, res) -> {
			res.type("application/json");
			System.out.println(req.body());
			Boolean isSuccessful = users.addUserRequest(req.body());
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
	}
}
