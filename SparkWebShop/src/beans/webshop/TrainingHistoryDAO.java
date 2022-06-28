package beans.webshop;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TrainingHistoryDAO {
	private HashMap<String, TrainingHistory> trainingHistorys = new HashMap<String, TrainingHistory>();
	private String path;
	private static Gson g = new Gson();
	private static final java.lang.reflect.Type TRAININGHISTORYS_TYPE = new TypeToken<ArrayList<TrainingHistory>>() {
	}.getType();
	public TrainingHistoryDAO() {
		this(".");
	}
	public TrainingHistoryDAO(String path) {
		this.path = path;
		
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
