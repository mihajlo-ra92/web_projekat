package beans.webshop;


public class Workout {
	
	private String name;
	private String workoutType;
	private String sportObjectId;
	private String workoutDuration; //in minutes
	private String description;
	private String trainerId;
	//slika ??
	public Workout() {
		// TODO Auto-generated constructor stub
	}
	public Workout(String name, String workoutType, String sportObjectId, String workoutDuration, String description,
			String trainerId) {
		super();
		this.name = name;
		this.workoutType = workoutType;
		this.sportObjectId = sportObjectId;
		this.workoutDuration = workoutDuration;
		this.description = description;
		this.trainerId = trainerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWorkoutType() {
		return workoutType;
	}
	public void setWorkoutType(String workoutType) {
		this.workoutType = workoutType;
	}
	public String getSportObject() {
		return sportObjectId;
	}
	public void setSportObject(String sportObjectId) {
		this.sportObjectId = sportObjectId;
	}
	public String getWorkoutDuration() {
		return workoutDuration;
	}
	public void setWorkoutDuration(String workoutDuration) {
		this.workoutDuration = workoutDuration;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTrainerId() {
		return trainerId;
	}
	public void setTrainerId(String trainerId) {
		this.trainerId = trainerId;
	}
	@Override
	public String toString() {
		return "Workout [name=" + name + ", workoutType=" + workoutType + ", sportObjectId=" + sportObjectId
				+ ", workoutDuration=" + workoutDuration + ", description=" + description + ", trainerId=" + trainerId
				+ "]";
	}

}
