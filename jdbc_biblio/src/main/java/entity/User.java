package entity;

public class User {
	private Long id;
	private String username;
	private int age;
	private String password;
	
	public User(String username, int age, String password) {
		super();
		this.setUsername(username);
		this.setAge(age);
		this.setPassword(password);
	}

	public double getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User() {
		// TODO Auto-generated constructor stub
		super();
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", age=" + age + ", password=" + password + "]";
	}
	
	
	
}
