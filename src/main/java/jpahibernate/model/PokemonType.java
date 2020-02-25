package jpahibernate.model;

import javax.persistence.*;

@Entity
@Table(name = "type_pokemon")
public class PokemonType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String label;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	@Override
	public String toString() {
		return "Type [label=" + label + "]";
	}
}
