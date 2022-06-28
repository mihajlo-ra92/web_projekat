package beans.webshop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class WorkoutDAO {
	private HashMap<String, Workout> workouts = new HashMap<String, Workout>();
	private String path;
	private static Gson g = new Gson();
	private static final java.lang.reflect.Type WORKOUTS_TYPE = new TypeToken<ArrayList<Workout>>() {
	}.getType();
	
	public WorkoutDAO() {
		this(".");
	}
	public WorkoutDAO(String path) {
		this.path = path;
		
		try {
			File file = new File(path + "/resources/JSON/workouts.json");
			System.out.println(file.getCanonicalPath());
			JsonReader reader = new JsonReader(new FileReader(file));
			ArrayList<Workout> workoutsList = g.fromJson(reader, WORKOUTS_TYPE);
			
			//treba da bude posebna f-ja
			for (Workout workIt : workoutsList) {
				workouts.put(workIt.getName(), workIt);
			}
//			SportObjectDAO soDAO = new SportObjectDAO();
//			SportObject so = soDAO.getSportObject("1");
//			Workout w = new Workout("Trening1", "personal", so, "45min", "description", "6");
//			addWorkout(w);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void addWorkout(Workout workout) throws FileNotFoundException {
		workouts.put(workout.getName(), workout);
		toJSON(path + "/resources/JSON/workouts.json");
	}
	private void toJSON(String filename) throws FileNotFoundException {
		PrintWriter out = new PrintWriter(filename);
		out.printf(g.toJson(workouts.values()));
		out.close();
	}

}
