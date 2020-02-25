package jpahibernate.model;

public class Dresseur {
	private long id;
	private String nom;
	private String ville;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	
	@Override
	public String toString() {
		return "Dresseur [nom=" + nom + ", ville=" + ville + "]";
	}
}
