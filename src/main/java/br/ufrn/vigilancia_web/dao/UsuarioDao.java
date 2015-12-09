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
	
	@SuppressWarnings("unchecked")
	public Usuario checkLogin(Usuario usuario) {
		EntityManager entityManager = getEntityManager();
		List<Usuario> users = entityManager.createQuery("FROM Usuario WHERE email = :email AND senha = :senha")
							.setParameter("email", usuario.getEmail())
							.setParameter("senha", usuario.getSenha())
							.getResultList();
		entityManager.close();
		return users.size() > 0 ? users.get(0) : null;
	}


}
