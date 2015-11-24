/**
 * 
 */
package br.ufrn.vigilancia_web.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author joao
 *
 */
public class GenericDao<T> implements IGenericDao<T>, Serializable {

	private static final long serialVersionUID = 1L;
	
	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("vigilancia_web");
	
	public GenericDao() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void create(T entity) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void update(T entity) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.merge(entity);
		em.getTransaction().commit();
		em.close();
	}
	
	@Override
	public void delete(Integer id, Class<T> entidade) {
		EntityManager em = factory.createEntityManager();
		try {
			em.getTransaction().begin();
			em.remove(em.find(entidade, id)); 
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}
	
	@Override
	public void delete(T entity) {
		EntityManager em = factory.createEntityManager();
		try {
			em.getTransaction().begin();
			em.remove(entity);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll(Class<T> entidade) {
		EntityManager em = factory.createEntityManager();
		try {
			return em.createQuery("from " + entidade.getSimpleName()).getResultList();
		} finally {
			em.close();
		}
	}

	@Override
	public T getById(Integer id, Class<T> entidade) {
		EntityManager em = factory.createEntityManager();
		try {
			return em.find(entidade, id);
		} finally {
			em.close();
		}
	}
	
	public EntityManager getEntityManager() {
		return factory.createEntityManager();
	}

}
