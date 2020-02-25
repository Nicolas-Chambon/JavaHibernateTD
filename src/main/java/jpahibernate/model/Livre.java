package jpahibernate.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Livre {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "nom")
	private String nom;

	@ManyToMany
	@JoinTable(name = "auteur",
			joinColumns = { @JoinColumn(name = "livre_id") },
			inverseJoinColumns = { @JoinColumn(name = "auteur_id") })
	private List<Auteur> auteurs;


	public Livre() {}

	public Livre(int number, String nom, int healthPoints) {
		super();
		this.nom = nom;
	}

	public Livre(int number, String nom, int healthPoints, List<Auteur> auteurs) {
		super();

		this.nom = nom;
		this.auteurs = auteurs;
	}


	
	public void addType(Auteur auteur) {
		if (auteurs == null) {
			auteurs = new ArrayList<>();
		}
		auteurs.add(auteur);
	}
	
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


	public List<Auteur> getAuteurs() {
		return auteurs;
	}
	public void setAuteurs(List<Auteur> auteurs) {
		this.auteurs = auteurs;
	}

	
	@Override
	public String toString() {
		return "livre [Nom=" + nom + ", auteurs=" + auteurs;
	}
}
