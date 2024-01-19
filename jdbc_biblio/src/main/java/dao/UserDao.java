package dao;

import java.util.ArrayList;
//import java.util.List;

import entity.User;

public interface UserDao {
	void addUser(User p);
	void updateUser(User p);
	User getUser(String username);
	ArrayList<User> getUsers();
}