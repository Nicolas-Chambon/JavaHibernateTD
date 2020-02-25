package jpahibernate.dao;

import jpahibernate.dao.helper.DatabaseHelper;

import javax.persistence.EntityManager;

public class GenericDao {
	
	private EntityManager entityManager;
	
	public GenericDao() {
		this.entityManager = DatabaseHelper.createEntityManager();
	}
	
	protected EntityManager getEntityManager() {
		if (entityManager == null || !entityManager.isOpen()) {
			entityManager = DatabaseHelper.createEntityManager();
		}
		return entityManager;
	}
}
