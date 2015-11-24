/**
 * 
 */
package br.ufrn.vigilancia_web.bo;

import java.util.List;

import br.ufrn.vigilancia_web.dao.GrupoDao;
import br.ufrn.vigilancia_web.exception.ValidationException;
import br.ufrn.vigilancia_web.model.Grupo;

/**
 * @author Danilo
 *
 */
public class GrupoBO extends AbstractBO<Grupo> {

	private static final long serialVersionUID = 1L;
	private GrupoDao dao;
	
	public GrupoBO() {
		dao = new GrupoDao();
	}
	
	public List<Grupo> findByDescricao(String descricao) {
		return dao.findByDescricao(descricao);
	}
	
	@Override
	public List<Grupo> getAll() {
		return dao.getAll();
	}

	@Override
	void validarObjeto() throws ValidationException {
		// TODO Auto-generated method stub
		
	}

}
