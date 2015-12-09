/**
 * 
 */
package br.ufrn.vigilancia_web.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.ufrn.vigilancia_web.bo.GrupoBO;
import br.ufrn.vigilancia_web.bo.StatusGrupoBO;
import br.ufrn.vigilancia_web.exception.ValidationException;
import br.ufrn.vigilancia_web.model.Grupo;
import br.ufrn.vigilancia_web.model.Status;
import br.ufrn.vigilancia_web.model.StatusGrupo;
import br.ufrn.vigilancia_web.utils.Mensagens;

/**
 * @author Danilo
 *
 */
@ManagedBean
@SessionScoped
public class GrupoMBean extends AbstractMBean<Grupo> {

	private static final long serialVersionUID = 1L;
	
	private static final String LISTAGEM = "/painel/grupo/list.xhtml";
	private static final String CADASTRO = "/painel/grupo/form.xhtml";
	
	private GrupoBO bo;
	private StatusGrupo statusGrupo;
	private StatusGrupoBO sgBO;
	
	public String cadastrar() {
		try {
			bo = new GrupoBO();
			if(obj.getId() == null || obj.getId() == 0) {
				bo.gravar(obj);
			} else {
				bo.update(obj);
			}
		} catch (ValidationException e) {
			tratarExcecao(e);
			return null;
		}
		
		addMensagem(Mensagens.MENSAGEM_SUCESSO, Mensagens.CADASTRO_PADRAO);
		showMessages();
		
		return goToList();
	}
	
	public String editar() {
		Integer id = Integer.valueOf(getFacesContext().getExternalContext().getRequestParameterMap().get("id"));
		bo = new GrupoBO();
		obj = bo.getById(id);
		
		return CADASTRO;
	}
	
	public String remover() {
		Integer id = Integer.valueOf(getFacesContext().getExternalContext().getRequestParameterMap().get("id"));
		bo = new GrupoBO();
		bo.remover(id);
		
		return goToList();
	}
	
	public String setarStatus() {
		sgBO = new StatusGrupoBO();
		
		try {
			sgBO.gravar(statusGrupo);
		} catch (ValidationException e) {
			tratarExcecao(e);
			return null;
		}
		
		addMensagem(Mensagens.MENSAGEM_SUCESSO, Mensagens.CADASTRO_PADRAO);
		showMessages();
		
		return goToList();
	}
	
	public String goToList() {
		bo = new GrupoBO();
		statusGrupo = new StatusGrupo();
		all = bo.getAll();
		return LISTAGEM;
	}
	
	public String preCadastrar() {
		obj = new Grupo();
		return CADASTRO;
	}
	
	public Status[] getAllStatus() {
		return Status.values();
	}

	public StatusGrupo getStatusGrupo() {
		return statusGrupo;
	}

	public void setStatusGrupo(StatusGrupo statusGrupo) {
		this.statusGrupo = statusGrupo;
	}
	
}
