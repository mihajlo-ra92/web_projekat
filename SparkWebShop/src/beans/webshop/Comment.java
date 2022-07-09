package beans.webshop;

public class Comment {
	private String id;
	private String buyer;
	private String sportObject;
	private String content;
	private int grade; //from 1 to 5
	private String status;
	public Comment() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", buyer=" + buyer + ", sportObject=" + sportObject + ", content=" + content
				+ ", grade=" + grade + ", status=" + status + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	public String getSportObject() {
		return sportObject;
	}
	public void setSportObject(String sportObject) {
		this.sportObject = sportObject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
