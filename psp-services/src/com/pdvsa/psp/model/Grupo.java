package com.pdvsa.psp.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Cascade;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "grupos", schema = "cs")
public class Grupo implements Serializable, BizEntity {
	private static final long serialVersionUID = 6940668694319266933L;
	private Long id = Long.MIN_VALUE;
	private Boolean activo = true;
	private String descripcion;
	private String nombre;
	private Integer pooling = 30000;
	private Integer version = 0;
	private Set<GrupoItem> grupoItems = new HashSet<GrupoItem>(0);
	private Set<ServidorGrupo> servidorGrupos = new HashSet<ServidorGrupo>(0);

	public Grupo() {
	}

	@Id
	@SequenceGenerator(name = "GRUPOS_ID_GENERATOR", sequenceName = "CS.GRUPOS_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GRUPOS_ID_GENERATOR")
	@Column(updatable = false, unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable = false)
	public Boolean getActivo() {
		return this.activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	@Column(length = 500)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(nullable = false, length = 50)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(nullable = false)
	public Integer getPooling() {
		return pooling;
	}

	public void setPooling(Integer pooling) {
		this.pooling = pooling;
	}

//	@Version
//	@Column(nullable = false)
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@OneToMany(mappedBy = "grupo", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true)
//	@Cascade(value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
//	@OptimisticLock(excluded = true)
	@XmlTransient
	public Set<GrupoItem> getGrupoItems() {
		return this.grupoItems;
	}

	public void setGrupoItems(Set<GrupoItem> grupoItems) {
		this.grupoItems = grupoItems;
	}

	@OneToMany(mappedBy = "grupo", fetch = FetchType.LAZY)
	@XmlTransient
//	@OptimisticLock(excluded = true)
	public Set<ServidorGrupo> getServidorGrupos() {
		return this.servidorGrupos;
	}

	public void setServidorGrupos(Set<ServidorGrupo> servidorGrupos) {
		this.servidorGrupos = servidorGrupos;
	}

	@Transient
	public boolean isNew() {
		return (getId() == Long.MIN_VALUE);
	}

	@Override
	public int hashCode() {
		return Long.valueOf(this.id).hashCode();
	}

	public boolean equals(Grupo obj) {
		return getId() == obj.getId();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Grupo) {
			Grupo objEntity = (Grupo) obj;
			return equals(objEntity);
		}
		return false;
	}

	@Override
	public String toString() {
		return nombre;
	}

}