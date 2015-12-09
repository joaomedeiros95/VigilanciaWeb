/**
 * 
 */
package br.ufrn.vigilancia_web.bo;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.vigilancia_web.exception.ValidationException;
import br.ufrn.vigilancia_web.model.Camera;
import br.ufrn.vigilancia_web.utils.Mensagem;
import br.ufrn.vigilancia_web.utils.MensagemErro;
import br.ufrn.vigilancia_web.utils.Mensagens;

/**
 * @author joao
 *
 */
public class CameraBO extends AbstractBO<Camera> {

	private static final long serialVersionUID = 1L;

	@Override
	void validarObjeto() throws ValidationException {
		List<Mensagem> mensagens = new ArrayList<>();
		
		if(objeto.getIp() == null || objeto.getIp() == "") {
			mensagens.add(new MensagemErro("IP é obrigatório"));
		}
		
		if(objeto.getDescricao() == null || objeto.getDescricao() == "") {
			mensagens.add(new MensagemErro("Descrição é obrigatório!"));
		}
		
		if(objeto.getTipoCamera() == null) {
			mensagens.add(new MensagemErro("Tipo Câmera é obrigatório!"));
		}
		
		if(!mensagens.isEmpty()) {
			throw new ValidationException(new Mensagens(mensagens));
		}
	}

}
