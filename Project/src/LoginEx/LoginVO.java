package LoginEx;

public class LoginVO {
	
	
	private String uid;
	private String pw;
	private String name;

	public LoginVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoginVO(String uid, String pw, String name) {
		super();
		
		this.uid = uid;
		this.pw = pw;
		this.name = name;
	}

	

	

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "BoardVO [id=" + uid + ", pw=" + pw + ", name=" + name + "]";
	}

}
