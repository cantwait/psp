package com.pdvsa.psp.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "usuarios", schema = "public")
public class Usuario implements Serializable, BizEntity {
	private static final long serialVersionUID = 8073553667213665976L;
	private String email;
	@Id
	@Column(unique = true, nullable = false, updatable = false)
	@SequenceGenerator(name = "USUARIOS_ID_GENERATOR", sequenceName = "PUBLIC.USUARIOS_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "USUARIOS_ID_GENERATOR")
	private Long id = Long.MIN_VALUE;
	private Boolean activo = true;
	@Column(name = "login", unique = true, nullable = false, length = 30)
	private String login;
	@Column(name = "numero_celular", length = 20)
	private String numeroCelular;
	private String nombre;
	@Column(name = "password", nullable = false, length = 100)
	private String password;
	@Column(name = "numero_telefonico", length = 20)
	private String numeroTelefonico;
	private Integer version = 0;
	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<UsuarioRol> usuarioRoles = new HashSet<UsuarioRol>(0);
//	@OneToMany(mappedBy="usuario", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
//	@XmlTransient
//	private Set<TransaccionOperacionUsuario> transaccionOperacionUsuario = new HashSet<TransaccionOperacionUsuario>();	

	public Usuario() {
	}

	public Usuario(String login, String nombre, Set<UsuarioRol> usuarioRoles) {
		this.login = login;
		this.nombre = nombre;
		this.usuarioRoles = usuarioRoles;
	}

	public Usuario(String email, Boolean activo, String login,
			String numeroCelular, String nombre, String numeroTelefonico,
			Set<UsuarioRol> usuarioRoles) {
		this.email = email;
		this.activo = activo;
		this.login = login;
		this.numeroCelular = numeroCelular;
		this.nombre = nombre;
		this.numeroTelefonico = numeroTelefonico;
		this.usuarioRoles = usuarioRoles;
	}

	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public Boolean getActivo() {
		return this.activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	
	public String getNumeroCelular() {
		return this.numeroCelular;
	}

	public void setNumeroCelular(String numeroCelular) {
		this.numeroCelular = numeroCelular;
	}

	
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getNumeroTelefonico() {
		return this.numeroTelefonico;
	}

	public void setNumeroTelefonico(String numeroTelefonico) {
		this.numeroTelefonico = numeroTelefonico;
	}

	// @Version
	// @Column(nullable=false)
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}


	@XmlTransient
	public Set<UsuarioRol> getUsuarioRoles() {
		return this.usuarioRoles;
	}

	public void setUsuarioRoles(Set<UsuarioRol> usuarioRoles) {
		this.usuarioRoles = usuarioRoles;
	}	

//	@XmlTransient	
//	public Set<TransaccionOperacionUsuario> getTransaccionOperacionUsuario() {
//		return transaccionOperacionUsuario;
//	}
//
//	public void setTransaccionOperacionUsuario(
//			Set<TransaccionOperacionUsuario> transaccionOperacionUsuario) {
//		this.transaccionOperacionUsuario = transaccionOperacionUsuario;
//	}

	@Transient
	public boolean isNew() {
		return (getId() == Long.MIN_VALUE);
	}

	@Override
	public int hashCode() {
		return Long.valueOf(this.id).hashCode();
	}

	public boolean equals(Usuario obj) {
		return getId() == obj.getId();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Usuario) {
			Usuario ObjEntity = (Usuario) obj;
			return equals(ObjEntity);
		}
		return false;
	}

	@Override
	public String toString() {
		return nombre + " (" + login + ")";
	}

	

}