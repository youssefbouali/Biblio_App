package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//import java.util.List;

//import com.mysql.cj.protocol.Resultset;

import contextDb.ConnexionDB;
import entity.Livre;

public class LivreImpl implements LivreDao {
	private Connection conn;

	@Override
	public void addLivre(Livre l) {
		
		conn = ConnexionDB.getConnection();
		String query = "INSERT INTO livres (id, titre, auteur, isbn) VALUES(NULL, ?, ?, ?)";
		
		try {
			PreparedStatement pr =  conn.prepareStatement(query);
			pr.setString(1, l.getTitre());
			pr.setString(2, l.getAuteur());
			pr.setString(3, l.getIsbn());
			pr.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//System.out.println("Added "+l.toString());
		
	}

	@Override
	public void updateLivre(Livre l) {
		
		conn = ConnexionDB.getConnection();
		String query = "UPDATE livres SET titre=?, auteur=?, isbn=? WHERE isbn=?";
		
		try {
			PreparedStatement pr =  conn.prepareStatement(query);
			pr.setString(1, l.getTitre());
			pr.setString(2, l.getAuteur());
			pr.setString(3, l.getIsbn());
			pr.setString(4, l.getIsbn());
			pr.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//System.out.println("Modified");
	}

	@Override
	public void removeLivre(String isbn) {

		conn = ConnexionDB.getConnection();
		String query = "DELETE FROM livres WHERE isbn=?";
		
		try {
			PreparedStatement pr =  conn.prepareStatement(query);
			pr.setString(1, isbn);
			pr.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//System.out.println("Removed");
	}
	
	@Override
	public Livre getLivre(String isbn) {

		conn = ConnexionDB.getConnection();
		Livre Livre = new Livre();
		String query = "SELECT * FROM livres WHERE isbn=? ";
		
		try {
			
			PreparedStatement pr = conn.prepareStatement(query);
	        pr.setString(1, isbn);

	        ResultSet rs = pr.executeQuery();

	        if (rs.next()) {
	            //Livre = new Livre(rs);
	            Livre.setTitre(rs.getString("titre"));
	            Livre.setAuteur(rs.getString("auteur"));
	            Livre.setIsbn(rs.getString("isbn"));
	        }
			
			/*PreparedStatement pr = conn.prepareStatement(query);
			ResultSet rs = pr.executeQuery();
			
			while (rs.next()) {
				Livre p = new Livre();
				p.setNom(rs.getString("nom"));
				p.setAge(rs.getInt("age"));
				p.setPassword(rs.getString("password"));
				
				Livre.add(p);
				
			}*/
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Livre;
	}

	@Override
	public ArrayList<Livre> getLivres() {

		conn = ConnexionDB.getConnection();
		ArrayList<Livre> Livres = new ArrayList<Livre>();
		String query = "SELECT * FROM livres";
		
		try {
			
			PreparedStatement pr = conn.prepareStatement(query);
			ResultSet rs = pr.executeQuery();
			
			while (rs.next()) {
				Livre l = new Livre();
				l.setTitre(rs.getString("titre"));
				l.setAuteur(rs.getString("auteur"));
				l.setIsbn(rs.getString("isbn"));
				
				Livres.add(l);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Livres;
	}

	@Override
	public ArrayList<Livre> getLivresSearch(String q) {

		conn = ConnexionDB.getConnection();
		ArrayList<Livre> Livres = new ArrayList<Livre>();
		String query = "SELECT * FROM livres WHERE titre LIKE ? OR auteur LIKE ? OR isbn LIKE ?";
		
		try {
			
			PreparedStatement pr = conn.prepareStatement(query);
	        pr.setString(1, "%"+q+"%");
	        pr.setString(2, "%"+q+"%");
	        pr.setString(3, "%"+q+"%");

	        ResultSet rs = pr.executeQuery();
			
			while (rs.next()) {
				Livre l = new Livre();
				l.setTitre(rs.getString("titre"));
				l.setAuteur(rs.getString("auteur"));
				l.setIsbn(rs.getString("isbn"));
				
				Livres.add(l);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Livres;
	}

	@Override
	public ArrayList<Livre> getLivresTri(String tri) {

		conn = ConnexionDB.getConnection();
		ArrayList<Livre> Livres = new ArrayList<Livre>();
		String query = "SELECT * FROM livres Order by "+tri+" ASC";
		
		try {
			
			PreparedStatement pr = conn.prepareStatement(query);
	        
			ResultSet rs = pr.executeQuery();
			
			while (rs.next()) {
				Livre l = new Livre();
				l.setTitre(rs.getString("titre"));
				l.setAuteur(rs.getString("auteur"));
				l.setIsbn(rs.getString("isbn"));
				
				Livres.add(l);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Livres;
	}
	
}
