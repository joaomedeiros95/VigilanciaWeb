/**
 * 
 */
package br.ufrn.vigilancia_web.bo;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import br.ufrn.vigilancia_web.dao.GenericDao;
import br.ufrn.vigilancia_web.exception.ValidationException;

/**
 * @author joao
 *
 */
public abstract class AbstractBO<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	protected T objeto;
	protected GenericDao<T> gDao = new GenericDao<>();
	
	public T getById(Integer id) {
		return (T) gDao.getById(id, getTypeClass());
	}
	
	public List<T> getAll() {
		return (List<T>) gDao.getAll(getTypeClass());
	}
	
	public void gravar(T objeto) throws ValidationException {
		this.objeto = objeto;
		
		validarObjeto();
		
		gDao.create(objeto);
	}
	
	public void update(T objeto) throws ValidationException {
		this.objeto = objeto;
		
		validarObjeto();
		
		gDao.update(objeto);
	}
	
	abstract void validarObjeto() throws ValidationException;
	
	@SuppressWarnings("unchecked")
	private Class<T> getTypeClass() {
		Class<T> clazz = (Class<T>) ((ParameterizedType) this.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        return clazz;
    }
	
	/**
	 * @param obj
	 */
	public void remover(T obj) {
		gDao.delete(obj);
	}
	
	/**
	 * @param obj
	 */
	public void remover(Integer id) {
		gDao.delete(id, getTypeClass());
	}
	
}
