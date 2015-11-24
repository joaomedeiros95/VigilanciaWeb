/**
 * 
 */
package br.ufrn.vigilancia_web.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Danilo
 *
 */
@Entity
@Table(schema="public", name="grupo")
public class Grupo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="id_grupo")
	private Integer id;
	
	private String descricao;
	
	@Transient
	private StatusGrupo statusGrupo;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public StatusGrupo getStatusGrupo() {
		return statusGrupo;
	}

	public void setStatusGrupo(StatusGrupo statusGrupo) {
		this.statusGrupo = statusGrupo;
	}
	
}
