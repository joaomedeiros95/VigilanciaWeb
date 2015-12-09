/**
 * JOÃO EDUARDO RIBEIRO DE MEDEIROS 
 */

package br.ufrn.vigilancia_web.utils;

import java.io.Serializable;

/**
 * Criado por joao
 * Em 25/06/2015
 */
public class Mensagem implements Serializable { 
	
	private static final long serialVersionUID = 1L;
	
	private String mensagem;
	
	public Mensagem() {
	}
	
	public Mensagem(String mensagem) {
		super();
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
}