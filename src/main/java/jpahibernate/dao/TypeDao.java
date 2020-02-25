package jpahibernate.dao;

import jpahibernate.dao.helper.DatabaseHelper;
import jpahibernate.exception.AlreadyExistsException;
import jpahibernate.model.PokemonType;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;

public class TypeDao extends GenericDao {

	/**
	 * Inserts a type.
	 * @param type The type to persist.
	 * @return the persisted type.
	 * @throws AlreadyExistsException The type already exists.
	 */
	public PokemonType insert(PokemonType type) throws AlreadyExistsException {
		EntityManager entityManager = getEntityManager();
		
		PokemonType existing = findByName(type.getLabel());
		if (existing != null) {
			throw new AlreadyExistsException("The type named " + type.getLabel() + " already exists.");
		}
		DatabaseHelper.beginTransaction(entityManager);
		entityManager.persist(type);
		DatabaseHelper.commitTransactionAndClose(entityManager);
		return type;
	}

	/**
	 * Finds all types.
	 * @return A list containing all the types.
	 * @throws SQLException 
	 */
	public List<PokemonType> findAll() {
		return getEntityManager().createQuery("from PokemonType order by number", PokemonType.class).getResultList();
	}

	/**
	 * Finds a type by its id.
	 * @return The matching type, otherwise null.
	 * @throws SQLException 
	 */
	public PokemonType findById(long id) {
		return getEntityManager().find(PokemonType.class, id);
	}

	/**
	 * Finds a type by its name.
	 * @return The matching type, otherwise null.
	 * @throws SQLException 
	 */
	public PokemonType findByName(String name) {
		TypedQuery<PokemonType> query = getEntityManager().createQuery("from PokemonType where label = :name", PokemonType.class);
		query.setParameter("name", name);
		return query.getSingleResult();		
	}
}
