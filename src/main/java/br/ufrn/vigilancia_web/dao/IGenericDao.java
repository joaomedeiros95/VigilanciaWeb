package br.ufrn.vigilancia_web.dao;

import java.util.List;

public interface IGenericDao<T> {

	void create(T entity);

	void update(T entity);

	void delete(T entity);

	List<T> getAll(Class<T> class1);

	T getById(Integer id, Class<T> class1);

	void delete(Integer id, Class<T> entidade);

}