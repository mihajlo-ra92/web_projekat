package beans.webshop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class TrainingHistoryDAO {
	private HashMap<String, TrainingSession> trainingHistory = new HashMap<String, TrainingSession>();
	private String path;
	private static Gson g = new Gson();
	private static final java.lang.reflect.Type TRAININGHISTORYS_TYPE = new TypeToken<ArrayList<TrainingSession>>() {
	}.getType();
	public TrainingHistoryDAO() {
		this(".");
	}
	public TrainingHistoryDAO(String path) {
		this.path = path;
		
		try {
			File file = new File(path + "/resources/JSON/trainingHistory.json");
			System.out.println(file.getCanonicalPath());
			JsonReader reader = new JsonReader(new FileReader(file));
			ArrayList<TrainingSession> trainingSessionList = g.fromJson(reader, TRAININGHISTORYS_TYPE);
			if (trainingSessionList != null) {
				for (TrainingSession sessionIt : trainingSessionList) {
					trainingHistory.put(sessionIt.getId(), sessionIt);
				}
//				System.out.println(trainingHistory);
			}
			
//			TrainingSession ts1 = new TrainingSession("1", "2022-05-30", "VrstaTreninga2", "2", "");
//			addTrainingHistoty(ts1);
//			TrainingSession ts2 = new TrainingSession("2", "2022-04-28", "VrstaTreninga1", "3", "6");
//			addTrainingHistoty(ts2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void addTrainingSessionRequest(Workout workout, String username) {
		String id = Integer.toString(trainingHistory.size()+1);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		LocalDateTime now = LocalDateTime.now(); 
		TrainingSession trainingSession = new TrainingSession(id, workout.getSportObject(),
				dtf.format(now), workout.getName(), username, workout.getTrainer()
				);
		try {			
			addTrainingHistoty(trainingSession);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void addTrainingHistoty(TrainingSession trainingSession) throws FileNotFoundException {
		trainingHistory.put(trainingSession.getId(), trainingSession);
		toJSON(path + "/resources/JSON/trainingHistory.json");	
	}
	
	public ArrayList<TrainingSession> getTrSessionsGroupFromTrainer(String trUsername){
		ArrayList<TrainingSession> trainersTrSess = new ArrayList<TrainingSession>();
		ArrayList<TrainingSession> retVal= new ArrayList<TrainingSession>();
		//punjenje trenera
		for(TrainingSession trIt : trainingHistory.values()) {
			if(trIt.getTrainer().equals(trUsername) ) {
				trainersTrSess.add(trIt);
			}
		}
		//punjenje group treninga
		for(TrainingSession trIt : trainersTrSess) {
			int counter = 0;
			for(TrainingSession trIt1 : trainersTrSess) {
				if(!trIt.getId().equals(trIt1.getId()) &&
					trIt.getDateTimeOfSignUp().equals(trIt1.getDateTimeOfSignUp()) &&
					trIt.getWorkout().equals(trIt1.getWorkout())) {
					counter++;
				}
			}
			if(counter > 0) {
				retVal.add(trIt);
			}
		}
		//izbacivanje duplih
		if(retVal.size() != 0) {
			for(TrainingSession trIt : retVal) {
				for(TrainingSession trIt1 : retVal) {
					if(trIt.getDateTimeOfSignUp().equals(trIt1.getDateTimeOfSignUp())) {
						retVal.remove(trIt1);
					}
				}
			}
		return retVal;
		}else {
			return null;
		}
	}
	public ArrayList<String> getVisitedNames(String username){
		ArrayList<String> names = new ArrayList<String>();
		for(TrainingSession tsIt : trainingHistory.values()) {
			if (tsIt.getBuyer().equals(username)) {
				if (!names.contains(tsIt.getSportObject())) {
					names.add(tsIt.getSportObject());
				}
			}
		}
		return names;
	}
	
	public ArrayList<TrainingSession> getTrainingsbySportObjectName(String SOName){
		ArrayList<TrainingSession> retVal= new ArrayList<TrainingSession>();
		for(TrainingSession trIt : trainingHistory.values()) {
			if(trIt.getSportObject().equals(SOName)) {				
				retVal.add(trIt);
			}
		}
		if(retVal.size() == 0) {
			return null;
		}else {			
			return retVal;
		}
	}
	
	public void deleteTrainingById(String req) {
		TrainingSession tr = g.fromJson(req, TrainingSession.class);
		System.out.println(tr);
		trainingHistory.remove(tr.getId());			
	}
	
	public ArrayList<TrainingSession> getTrSessionsPersonalFromTrainer(String trUsername) {
		ArrayList<TrainingSession> trainersTrSess = new ArrayList<TrainingSession>();
		ArrayList<TrainingSession> retVal= new ArrayList<TrainingSession>();
		for(TrainingSession trIt : trainingHistory.values()) {
			if(trIt.getTrainer().equals(trUsername) ) {
				trainersTrSess.add(trIt);
			}
		}
		for(TrainingSession trIt : trainersTrSess) {
			int counter = 0;
			for(TrainingSession trIt1 : trainersTrSess) {
				if(!trIt.getId().equals(trIt1.getId()) && trIt.getDateTimeOfSignUp().equals(trIt1.getDateTimeOfSignUp())) {
					counter++;
				}
			}
			if(counter == 0) {
				retVal.add(trIt);
			}
		}
		return retVal;
	}
	
	private void toJSON(String filename) throws FileNotFoundException {
		PrintWriter out = new PrintWriter(filename);
		out.printf(g.toJson(trainingHistory.values()));
		out.close();
	}
	public Collection<TrainingSession> values() {
		return trainingHistory.values();
	}
}
