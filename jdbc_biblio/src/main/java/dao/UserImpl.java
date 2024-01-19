package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//import java.util.List;


import contextDb.ConnexionDB;
import entity.User;

public class UserImpl implements UserDao {
	private Connection conn;

	@Override
	public void addUser(User u) {
		
		conn = ConnexionDB.getConnection();
		String query = "INSERT INTO users (id, username, age, password) VALUES(NULL, ?, ?, ?)";
		
		try {
			PreparedStatement pr =  conn.prepareStatement(query);
			pr.setString(1, u.getUsername());
			pr.setInt(2, u.getAge());
			pr.setString(3, u.getPassword());
			pr.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//System.out.println("Added");
		
	}

	@Override
	public void updateUser(User u) {
		
	}

	@Override
	public User getUser(String username) {

		conn = ConnexionDB.getConnection();
		User User = new User();
		String query = "SELECT * FROM users WHERE username=? ";
		
		try {
			
			PreparedStatement pr = conn.prepareStatement(query);
	        pr.setString(1, username);

	        ResultSet rs = pr.executeQuery();

	        if (rs.next()) {
	            //User = new User(rs);
	            User.setUsername(rs.getString("username"));
	            User.setAge(rs.getInt("age"));
	            User.setPassword(rs.getString("password"));
	        }
			
			/*PreparedStatement pr = conn.prepareStatement(query);
			ResultSet rs = pr.executeQuery();
			
			while (rs.next()) {
				User p = new User();
				p.setNom(rs.getString("nom"));
				p.setAge(rs.getInt("age"));
				p.setPassword(rs.getString("password"));
				
				User.add(p);
				
			}*/
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return User;
	}

	@Override
	public ArrayList<User> getUsers() {

		conn = ConnexionDB.getConnection();
		ArrayList<User> Users = new ArrayList<User>();
		String query = "SELECT * FROM users";
		
		try {
			
			PreparedStatement pr = conn.prepareStatement(query);
			ResultSet rs = pr.executeQuery();
			
			while (rs.next()) {
				User u = new User();
				u.setUsername(rs.getString("username"));
				u.setAge(rs.getInt("age"));
				u.setPassword(rs.getString("password"));
				
				Users.add(u);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Users;
	}
	
}
