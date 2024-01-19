package dao;
/*
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;*/
import java.util.ArrayList;
//import java.util.List;

//import contextDb.ConnexionDB;
import entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class UserImpl implements UserDao {
	//private Connection conn;
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;

	public UserImpl() {

		entityManagerFactory = Persistence.createEntityManagerFactory("MyPersistenceUnit");
		entityManager = entityManagerFactory.createEntityManager();
	}

	@Override
	public void addUser(User u) {

		entityManager.getTransaction().begin();
		entityManager.persist(u);
		entityManager.getTransaction().commit();

		//System.out.println("Added");

	}

	@Override
	public void updateUser(User u) {

	}

	@Override
	public User getUser(String username) {
		try {
	        entityManager.getTransaction().begin();
	        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
	        query.setParameter("username", username);
	        User user = query.getSingleResult();
	        entityManager.getTransaction().commit();
	        return user;
		} catch (NoResultException e) {
		    entityManager.getTransaction().rollback();
		    return null;
		}

	}


	@Override
	public ArrayList<User> getUsers() {
		return new ArrayList<>(entityManager.createQuery("SELECT u FROM User u", User.class).getResultList());
	}

	public void close() {
		entityManager.close();
		entityManagerFactory.close();
	}

}
