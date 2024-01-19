package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
	private String username;

	@Column(name = "age")
	private int age;

	@Column(name = "password")
	private String password;

	public User(String username, int age, String password) {
		super();
		this.setUsername(username);
		this.setAge(age);
		this.setPassword(password);
	}

	public User() {
		// TODO Auto-generated constructor stub
		super();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	@Override
	public String toString() {
		return "User [username=" + username + ", age=" + age + ", password=" + password + "]";
	}



}
