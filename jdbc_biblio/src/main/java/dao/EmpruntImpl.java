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

public class EmpruntImpl implements EmpruntDao {
	private Connection conn;

	@Override
	public void addEmprunt(String isbn, String username) {
		
		conn = ConnexionDB.getConnection();
		String query = "INSERT INTO emprunts (id, username, isbn, date) VALUES(NULL, ?, ?, ?)";
		
		try {
			PreparedStatement pr =  conn.prepareStatement(query);
			pr.setString(1, username);
			pr.setString(2, isbn);
			pr.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
			pr.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//System.out.println("Added "+username+" "+isbn+" "+java.sql.Date.valueOf(java.time.LocalDate.now()));
		
	}

	@Override
	public void removeEmprunt(String isbn, String username) {

		conn = ConnexionDB.getConnection();
		String query = "DELETE FROM emprunts WHERE username=? AND isbn=?";
		
		try {
			PreparedStatement pr =  conn.prepareStatement(query);
			pr.setString(1, username);
			pr.setString(2, isbn);
			pr.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//System.out.println("Removed");
	}
	
	@Override
	public Livre getEmprunt(String isbn, String username) {

		conn = ConnexionDB.getConnection();
		Livre Livre = new Livre();
		String query = "SELECT livres.titre, livres.auteur, livres.isbn FROM emprunts "
				+ "INNER JOIN livres ON emprunts.isbn=livres.isbn "
				+ "WHERE emprunts.username=? AND emprunts.isbn=?";
		
		try {
			
			PreparedStatement pr = conn.prepareStatement(query);
	        pr.setString(1, username);
	        pr.setString(2, isbn);

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
	public ArrayList<Livre> getEmprunts(String username) {

		conn = ConnexionDB.getConnection();
		ArrayList<Livre> Livres = new ArrayList<Livre>();
		String query = "SELECT livres.titre, livres.auteur, livres.isbn FROM emprunts "
				+ "INNER JOIN livres ON emprunts.isbn=livres.isbn "
				+ "WHERE emprunts.username=?";
		
		try {
			
			PreparedStatement pr = conn.prepareStatement(query);
	        pr.setString(1, username);
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
