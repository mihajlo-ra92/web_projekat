package beans.webshop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import enums.MembershipType;

public class MembershipDAO {
	private HashMap<String, Membership> memberships = new HashMap<String, Membership>();
	private String path;
	private static Gson g = new Gson();
	private static final java.lang.reflect.Type MEMBERSHIPS_TYPE = new TypeToken<ArrayList<Membership>>() {
	}.getType();
	public MembershipDAO() {
		this(".");
	}
	public MembershipDAO(String path) {
		this.path = path;
		
		try {
			File file = new File(path + "/resources/JSON/memberships.json");
			System.out.println(file.getCanonicalPath());
			JsonReader reader = new JsonReader(new FileReader(file));
			ArrayList<Membership> membershipList = g.fromJson(reader, MEMBERSHIPS_TYPE);
			if (membershipList != null) {
				for (Membership membershipIt : membershipList) {
					memberships.put(membershipIt.getId(), membershipIt);
				}
				updateActive();
//				System.out.println(memberships);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String getNewId() {
		int largest = -1;
		for (Membership membershipIt : memberships.values()) {
			if (Integer.parseInt(membershipIt.getId()) > largest) {
				largest = Integer.parseInt(membershipIt.getId());
			}
		}
		return Integer.toString(largest + 1);
	}
	
	public void addMembership(Membership membership) throws FileNotFoundException {
		memberships.put(membership.getId(), membership);
		toJSON(path + "/resources/JSON/memberships.json");
	}
	public Boolean addMembershipRequest(String body) {
		System.out.println(body);
		String [] split = body.split(";");
		String id = getNewId();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
		LocalDateTime oneMonth = LocalDateTime.now().plusMonths(1);
		LocalDateTime oneYear= LocalDateTime.now().plusYears(1);
		System.out.println("CURRENT DATE: " + dtf.format(now)); 
		try {
			switch(split[0]) {
			  case "DAILY":
				  Membership memDay = new Membership(id, MembershipType.DAILY, dtf.format(now),
			    		dtf.format(now), dtf.format(tomorrow), 500, true, 2, split[1]);
			      addMembership(memDay);
			  break;
			  case "MONTHLY":
				  Membership memMonth = new Membership(id, MembershipType.MONTHLY, dtf.format(now),
					  dtf.format(now), dtf.format(oneMonth), 2500, true, 3, split[1]);
			      addMembership(memMonth);
			  break;
			  case "YEARLY":
				  Membership memYear= new Membership(id, MembershipType.YEARLY, dtf.format(now),
					  dtf.format(now), dtf.format(oneYear), 10000, true, 5, split[1]);
			      addMembership(memYear);
			  break;
			  default:
			    // code block
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}
	private void toJSON(String filename) throws FileNotFoundException {
		PrintWriter out = new PrintWriter(filename);
		out.printf(g.toJson(memberships.values()));
		out.close();
	}
	public void updateActive() throws ParseException, FileNotFoundException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String timeStamp = dateFormat.format(Calendar.getInstance().getTime());
		for(Membership mem : memberships.values()) {
			String start = mem.getStartDate();
			String end = mem.getEndDate();
			if (dateFormat.parse(timeStamp).after(dateFormat.parse(start)) &&
					dateFormat.parse(end).after(dateFormat.parse(timeStamp))) {
				mem.setActive(true);
				System.out.println("MEM ID: " + mem.getId() + "is active");
			}
			else {
				mem.setActive(false);
				System.out.println("MEM ID: " + mem.getId() + "is NOT active");
			}
		}
		toJSON(path + "/resources/JSON/memberships.json");
	}
	public Boolean checkMembership(User user) {
		for(Membership mem : memberships.values()) {
			if (mem.getBuyer().equals(user.getUsername())) {
				if (mem.isActive()) {
					return true;
				}
			}
		}
		return false;
	}
	public Membership getMembership(String username) {
		for(Membership mem : memberships.values()) {
			if (mem.getBuyer().equals(username)) {
				if (mem.isActive()) {
					return mem;
				}
			}
		}
		return null;
	}
	public Boolean logWorkout(User user) throws FileNotFoundException {
		for(Membership mem : memberships.values()) {
			if (mem.getBuyer().equals(user.getUsername()) &&
				mem.isActive()) {
				if (mem.getNumberOfUsedWorkouts() < mem.getNumberOfWorkouts()) {
					mem.incrementNumberOfUsedWorkouts();
					toJSON(path + "/resources/JSON/memberships.json");
					return true;
				}
			}
		}
		return false;
	}

}
