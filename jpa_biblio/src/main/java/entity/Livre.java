package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "livres")
public class Livre {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titre")
	private String titre;

    @Column(name = "auteur")
	private String auteur;

    @Column(name = "isbn")
	private String isbn;

	public Livre(String titre, String auteur, String isbn) {
		super();
		this.setTitre(titre);
		this.setAuteur(auteur);
		this.setIsbn(isbn);
	}

	public Livre() {
		super();
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	@Override
	public String toString() {
		return "Livre [titre=" + titre + ", auteur=" + auteur + ", isbn=" + isbn + "]";
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}



}
