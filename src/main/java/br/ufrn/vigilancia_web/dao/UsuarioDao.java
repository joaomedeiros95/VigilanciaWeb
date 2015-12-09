/**
 * 
 */
package br.ufrn.vigilancia_web.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.ufrn.vigilancia_web.model.Usuario;

/**
 * @author joao
 *
 */
public class UsuarioDao extends GenericDao<Usuario> {

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	public Usuario findByEmail(String email) {
		EntityManager em = getEntityManager();
		StringBuilder hql = new StringBuilder();
		
		hql.append(" FROM Usuario WHERE email = '" + email + "'");
		
		List<Usuario> objetos;
		try {
			objetos = em.createQuery(hql.toString()).getResultList();
		} finally {
			em.close();
		}
		
		if(objetos == null) {
			return null;
		} else {
			return objetos.get(0);
		}
	}

}
