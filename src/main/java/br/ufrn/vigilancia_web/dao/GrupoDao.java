/**
 * 
 */
package br.ufrn.vigilancia_web.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import br.ufrn.vigilancia_web.model.Grupo;
import br.ufrn.vigilancia_web.model.Status;
import br.ufrn.vigilancia_web.model.StatusGrupo;

/**
 * @author Danilo
 *
 */
public class GrupoDao extends GenericDao<Grupo> {

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	public List<Grupo> getAll() {
		EntityManager em = getEntityManager();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select g.*, (select status from status_grupo s where g.id_grupo = s.id_grupo order by s.ultima_atualizacao desc limit 1),  ");
		sql.append(" (select ultima_atualizacao from status_grupo s where g.id_grupo = s.id_grupo order by s.ultima_atualizacao desc limit 1) ");
		sql.append(" from grupo g  ");
		
		List<Object[]> objetos;
		try {
			objetos = em.createNativeQuery(sql.toString()).getResultList();
		} finally {
			em.close();
		}
		
		List<Grupo> retorno = new ArrayList<>();
		
		for(Object[] objeto : objetos) {
			Grupo grupo = new Grupo();
			grupo.setId((Integer) objeto[0]);
			grupo.setDescricao((String) objeto[1]);
			
			if(objeto[2] != null) {
				grupo.setStatusGrupo(new StatusGrupo());
				grupo.getStatusGrupo().setGrupo(grupo);
				grupo.getStatusGrupo().setStatus(Status.valueOf((String) objeto[2]));
				grupo.getStatusGrupo().setUltimaAtualizacao((Date) objeto[3]);
			}
			
			retorno.add(grupo);
		}
		
		return retorno;
	}
	
}
