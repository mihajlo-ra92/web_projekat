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

public class CommentDAO {
	private HashMap<String, Comment> comments = new HashMap<String, Comment>();
	private String path;
	private static Gson g = new Gson();
	private static final java.lang.reflect.Type COMMENTS_TYPE = new TypeToken<ArrayList<Comment>>() {
	}.getType();
	
	public CommentDAO() {
		this(".");
	}
	public CommentDAO(String path) {
		this.path = path;
		try {
			File file = new File(path + "/resources/JSON/comments.json");
			System.out.println(file.getCanonicalPath());
			JsonReader reader = new JsonReader(new FileReader(file));
			ArrayList<Comment> commentsList = g.fromJson(reader, COMMENTS_TYPE);
			
			for (Comment commIt : commentsList) {
				comments.put(commIt.getId(), commIt);
			}
			System.out.println("COMMENTS: " + comments);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String submitComment(Comment comment) throws FileNotFoundException {
		if (comment.getGrade() == 0) {
			return "Grade must be selected!";
		}
		String id = Integer.toString(comments.size() + 1);
		comment.setId(id);
		comments.put(id, comment);
		toJSON(path + "/resources/JSON/comments.json");
		
		return "Your comment has been selected for review";
	}
	public void updateComment(Comment comment) throws FileNotFoundException {
		comments.put(comment.getId(), comment);
		toJSON(path + "/resources/JSON/comments.json");
	}
	private void toJSON(String filename) throws FileNotFoundException {
		PrintWriter out = new PrintWriter(filename);
		//System.out.println(filename);
		out.printf(g.toJson(comments.values()));
		out.close();
	}
	public ArrayList<Comment> getWaiting() {
		ArrayList<Comment> retList = new ArrayList<Comment>();
		for(Comment comIt : comments.values()) {
			if(comIt.getStatus().equals("WAITING")) {
				retList.add(comIt);
			}
		}
		return retList;
	}
	public ArrayList<Comment> getApprovedForObject(String objectName){
		ArrayList<Comment> retList = new ArrayList<Comment>();
		for(Comment comIt : comments.values()) {
			if(comIt.getStatus().equals("APPROVED")) {
				if(comIt.getSportObject().equals(objectName)) {					
					retList.add(comIt);
				}
			}
		}
		return retList;
	}

}
