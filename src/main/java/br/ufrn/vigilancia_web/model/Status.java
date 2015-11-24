/**
 * 
 */
package br.ufrn.vigilancia_web.model;

/**
 * @author joao
 *
 */
public enum Status {

	SEGURO, SUSPEITO, INSEGURO, NAODEFINIDO;
	
	public String getNome() {
		return this.name();
	}
	
}
