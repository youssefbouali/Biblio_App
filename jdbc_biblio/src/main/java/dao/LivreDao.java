package dao;

import java.util.ArrayList;
//import java.util.List;

import entity.Livre;

public interface LivreDao {
	void addLivre(Livre l);
	void updateLivre(Livre l);
	Livre getLivre(String isbn);
	void removeLivre(String isbn);
	ArrayList<Livre> getLivres();
	ArrayList<Livre> getLivresSearch(String q);
	ArrayList<Livre> getLivresTri(String tri);
}