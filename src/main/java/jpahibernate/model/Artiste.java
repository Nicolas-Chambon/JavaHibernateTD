package jpahibernate.model;

import javax.persistence.*;

@Entity
@Table(name = "Auteur")
public class Artiste {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String nom;
	
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
	
	@Override
	public String toString() {
		return "Nom [nom=" + nom + "]";
	}
}
