package com.pdvsa.psp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@org.hibernate.annotations.Immutable
@Table(name="transaccion_operacion_usuario", schema="public")
public class TransaccionOperacionUsuario implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name="TRANSACCION_OPERACION_ID_GENERATOR", sequenceName="PUBLIC.TRANSACCION_OPERACION_USUARIO_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TRANSACCION_OPERACION_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Long id;
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="transaccion")
	private Transaccion transaccion;
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="operacion")
	private Operacion operacion;
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="usuario")
	private Usuario usuario;
	
	public TransaccionOperacionUsuario(){}
	
	public TransaccionOperacionUsuario(Transaccion transaccion, Operacion operacion, Usuario usuario){
		this.transaccion = transaccion;
		this.operacion = operacion;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Transaccion getTransaccion() {
		return transaccion;
	}

	public void setTransaccion(Transaccion transaccion) {
		this.transaccion = transaccion;
	}

	public Operacion getOperacion() {
		return operacion;
	}

	public void setOperacion(Operacion operacion) {
		this.operacion = operacion;
	}
	


	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((operacion == null) ? 0 : operacion.hashCode());
		result = prime * result
				+ ((transaccion == null) ? 0 : transaccion.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransaccionOperacionUsuario other = (TransaccionOperacionUsuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (operacion == null) {
			if (other.operacion != null)
				return false;
		} else if (!operacion.equals(other.operacion))
			return false;
		if (transaccion == null) {
			if (other.transaccion != null)
				return false;
		} else if (!transaccion.equals(other.transaccion))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

	
	
	
	

}
