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
			for (TrainingSession sessionIt : trainingSessionList) {
				trainingHistory.put(sessionIt.getId(), sessionIt);
			}
			System.out.println(trainingHistory);
//			TrainingSession ts1 = new TrainingSession("1", "2022-05-30", "VrstaTreninga2", "2", "");
//			addTrainingHistoty(ts1);
//			TrainingSession ts2 = new TrainingSession("2", "2022-04-28", "VrstaTreninga1", "3", "6");
//			addTrainingHistoty(ts2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void addTrainingHistoty(TrainingSession trainingSession) throws FileNotFoundException {
		trainingHistory.put(trainingSession.getId(), trainingSession);
		toJSON(path + "/resources/JSON/trainingHistory.json");
		
	}
	private void toJSON(String filename) throws FileNotFoundException {
		PrintWriter out = new PrintWriter(filename);
		out.printf(g.toJson(trainingHistory.values()));
		out.close();
	}
}
