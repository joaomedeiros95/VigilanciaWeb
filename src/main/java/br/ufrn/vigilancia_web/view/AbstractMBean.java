/**
 * 
 */
package br.ufrn.vigilancia_web.view;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.ufrn.vigilancia_web.exception.ValidationException;
import br.ufrn.vigilancia_web.utils.Mensagem;
import br.ufrn.vigilancia_web.utils.MensagemAviso;
import br.ufrn.vigilancia_web.utils.MensagemErro;
import br.ufrn.vigilancia_web.utils.Mensagens;

/**
 * @author joao
 *
 */
public class AbstractMBean<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	protected T obj;
	protected Mensagens mensagens = new Mensagens();
	protected List<T> all;
	
	public void showErrorMessage(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", mensagem) );
	}
	
	public void showInfoMessage(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção", mensagem) );
	}
	
	public void showSuccessMessage(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", mensagem) );
	}
	
	public void showMessages() {
		for(Mensagem mensagem : mensagens.getMensagens()) {
			if(mensagem instanceof MensagemErro) {
				showErrorMessage(mensagem.getMensagem());
			} else if(mensagem instanceof MensagemAviso) {
				showInfoMessage(mensagem.getMensagem());
			} else {
				showSuccessMessage(mensagem.getMensagem());
			}
		}
	}
	
	/** Caso a página atual esteja no painel de controle, retorne para o início do painel, 
	 *  caso contrário retorne para o ínicio do site.
	 */
	public String irParaoInicio() {
		String pagina = FacesContext.getCurrentInstance().getViewRoot().getViewId();
		
		if(pagina.contains("painel")) {
			return "/painel/index.xhtml";
		} else {
			return "/index.xhtml";
		}
	}
	
	public Integer getAnoAtual() {
		return new GregorianCalendar().get(Calendar.YEAR);
	}

	public T getObj() {
		return obj;
	}

	public void setObj(T obj) {
		this.obj = obj;
	}

	public List<T> getAll() {
		return all;
	}
	
	public void addMensagem(Integer tipo, String mensagem) {
		if(mensagem == null) {
			mensagem = Mensagens.MENSAGEM_PADRAO;
		}
		mensagens.addMensagem(tipo, mensagem);
	}
	
	public void addMensagens(Mensagens mensagens) {
		this.mensagens = mensagens;
	}
	
	public boolean hasErrors() {
		return mensagens.hasErrors();
	}
	
	public void clearMensagens() {
		mensagens = new Mensagens();
	}

	public void setAll(List<T> all) {
		this.all = all;
	}
	
	public FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}
	
	public void addSessionAttribute(String key, Object value) {
		getFacesContext().getExternalContext().getSessionMap().put(key, value);
	}
	
	public void invalidateSession() {
		getFacesContext().getExternalContext().invalidateSession();
	}
	
	public void tratarExcecao(ValidationException e){
		e.printStackTrace();
		
		if(e.getMensagens() != null) {
			addMensagens(e.getMensagens());
		} else {
			addMensagem(Mensagens.MENSAGEM_ERRO, e.getMessage());
		}
		
		showMessages();
	}
	
}
