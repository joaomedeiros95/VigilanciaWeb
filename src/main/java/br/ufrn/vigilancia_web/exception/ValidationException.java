/**
 * 
 */
package br.ufrn.vigilancia_web.exception;

import br.ufrn.vigilancia_web.utils.Mensagens;

/**
 * @author joao
 *
 */
public class ValidationException extends Exception {

	private static final long serialVersionUID = 1L;
	private Mensagens mensagens;
	
	public ValidationException() {
		super("Ocorreram erros na validação do objeto.");
	}
	
	public ValidationException(Mensagens mensagens) {
		super("Ocorreram erros na validação do objeto.");
		this.mensagens = mensagens;
	}

	public Mensagens getMensagens() {
		return mensagens;
	}

	public void setMensagens(Mensagens mensagens) {
		this.mensagens = mensagens;
	}

}