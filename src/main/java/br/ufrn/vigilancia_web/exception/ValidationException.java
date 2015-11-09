/**
 * 
 */
package br.ufrn.vigilancia_web.exception;

/**
 * @author joao
 *
 */
public class ValidationException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ValidationException() {
		super("Ocorreu um erro na validação do objeto.");
	}

}