/**
 * 
 */
package br.ufrn.vigilancia_web.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author joao
 *
 */
@Entity
@Table(schema="public", name="status_grupo")
public class StatusGrupo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id_status_grupo")
	private Integer id;
	
	@Column(name="ultima_atualizacao", insertable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date ultimaAtualizacao = new Date();
	
	@ManyToOne
	@JoinColumn(name="id_grupo")
	private Grupo grupo;
	
	@Enumerated(EnumType.STRING)
	private Status status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getUltimaAtualizacao() {
		return ultimaAtualizacao;
	}

	public void setUltimaAtualizacao(Date ultimaAtualizacao) {
		this.ultimaAtualizacao = ultimaAtualizacao;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
