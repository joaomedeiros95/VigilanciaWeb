/**
 * 
 */
package br.ufrn.vigilancia_web.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author joao
 *
 */
@Entity
@Table(schema="public", name="camera")
public class Camera implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="id_camera")
	private Integer id;
	
	@Column(name="ip_camera")
	private String ip;
	
	private String descricao;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="tipo_camera")
	private TipoCamera tipoCamera;
	
	@ManyToOne
	@JoinColumn(name="id_grupo")
	private Grupo grupo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public TipoCamera getTipoCamera() {
		return tipoCamera;
	}

	public void setTipoCamera(TipoCamera tipoCamera) {
		this.tipoCamera = tipoCamera;
	}
	
}
