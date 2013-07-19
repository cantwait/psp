package com.pdvsa.psp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@org.hibernate.annotations.Immutable
@Table(name="permiso_operacion", schema="public")
public class PermisoOperacion implements Serializable{

	private static final long serialVersionUID = -8712232420335945781L;
	@Id
	@SequenceGenerator(name="PERMISO_OPERACION_ID_GENERATOR", sequenceName="PUBLIC.PERMISO_OPERACION_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PERMISO_OPERACION_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Long id;
	@ManyToOne
	@JoinColumn(name="permiso")
	private Permiso permiso;
	@ManyToOne
	@JoinColumn(name="operacion")
	private Operacion operacion;
	
	public PermisoOperacion(){}
	
	public PermisoOperacion(Operacion o, Permiso p){
		this.permiso = p;
		this.operacion = o;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Permiso getPermiso() {
		return permiso;
	}

	public void setPermiso(Permiso permiso) {
		this.permiso = permiso;
	}

	public Operacion getOperacion() {
		return operacion;
	}

	public void setOperacion(Operacion operacion) {
		this.operacion = operacion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());		
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
		PermisoOperacion other = (PermisoOperacion) obj;
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
		if (permiso == null) {
			if (other.permiso != null)
				return false;
		} else if (!permiso.equals(other.permiso))
			return false;
		return true;
	}
	
	

}
