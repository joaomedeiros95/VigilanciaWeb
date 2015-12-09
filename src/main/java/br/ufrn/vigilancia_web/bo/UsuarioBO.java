/**
 * 
 */
package br.ufrn.vigilancia_web.bo;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.vigilancia_web.exception.ValidationException;
import br.ufrn.vigilancia_web.model.Usuario;
import br.ufrn.vigilancia_web.utils.Mensagem;
import br.ufrn.vigilancia_web.utils.MensagemErro;
import br.ufrn.vigilancia_web.utils.Mensagens;

/**
 * @author joao
 *
 */
public class UsuarioBO extends AbstractBO<Usuario> {

	private static final long serialVersionUID = 1L;

	@Override
	void validarObjeto() throws ValidationException {
		List<Mensagem> mensagens = new ArrayList<>();
		
		if(objeto.getEmail() == null || objeto.getEmail() == "") {
			mensagens.add(new MensagemErro("Email é obrigatório!"));
		}
		
		if(objeto.getNome() == null || objeto.getNome() == "") {
			mensagens.add(new MensagemErro("Nome é obrigatório!"));
		}
		
		if(objeto.getSenha() == null || objeto.getSenha() == "") {
			mensagens.add(new MensagemErro("Senha é obrigatório!"));
		}
		
		if(!mensagens.isEmpty()) {
			throw new ValidationException(new Mensagens(mensagens));
		}
	}

}