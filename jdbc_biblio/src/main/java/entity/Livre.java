package entity;

public class Livre {
	private Long id;
	private String titre;
	private String auteur;
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

	public double getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
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

	@Override
	public String toString() {
		return "Livre [titre=" + titre + ", auteur=" + auteur + ", isbn=" + isbn + "]";
	}
	
	
	
}
