/**
 * JOÃO EDUARDO RIBEIRO DE MEDEIROS 
 */
package br.ufrn.vigilancia_web.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Criado por joao
 * Em 25/06/2015
 */
public class Mensagens implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public static final int MENSAGEM_ERRO = 1;
	public static final int MENSAGEM_AVISO = 2;
	public static final int MENSAGEM_SUCESSO = 3;
	public static final String MENSAGEM_PADRAO = "Ocorreu um Comportamento Inesperado ao realizar sua operação, entre em contato com o suporte!";
	public static final String CADASTRO_PADRAO = "Cadastrado com Sucesso!";
	
	private List<Mensagem> mensagens;
	
	public Mensagens() {
		mensagens = new ArrayList<Mensagem>();
	}
	
	public Mensagens(List<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}
	
	public void addMensagemErro(String mensagem) {
		MensagemErro msg = new MensagemErro();
		msg.setMensagem(mensagem);
		mensagens.add(msg);
	}
	
	public void addMensagemAviso(String mensagem) {
		MensagemAviso msg = new MensagemAviso();
		msg.setMensagem(mensagem);
		mensagens.add(msg);
	}
	
	public void addMensagemSucesso(String mensagem) {
		MensagemSucesso msg = new MensagemSucesso();
		msg.setMensagem(mensagem);
		mensagens.add(msg);
	}
	
	public void addMensagem(Integer tipo, String mensagem) {
		switch (tipo) {
		case MENSAGEM_AVISO:
			addMensagemAviso(mensagem);
			break;
		case MENSAGEM_ERRO:
			addMensagemErro(mensagem);
			break;
		case MENSAGEM_SUCESSO:
			addMensagemSucesso(mensagem);
			break;
		default:
			break;
		}
	}

	public List<Mensagem> getMensagens() {
		return mensagens;
	}
	
	public boolean hasErrors() {
		for(Mensagem mensagem : mensagens) {
			if(mensagem instanceof MensagemErro) {
				return true;
			}
		}
		
		return false;
	}

}