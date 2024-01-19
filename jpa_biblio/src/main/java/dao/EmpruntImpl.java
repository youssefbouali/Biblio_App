package dao;

//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
import java.util.ArrayList;
//import java.util.List;
import java.util.List;

//import com.mysql.cj.protocol.Resultset;

//import contextDb.ConnexionDB;
import entity.Livre;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class EmpruntImpl implements EmpruntDao {
	//private Connection conn;
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;

	public EmpruntImpl() {

		entityManagerFactory = Persistence.createEntityManagerFactory("MyPersistenceUnit");
		entityManager = entityManagerFactory.createEntityManager();
	}

	@Override
	public void addEmprunt(String isbn, String username) {
	    try {
	    	EntityManager entityManager = entityManagerFactory.createEntityManager();
	        EntityTransaction transaction = entityManager.getTransaction();

	        try {
	            transaction.begin();

	            Query query = entityManager.createNativeQuery("INSERT INTO emprunts (id, username, isbn, date) VALUES(NULL, ?, ?, ?)");
	            query.setParameter(1, username);
	            query.setParameter(2, isbn);
	            query.setParameter(3, java.sql.Date.valueOf(java.time.LocalDate.now()));

	            query.executeUpdate();
	            transaction.commit();

	        } catch (Exception e) {
	            if (transaction.isActive()) {
	                transaction.rollback();
	            }
	            e.printStackTrace();

	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

		//System.out.println("Added "+username+" "+isbn+" "+java.sql.Date.valueOf(java.time.LocalDate.now()));

	}

	@Override
	public void removeEmprunt(String isbn, String username) {
		try {
	    	EntityManager entityManager = entityManagerFactory.createEntityManager();
	        EntityTransaction transaction = entityManager.getTransaction();

	        try {
	            transaction.begin();

	            Query query = entityManager.createNativeQuery("DELETE FROM emprunts WHERE username=? AND isbn=?");
	            query.setParameter(1, username);
	            query.setParameter(2, isbn);

	            query.executeUpdate();
	            transaction.commit();

	        } catch (Exception e) {
	            if (transaction.isActive()) {
	                transaction.rollback();
	            }
	            e.printStackTrace();

	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }


		//System.out.println("Removed");
	}

	@Override
	public Livre getEmprunt(String isbn, String username) {

		try {
	        entityManager.getTransaction().begin();

	        Query query = entityManager.createNativeQuery(
	        		"SELECT livres.titre, livres.auteur, livres.isbn FROM emprunts "
	    		    		+ "		INNER JOIN livres ON emprunts.isbn=livres.isbn "
	    		    		+ "		WHERE emprunts.username=? AND emprunts.isbn=?");

	        query.setParameter(1, username);
	        query.setParameter(2, isbn);

	        Object[] result = (Object[]) query.getSingleResult();

	            String titre = (String) result[0];
	            String auteur = (String) result[1];
	            String isbn1 = (String) result[2];

	            Livre livre = new Livre();
	            livre.setTitre(titre);
	            livre.setAuteur(auteur);
	            livre.setIsbn(isbn1);


	        entityManager.getTransaction().commit();
	        return livre;
	    } catch (NoResultException e) {
	        if (entityManager.getTransaction().isActive()) {
	            entityManager.getTransaction().rollback();
	        }
	        return null;
	    } finally {
	        entityManager.close();
	    }

	}

	@Override
	public ArrayList<Livre> getEmprunts(String username) {
	    try {
	        entityManager.getTransaction().begin();

	        Query query = entityManager.createNativeQuery(
	                "SELECT livres.titre, livres.auteur, livres.isbn FROM emprunts "
	                + "INNER JOIN livres ON emprunts.isbn = livres.isbn "
	                + "WHERE emprunts.username = ?");

	        query.setParameter(1, username);

	        List<Object[]> results = query.getResultList();
	        ArrayList<Livre> livres = new ArrayList<>();

	        for (Object[] result : results) {
	            String titre = (String) result[0];
	            String auteur = (String) result[1];
	            String isbn = (String) result[2];

	            Livre livre = new Livre();
	            livre.setTitre(titre);
	            livre.setAuteur(auteur);
	            livre.setIsbn(isbn);

	            livres.add(livre);
	        }

	        entityManager.getTransaction().commit();
	        return livres;
	    } catch (NoResultException e) {
	        if (entityManager.getTransaction().isActive()) {
	            entityManager.getTransaction().rollback();
	        }
	        return new ArrayList<>();
	    } finally {
	        entityManager.close();
	    }


	}

}
