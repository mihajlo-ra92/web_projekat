package rest;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;
import java.io.File;
import com.google.gson.Gson;
import beans.webshop.SportObjectDAO;
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
			return g.toJson(users.getCurrentUser());
		});
		
		get("/rest/proizvodi/getJustUsers", (req, res) -> {
			res.type("application/json");
			return g.toJson(users.values());
		});
		
		//USER POST REQUESTS:
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
		
		//SPORTOBJECT GET REQUESTS:
		get("/rest/proizvodi/getJustSportObjects", (req, res) -> {
			res.type("application/json");
			return g.toJson(sportObjects.values());
		});
		//treba get za pretragu objekta na beku da napravim
	}
}
