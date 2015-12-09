/**
 * 
 */
package br.ufrn.vigilancia_web.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.ufrn.vigilancia_web.bo.CasaBO;
import br.ufrn.vigilancia_web.bo.MensagemCasaBO;
import br.ufrn.vigilancia_web.exception.ValidationException;
import br.ufrn.vigilancia_web.model.Casa;
import br.ufrn.vigilancia_web.model.MensagemCasa;
import br.ufrn.vigilancia_web.utils.Mensagens;

/**
 * @author joao
 *
 */
@ManagedBean
@SessionScoped
public class CasaMBean extends AbstractMBean<Casa> {

	private static final long serialVersionUID = 1L;
	
	private static final String LISTAGEM = "/painel/casa/list.xhtml";
	
	private CasaBO bo;
	private MensagemCasaBO mcBO;
	
	private MensagemCasa mensagemCasa;
	
	public String abrirFecharCasa() {
		Integer id = Integer.valueOf(getFacesContext().getExternalContext().getRequestParameterMap().get("id"));
		bo = new CasaBO();
		
		Casa casa = bo.getById(id);
		
		if(casa.isFechada()) {
			casa.setFechada(false);
		} else {
			casa.setFechada(true);
		}
		
		try {
			bo.update(casa);
		} catch (ValidationException e) {
			tratarExcecao(e);
			return null;
		}
		
		addMensagem(Mensagens.MENSAGEM_SUCESSO, Mensagens.CADASTRO_PADRAO);
		showMessages();
		
		return goToList();
	}
	
	public String escreverMensagemCasa() {
		mcBO = new MensagemCasaBO();
		
		try {
			mcBO.gravar(mensagemCasa);
		} catch (ValidationException e) {
			tratarExcecao(e);
			return null;
		}
		
		addMensagem(Mensagens.MENSAGEM_SUCESSO, Mensagens.CADASTRO_PADRAO);
		showMessages();
		
		return goToList();
	}
	
	public String goToList() {
		bo = new CasaBO();
		mensagemCasa = new MensagemCasa();
		all = bo.getAll();
		return LISTAGEM;
	}

	public MensagemCasa getMensagemCasa() {
		return mensagemCasa;
	}

	public void setMensagemCasa(MensagemCasa mensagemCasa) {
		this.mensagemCasa = mensagemCasa;
	}

}
