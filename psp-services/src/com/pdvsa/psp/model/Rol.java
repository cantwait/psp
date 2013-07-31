package com.pdvsa.psp.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Cascade;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles", schema = "public")
public class Rol implements Serializable, BizEntity {
	private static final long serialVersionUID = -2473939702505272815L;
	private String descripcion;
	private Long id = Long.MIN_VALUE;
	private Boolean activo = true;
	private String nombre;
	private Integer version = 0;
	private Set<UsuarioRol> usuarioRoles = new HashSet<UsuarioRol>(0);

	public Rol() {
	}

	@Id
	@Column(unique = true, nullable = false, updatable = false)
	@SequenceGenerator(name = "ROLES_ID_GENERATOR", sequenceName = "PUBLIC.ROLES_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ROLES_ID_GENERATOR")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "descripcion", length = 500)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "activo", nullable = false)
	public Boolean getActivo() {
		return this.activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	@Column(name = "nombre", unique = true, nullable = false, length = 50)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

//	@Version
//	@Column(nullable = false)
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@OneToMany(mappedBy = "rol", fetch = FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)	
	//@OptimisticLock(excluded = true)
	@XmlTransient
	public Set<UsuarioRol> getUsuarioRoles() {
		return this.usuarioRoles;
	}

	public void setUsuarioRoles(Set<UsuarioRol> usuarioRoles) {
		this.usuarioRoles = usuarioRoles;
	}

	@Transient
	public boolean isNew() {
		return (getId() == Long.MIN_VALUE);
	}

	@Override
	public int hashCode() {
		return Long.valueOf(this.id).hashCode();
	}

	public boolean equals(Rol obj) {
		return getId() == obj.getId();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Rol) {
			Rol objEntity = (Rol) obj;
			return equals(objEntity);
		}
		return false;
	}

	@Override
	public String toString() {
		return nombre;
	}

	
}