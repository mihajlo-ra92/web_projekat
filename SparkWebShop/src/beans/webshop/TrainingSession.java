package beans.webshop;


public class TrainingSession {
	private String id;
	private String dateTimeOfSignUp;
	private String SportObject;
	private String workoutName;
	private String buyer;
	private String trainer; //can be "" or null
	
	public TrainingSession() {
		// TODO Auto-generated constructor stub
	}
	public TrainingSession(String id, String SportObject, String dateTimeOfSignUp, String workoutName, String buyer, String trainer) {
		super();
		this.id = id;
		this.dateTimeOfSignUp = dateTimeOfSignUp;
		this.workoutName = workoutName;
		this.buyer = buyer;
		this.trainer = trainer;
		this.SportObject = SportObject;
	}
	@Override
	public String toString() {
		return "TrainingHistory [id=" + id + ", dateTimeOfSignUp=" + dateTimeOfSignUp + ", workoutName=" + workoutName
				+ ", buyer=" + buyer + ", trainer=" + trainer + "]";
	}
	public String getSportObject() {
		return SportObject;
	}
	public void setSportObject(String sportObject) {
		SportObject = sportObject;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDateTimeOfSignUp() {
		return dateTimeOfSignUp;
	}

	public void setDateTimeOfSignUp(String dateTimeOfSignUp) {
		this.dateTimeOfSignUp = dateTimeOfSignUp;
	}

	public String getWorkout() {
		return workoutName;
	}

	public void setWorkout(String workoutName) {
		this.workoutName = workoutName;
	}

	public String getWorkoutName() {
		return workoutName;
	}
	public void setWorkoutName(String workoutName) {
		this.workoutName = workoutName;
	}
	public String getTrainer() {
		return trainer;
	}
	public void setTrainer(String trainer) {
		this.trainer = trainer;
	}
	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
}
