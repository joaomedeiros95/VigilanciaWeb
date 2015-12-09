/**
 * 
 */
package br.ufrn.vigilancia_web.exception;

import br.ufrn.vigilancia_web.utils.Mensagens;

/**
 * @author joao
 *
 */
public class LoginException extends ValidationException {

	private static final long serialVersionUID = 1L;
	
	public LoginException(Mensagens mensagens) {
		super(mensagens);
	}

}
