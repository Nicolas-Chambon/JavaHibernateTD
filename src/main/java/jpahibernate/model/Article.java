package jpahibernate.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;

	@Column(name = "number_", unique = true)
	private int number;

	@Column(name = "name_")
	private String name;

	@Column(name = "health_points")
	private int healthPoints;

	@ManyToMany
	@JoinTable(name = "pokemon_est_de_type",
		joinColumns = { @JoinColumn(name = "pokemon_id") },
	    inverseJoinColumns = { @JoinColumn(name = "type_id") })
	private List<PokemonType> types;

	@OneToOne
	@JoinColumn(name = "evolution")
	private Article evolution;

	public Article() {}

	public Article(int number, String name, int healthPoints) {
		super();
		this.number = number;
		this.name = name;
		this.healthPoints = healthPoints;;
	}

	public Article(int number, String name, int healthPoints, List<PokemonType> types) {
		super();
		this.number = number;
		this.name = name;
		this.healthPoints = healthPoints;
		this.types = types;
	}

	public Article(int number, String name, int healthPoints, Article evolution) {
		super();
		this.number = number;
		this.name = name;
		this.healthPoints = healthPoints;
		this.evolution = evolution;
	}

	public Article(int number, String name, int healthPoints, List<PokemonType> types, Article evolution) {
		super();
		this.number = number;
		this.name = name;
		this.healthPoints = healthPoints;
		this.types = types;
		this.evolution = evolution;
	}
	
	public void addType(PokemonType type) {
		if (types == null) {
			types = new ArrayList<>();
		}
		types.add(type);
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHealthPoints() {
		return healthPoints;
	}
	public void setHealthPoints(int healthPoints) {
		this.healthPoints = healthPoints;
	}
	public List<PokemonType> getTypes() {
		return types;
	}
	public void setTypes(List<PokemonType> types) {
		this.types = types;
	}
	public Article getEvolution() {
		return evolution;
	}
	public void setEvolution(Article evolution) {
		this.evolution = evolution;
	}
	
	@Override
	public String toString() {
		return "Pokemon [number=" + number + ", name=" + name + ", healthPoints=" + healthPoints + ", types=" + types
				+ ", evolution=" + evolution + "]";
	}
}
