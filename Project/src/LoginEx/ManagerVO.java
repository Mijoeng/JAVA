package LoginEx;

public class ManagerVO {

	private String id;
	private String password;
	public ManagerVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ManagerVO(String id, String password) {
		super();
		this.id = id;
		this.password = password;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "ManagerVO [id=" + id + ", password=" + password + "]";
	}
}
