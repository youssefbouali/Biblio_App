package dao;

import java.util.ArrayList;

import entity.Livre;

public interface EmpruntDao {
	void addEmprunt(String isbn, String username);
	Livre getEmprunt(String isbn, String username);
	void removeEmprunt(String isbn, String username);
	ArrayList<Livre> getEmprunts(String username);
}