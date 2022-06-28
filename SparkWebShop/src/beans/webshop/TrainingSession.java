package beans.webshop;


public class TrainingSession {
	private String id;
	private String dateTimeOfSignUp;
	private String workoutName;
	private String buyerId;
	private String trainerId; //can be "" or null
	
	public TrainingSession() {
		// TODO Auto-generated constructor stub
	}
	public TrainingSession(String id, String dateTimeOfSignUp, String workoutName, String buyerId, String trainerId) {
		super();
		this.id = id;
		this.dateTimeOfSignUp = dateTimeOfSignUp;
		this.workoutName = workoutName;
		this.buyerId = buyerId;
		this.trainerId = trainerId;
	}
	@Override
	public String toString() {
		return "TrainingHistory [id=" + id + ", dateTimeOfSignUp=" + dateTimeOfSignUp + ", workoutName=" + workoutName
				+ ", buyerId=" + buyerId + ", trainerId=" + trainerId + "]";
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

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
}
