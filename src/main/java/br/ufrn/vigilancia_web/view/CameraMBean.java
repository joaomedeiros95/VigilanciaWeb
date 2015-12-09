/**
 * 
 */
package br.ufrn.vigilancia_web.view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.ufrn.vigilancia_web.bo.CameraBO;
import br.ufrn.vigilancia_web.bo.GrupoBO;
import br.ufrn.vigilancia_web.exception.ValidationException;
import br.ufrn.vigilancia_web.model.Camera;
import br.ufrn.vigilancia_web.model.Grupo;
import br.ufrn.vigilancia_web.model.TipoCamera;
import br.ufrn.vigilancia_web.utils.Mensagens;

/**
 * @author joao
 */
@ManagedBean
@SessionScoped
public class CameraMBean extends AbstractMBean<Camera> {

	private static final long serialVersionUID = 1L;
	
	private static final String LISTAGEM = "/painel/camera/list.xhtml";
	private static final String CADASTRO = "/painel/camera/form.xhtml";
	private static final String VIEW_CAMERA = "/painel/camera/camera.xhtml";
	
	private CameraBO bo;
	private GrupoBO gBo;
	
	private Camera viewCamera;
	
	public String cadastrar() {
		try {
			bo = new CameraBO();
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
		bo = new CameraBO();
		obj = bo.getById(id);
		
		return CADASTRO;
	}
	
	public String visualizar() {
		Integer id = Integer.valueOf(getFacesContext().getExternalContext().getRequestParameterMap().get("id"));
		bo = new CameraBO();
		viewCamera = bo.getById(id);
		
		return VIEW_CAMERA;
	}
	
	public String remover() {
		Integer id = Integer.valueOf(getFacesContext().getExternalContext().getRequestParameterMap().get("id"));
		bo = new CameraBO();
		bo.remover(id);
		
		return goToList();
	}
	
	public String goToList() {
		bo = new CameraBO();
		all = bo.getAll();
		return LISTAGEM;
	}
	
	public List<Grupo> getAllGrupo() {
		gBo = new GrupoBO();
		return gBo.getAll();
	}
	
	public String preCadastrar() {
		obj = new Camera();
		return CADASTRO;
	}

	public Camera getViewCamera() {
		return viewCamera;
	}

	public void setViewCamera(Camera viewCamera) {
		this.viewCamera = viewCamera;
	}
	
	public TipoCamera[] getTipoCameras() {
		return TipoCamera.values();
	}
	
}
