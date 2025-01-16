package model;

public class Member {
	private int id;
	private String userID;
	private String password;
	private String email;
	//디폴트 생성자
	public Member() {};
	//생성자 오버로딩
	public Member (String userID, String password, String email) {
		this.userID = userID;
		this.password = password;
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserID() {
		return userID;
	}
	public void setUsername(String userID) {
		this.userID = userID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
