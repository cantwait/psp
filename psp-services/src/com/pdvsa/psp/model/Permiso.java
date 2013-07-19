package com.pdvsa.psp.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name="permisos", schema="public")
public class Permiso implements Serializable{

	private static final long serialVersionUID = 4148495248651448588L;
	@Id
	private String codigo;
	private String descripcion;
	@OneToMany(mappedBy="permiso", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	@XmlTransient
	private Set<PermisoOperacion> permisoOperaciones = new HashSet<PermisoOperacion>();
	
	
	public Permiso(){}
	
	public Permiso(String codigo, String descripcion){
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
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
		Permiso other = (Permiso) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		return true;
	}

	public Set<PermisoOperacion> getPermisoOperaciones() {
		return permisoOperaciones;
	}

	public void setPermisoOperaciones(Set<PermisoOperacion> permisoOperaciones) {
		this.permisoOperaciones = permisoOperaciones;
	}
	
	

}
