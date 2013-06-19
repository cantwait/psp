package com.pdvsa.psp.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
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

@Entity
@Table(name="paises", schema="public")
public class Pais implements Serializable, BizEntity{
	private static final long serialVersionUID = -7007902509754868704L;
	private Long id = Long.MIN_VALUE;
	private Boolean activo = true;
	private String descripcion;
	private String nombre;
	private Integer version = 0;
	private Set<Region> regiones = new HashSet<Region>(0);

	public Pais() {

	}

	@Id
	@SequenceGenerator(name="PAISES_ID_GENERATOR", sequenceName="PUBLIC.PAISES_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PAISES_ID_GENERATOR")
	@Column(updatable=false, unique=true, nullable=false)	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable = false)
	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	@Column(length = 500)
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(nullable = false, length = 100)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

//	@Version
//	@Column(nullable = false)
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@OneToMany(mappedBy = "pais", fetch = FetchType.LAZY)
	@XmlTransient
	public Set<Region> getRegiones() {
		return regiones;
	}

	public void setRegiones(Set<Region> regiones) {
		this.regiones = regiones;
	}

	@Transient
	public boolean isNew() {
		return (getId() == Long.MIN_VALUE);
	}
	
	@Override
	public int hashCode() {
		return Long.valueOf(this.id).hashCode();
	}
	
	public boolean equals(Pais obj) {
		return getId() == obj.getId();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Pais) {
			Pais objEntity = (Pais) obj;
			return equals(objEntity);
		}
		return false;
	}
	
	@Override
	public String toString() {
		return nombre;
	}

}
