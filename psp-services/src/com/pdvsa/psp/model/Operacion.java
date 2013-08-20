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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name="operaciones", schema="public")
public class Operacion implements Serializable{

	private static final long serialVersionUID = 2371432274292360225L;
	@Id
	private String codigo;
	private String nombre;
	private Integer orden = new Integer(0);
	@OneToMany(mappedBy="operacion", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<TransaccionOperacionUsuario> transaccionOperacionUsuario = new HashSet<TransaccionOperacionUsuario>();
//	@Transient
//	private Set<Usuario> usuarios = new HashSet<Usuario>();
	@Transient
	private Set<Rol> roles = new HashSet<Rol>();
	
	public Operacion(){}
	
	public Operacion(String codigo, String nombre){
		this.codigo = codigo;
		this.nombre = nombre;
	}	

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}	

	@XmlTransient
	public Set<TransaccionOperacionUsuario> getTransaccionOperacionUsuario() {
		return transaccionOperacionUsuario;
	}

	public void setTransaccionOperacionUsuario(
			Set<TransaccionOperacionUsuario> transaccionOperacionUsuario) {
		this.transaccionOperacionUsuario = transaccionOperacionUsuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Operacion other = (Operacion) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	@XmlTransient
	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}
	
//	@XmlTransient
//	public Set<Usuario> getUsuarios() {
//		return usuarios;
//	}
//
//	public void setUsuarios(Set<Usuario> usuarios) {
//		this.usuarios = usuarios;
//	}

	
	
	
	
	

}
