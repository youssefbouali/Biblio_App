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
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class LivreImpl implements LivreDao {
	//private Connection conn;
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;

	public LivreImpl() {

		entityManagerFactory = Persistence.createEntityManagerFactory("MyPersistenceUnit");
		entityManager = entityManagerFactory.createEntityManager();
	}

	@Override
	public void addLivre(Livre l) {

		entityManager.getTransaction().begin();
		entityManager.persist(l);
		entityManager.getTransaction().commit();

		//System.out.println("Added "+l.toString());

	}

	@Override
	public void updateLivre(Livre l) {
	    entityManager.getTransaction().begin();

	    int updatedEntities = entityManager.createQuery(
	            "UPDATE Livre l SET l.auteur = :auteur, l.titre = :titre WHERE l.isbn = :isbn")
	            .setParameter("auteur", l.getAuteur())
	            .setParameter("titre", l.getTitre())
	            .setParameter("isbn", l.getIsbn())
	            .executeUpdate();

	    if (updatedEntities == 1) {
	        entityManager.getTransaction().commit();
	    } else {
	        System.out.println("Livre with ISBN " + l.getIsbn() + " not found for update.");
	        entityManager.getTransaction().rollback();
	    }

		//System.out.println("Modified");
	}

	@Override
	public void removeLivre(String isbn) {
	    try {
	        entityManager.getTransaction().begin();

	        TypedQuery<Livre> query = entityManager.createQuery("SELECT l FROM Livre l WHERE l.isbn = :isbn", Livre.class);
	        query.setParameter("isbn", isbn);
	        Livre livre = query.getSingleResult();

	        if (livre != null) {
	            entityManager.remove(livre);
	        }

	        entityManager.getTransaction().commit();
	    } catch (Exception e) {
	        if (entityManager.getTransaction().isActive()) {
	            entityManager.getTransaction().rollback();
	        }
	    } finally {
            entityManager.close();
            entityManagerFactory.close();
        }

		//System.out.println("Removed");
	}

	@Override
	public Livre getLivre(String isbn) {

		try {
		    entityManager.getTransaction().begin();
		    TypedQuery<Livre> query = entityManager.createQuery("SELECT l FROM Livre l WHERE l.isbn = :isbn", Livre.class);
		    query.setParameter("isbn", isbn);
		    Livre livre = query.getSingleResult();
		    entityManager.getTransaction().commit();
		    return livre;
		} catch (NoResultException e) {
		    entityManager.getTransaction().rollback();
		    return null;
		}


	}

	@Override
	public ArrayList<Livre> getLivres() {
		return new ArrayList<>(entityManager.createQuery("SELECT l FROM Livre l", Livre.class).getResultList());
	}

	@Override
	public ArrayList<Livre> getLivresSearch(String q) {

		try {
		    entityManager.getTransaction().begin();
		    TypedQuery<Livre> query = entityManager.createQuery("SELECT l FROM Livre l WHERE l.titre LIKE :q OR l.auteur LIKE :q OR l.isbn LIKE :q", Livre.class);
		    query.setParameter("q", "%"+q+"%");


	        List<Livre> results = query.getResultList();
	        ArrayList<Livre> livres = new ArrayList<>(results);

		    entityManager.getTransaction().commit();
		    return livres;
		} catch (NoResultException e) {
		    entityManager.getTransaction().rollback();
		    return null;
		}
	}

	@Override
	public ArrayList<Livre> getLivresTri(String tri) {

		try {
		    entityManager.getTransaction().begin();
		    TypedQuery<Livre> query = entityManager.createQuery("SELECT l FROM Livre l Order by l."+tri+" ASC", Livre.class);

	        List<Livre> results = query.getResultList();
	        ArrayList<Livre> livres = new ArrayList<>(results);

		    entityManager.getTransaction().commit();
		    return livres;
		} catch (NoResultException e) {
		    entityManager.getTransaction().rollback();
		    return null;
		}
	}

}
