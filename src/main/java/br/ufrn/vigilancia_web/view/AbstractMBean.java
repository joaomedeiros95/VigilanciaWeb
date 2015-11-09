/**
 * 
 */
package br.ufrn.vigilancia_web.view;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.context.FacesContext;

/**
 * @author joao
 *
 */
public class AbstractMBean<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	protected T obj;
	protected List<T> all;
	
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

	public void setAll(List<T> all) {
		this.all = all;
	}
	
	public FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}
	
}
