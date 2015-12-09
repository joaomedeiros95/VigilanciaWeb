/**
 * 
 */
package br.ufrn.vigilancia_web.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.ufrn.vigilancia_web.bo.UsuarioBO;
import br.ufrn.vigilancia_web.exception.LoginException;
import br.ufrn.vigilancia_web.exception.ValidationException;
import br.ufrn.vigilancia_web.model.Usuario;
import br.ufrn.vigilancia_web.utils.Mensagens;

/**
 * @author joao
 *
 */
@ManagedBean
@SessionScoped
public class UsuarioMBean extends AbstractMBean<Usuario> {

	private static final long serialVersionUID = 1L;
	
	private static final String CADASTRO = "/cadastro.xhtml";
	private static final String LOGIN = "/login.xhtml";
	private static final String ADMIN = "/painel/index.xhtml";
	
	private boolean logado;
	
	private UsuarioBO bo;
	
	public String cadastrar() {
		bo = new UsuarioBO();
		try {
			bo.gravar(obj);
		} catch (ValidationException e) {
			tratarExcecao(e);
			return null;
		}
		
		addMensagem(Mensagens.MENSAGEM_SUCESSO, "Cadastro realizado com sucesso!");
		showMessages();
		
		return irParaoInicio();
	}
	
	public String login() {
		logado = false;
		bo = new UsuarioBO();
		
		try {
			bo.checkLogin(obj);
			addSessionAttribute("login", obj.getEmail());
		} catch (LoginException e) {
			tratarExcecao(e);
			return null;
		}
		
		addMensagem(Mensagens.MENSAGEM_SUCESSO, "Login realizado com sucesso!");
		showMessages();
		
		logado = true;
		
		return ADMIN;
	}
	
	public String sair() {
		obj = new Usuario();
		logado = false;
		invalidateSession();
		
		return "/index.xhtml";
	}
	
	public String goToCadastro() {
		obj = new Usuario();
		return CADASTRO;
	}
	
	public String goToLogin() {
		obj = new Usuario();
		return LOGIN;
	}

	public boolean isLogado() {
		return logado;
	}

	public void setLogado(boolean logado) {
		this.logado = logado;
	}

}
