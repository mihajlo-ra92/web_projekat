package beans.webshop;


public class TrainingHistory {
	private String id;
	private String dateTimeOfSignUp;
	private Workout workout;
	private String buyerId;
	
	public TrainingHistory() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "TrainingHistory [id=" + id + ", dateTimeOfSignUp=" + dateTimeOfSignUp + ", workout=" + workout
				+ ", buyerId=" + buyerId + "]";
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

	public Workout getWorkout() {
		return workout;
	}

	public void setWorkout(Workout workout) {
		this.workout = workout;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
}
