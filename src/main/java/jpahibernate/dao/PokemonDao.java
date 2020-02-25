package jpahibernate.dao;

import jpahibernate.dao.helper.DatabaseHelper;
import jpahibernate.exception.AlreadyExistsException;
import jpahibernate.model.Pokemon;
import jpahibernate.model.PokemonType;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;

public class PokemonDao extends GenericDao {

	/**
	 * Inserts a pokemon.
	 * @param pokemon The pokemon to persist.
	 * @return The persisted pokemon.
	 * @throws AlreadyExistsException The pokemon already exists.
	 */
	public Pokemon insert(Pokemon pokemon) throws AlreadyExistsException {
		EntityManager entityManager = getEntityManager();
		
		// Check if number already exists
		Pokemon existing = findByNumber(pokemon.getNumber());
		if (existing != null) {
			throw new AlreadyExistsException("A pokemon with the number " + pokemon.getNumber() + " already exists.");
		}
		DatabaseHelper.beginTransaction(entityManager);
		entityManager.persist(pokemon);
		DatabaseHelper.commitTransactionAndClose(entityManager);
		return pokemon;
	}
	
	public Pokemon update(Pokemon pokemon) {
		EntityManager entityManager = getEntityManager();
		
		DatabaseHelper.beginTransaction(entityManager);
		entityManager.merge(pokemon);
		DatabaseHelper.commitTransactionAndClose(entityManager);
		
		return pokemon;
	}

	/**
	 * Finds all pokemons.
	 * @return A list containing all the pokemons.
	 */
	public List<Pokemon> findAll() {
		return getEntityManager().createQuery("select p from Pokemon p left join fetch p.types order by p.number", Pokemon.class).getResultList();
	}

	/**
	 * Finds all pokemons of a given type.
	 * @return A list containing all the pokemons of the given type.
	 */
	public List<Pokemon> findAllByType(PokemonType type) {
		TypedQuery<Pokemon> query = getEntityManager().createQuery("select p from Pokemon p join p.types t where t = :type order by p.number", Pokemon.class);
		query.setParameter("type", type);
		return query.getResultList();
	}

	/**
	 * Finds a pokemon by its id.
	 * @return The matching pokemon, otherwise null.
	 * @throws SQLException 
	 */
	public Pokemon findById(long id) {
		return getEntityManager().find(Pokemon.class, id);
	}

	/**
	 * Finds a pokemon by its number.
	 * @return The matching pokemon, otherwise null.
	 * @throws SQLException 
	 */
	public Pokemon findByNumber(int number) {
		TypedQuery<Pokemon> query = getEntityManager().createQuery("from Pokemon p where p.number = :number", Pokemon.class);
		query.setParameter("number", number);

		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
