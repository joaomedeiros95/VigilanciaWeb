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
 * @author joao
 *
 */
public class GrupoDao extends GenericDao<Grupo> {

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	public List<Grupo> getAll() {
		EntityManager em = getEntityManager();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select g.*, st.id_status_grupo, st.status, st.ultima_atualizacao ");
		sql.append(" from grupo g, (	select id_status_grupo, id_grupo, status, ultima_atualizacao ");
		sql.append(" 					from status_grupo s order by s.ultima_atualizacao ");
		sql.append("					desc limit 1) as st ");
		sql.append(" where g.id_grupo = st.id_grupo; ");
		
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
			
			grupo.setStatusGrupo(new StatusGrupo());
			grupo.getStatusGrupo().setGrupo(grupo);
			grupo.getStatusGrupo().setId((Integer) objeto[2]);
			grupo.getStatusGrupo().setStatus(Status.valueOf((String) objeto[3]));
			grupo.getStatusGrupo().setUltimaAtualizacao((Date) objeto[4]);
			
			retorno.add(grupo);
		}
		
		return retorno;
	}
	
}
