package test;

//import java.util.List;

import dao.UserDao;
import dao.UserImpl;
import entity.User;

public class Start {

	public static void main(String[] args) {

		UserDao dao = new UserImpl();

		User p = new User("u1", 44, "123456");

		dao.addUser(p);

		/*List<User> Users = dao.getUsers();

		for(User User : Users) {
			System.out.println(User.toString());
		}*/

		User Users = dao.getUser("u1");
		System.out.println(Users.toString());
	}

}
