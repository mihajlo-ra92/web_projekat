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
			if (workoutsList != null) {
				for (Workout workIt : workoutsList) {
					workouts.put(workIt.getId(), workIt);
				}
			}
			
//			System.out.println(workouts);
//			Workout w = new Workout("VrstaTreninga3", "swimming", "2", "45", "description3", "");
//			addWorkout(w);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	private String getNewId() {
		int largest = -1;
		for (Workout workIt: workouts.values()) {
			if (Integer.parseInt(workIt.getId()) > largest) {
				largest = Integer.parseInt(workIt.getId());
			}
		}
		return Integer.toString(largest + 1);
	}
	
	public void setTrainerToWorkout(ArrayList<Workout> wo, String NameWorkout,String reqTrainer) throws FileNotFoundException {
		for(Workout woIt : wo) {
			if(woIt.getName().equals(NameWorkout)) {
				woIt.setTrainer(reqTrainer);
				break;
			}
		}
		toJSON(path + "/resources/JSON/workouts.json");
	}
	
	public void DeleteContentByNameAndSportObject(String SOName, String WorkoutName) throws FileNotFoundException {
		for(Workout woIt : workouts.values()) {
			if(woIt.getName().equals(WorkoutName) && woIt.getSportObject().equals(SOName)) {
				workouts.remove(woIt.getId());
			}
		}
		toJSON(path + "/resources/JSON/workouts.json");
	}
	public void addWorkout(Workout workout) throws FileNotFoundException {
		workout.setId(getNewId());
		workouts.put(workout.getId(), workout);
		toJSON(path + "/resources/JSON/workouts.json");
	}
	private void toJSON(String filename) throws FileNotFoundException {
		PrintWriter out = new PrintWriter(filename);
		out.printf(g.toJson(workouts.values()));
		out.close();
	}
	public void editWorkout(Workout wo) throws FileNotFoundException {
		for(Workout woIt : workouts.values()) {
			if(woIt.getId().equals(wo.getId())) {
				woIt.setDescription(wo.getDescription());
				woIt.setName(wo.getName());
				woIt.setWorkoutDuration(wo.getWorkoutDuration());
				woIt.setWorkoutType(wo.getWorkoutType());
			}
		}
		toJSON(path + "/resources/JSON/workouts.json");
	}
	
	
	
	public ArrayList<Workout> getBySportObject(String SOName){
		ArrayList<Workout> returnList = new ArrayList<Workout>();
		for(Workout woIt : workouts.values()) {
			if (woIt.getSportObject().equals(SOName)) {
				returnList.add(woIt);
			}
		}
		return returnList;
	}
	public Collection<Workout> getValues(){
		return workouts.values();
	}
	public ArrayList<Workout> getPersonalWorkoutsForTrainer(String username){
		ArrayList<Workout> retVal = new ArrayList<Workout>();
		for (Workout woIt : workouts.values()) {
			if (woIt.getWorkoutType().equals("Personal")) {
				if (woIt.getTrainer() != null) {
					if (woIt.getTrainer().equals(username)) {
						retVal.add(woIt);
					}
				}
			}
		}
		return retVal;
	}
	public ArrayList<Workout> getGroupWorkoutsForTrainer(String username){
		ArrayList<Workout> retVal = new ArrayList<Workout>();
		for (Workout woIt : workouts.values()) {
			if (woIt.getWorkoutType().equals("Group")) {
				if (woIt.getTrainer() != null) {
					if (woIt.getTrainer().equals(username)) {
						retVal.add(woIt);
					}
				}
			}
		}
		return retVal;
	}
}
