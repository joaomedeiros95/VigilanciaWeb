/**
 * 
 */
package br.ufrn.vigilancia_web.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.ufrn.vigilancia_web.bo.UsuarioBO;
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
	
	public String goToCadastro() {
		obj = new Usuario();
		return CADASTRO;
	}

}
