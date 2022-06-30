package beans.webshop;


public class Workout {
	private String id;
	private String name;
	private String workoutType;
	private String sportObject;
	private String workoutDuration; //in minutes
	private String description;
	private String trainer;//can be empty or null
	//slika ??
	public Workout() {
		// TODO Auto-generated constructor stub
	}
	public Workout(String id, String name, String workoutType, String workoutDuration, String description) {
		super();
		this.id = id;
		this.name = name;
		this.workoutType = workoutType;
		this.workoutDuration = workoutDuration;
		this.description = description;
	}
	public Workout(String id, String name, String workoutType, String sportObject, String workoutDuration, String description,
			String trainer) {
		super();
		this.id = id;
		this.name = name;
		this.workoutType = workoutType;
		this.sportObject = sportObject;
		this.workoutDuration = workoutDuration;
		this.description = description;
		this.trainer = trainer;
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
		return sportObject;
	}
	public void setSportObject(String sportObject) {
		this.sportObject = sportObject;
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
	public String getTrainer() {
		return trainer;
	}
	public void setTrainer(String trainer) {
		this.trainer = trainer;
	}
	@Override
	public String toString() {
		return "Workout [id=" + id + ", name=" + name + ", workoutType=" + workoutType + ", sportObject=" + sportObject
				+ ", workoutDuration=" + workoutDuration + ", description=" + description + ", trainer=" + trainer
				+ "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
