package jpahibernate.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity

public class CD extends Article {

	@Column(name = "nom")
	private String nom;

	@ManyToMany
	@JoinTable(name = "artiste_cd",
			joinColumns = { @JoinColumn(name = "cd_id") },
			inverseJoinColumns = { @JoinColumn(name = "artiste_id") })
	private List<Artiste> artistes;

	@ManyToMany
	@JoinTable(name = "StyleMusical",
			joinColumns = { @JoinColumn(name = "cd_id") },
			inverseJoinColumns = { @JoinColumn(name = "styleMusical_id") })
	private List<StyleMusical> styles;


	public CD() {}

	public CD(String nom) {
		super();
		this.nom = nom;
	}

	public CD(String nom, List<Artiste> artistes) {
		super();

		this.nom = nom;
		this.artistes = artistes;
	}


	
	public void addType(Artiste artiste) {
		if (artistes == null) {
			artistes = new ArrayList<>();
		}
		artistes.add(artiste);
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


	public List<Artiste> getartistes() {
		return artistes;
	}
	public void setartistes(List<Artiste> artistes) {
		this.artistes = artistes;
	}

	public List<StyleMusical> getStyles() {
		return styles;
	}
	public void setStyles(List<StyleMusical> styles) {
		this.styles = styles;
	}

	
	@Override
	public String toString() {
		return "livre [Nom=" + nom + ", artistess=" + artistes;
	}
}
