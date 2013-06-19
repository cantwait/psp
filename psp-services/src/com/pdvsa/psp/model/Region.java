package com.pdvsa.psp.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="regiones", schema="public")
public class Region implements Serializable,  BizEntity {
	private static final long serialVersionUID = 3998301630662663411L;
	private Long id = Long.MIN_VALUE;
	private Boolean activo = true;
	private String descripcion;
	private String nombre;
	private Integer version = 0;
	private Pais pais;
	private Set<Localidad> localidades = new HashSet<Localidad>(0);

    public Region() {
    }


	@Id
	@SequenceGenerator(name="REGIONES_ID_GENERATOR", sequenceName="PUBLIC.REGIONES_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REGIONES_ID_GENERATOR")
	@Column(updatable=false, unique=true, nullable=false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	@Column(nullable=false)
	public Boolean getActivo() {
		return this.activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}


	@Column(length=500)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	@Column(nullable=false, length=100)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


//	@Version
//	@Column(nullable=false)
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}


    @ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="id_pais", nullable=false)
	public Pais getPais() {
		return this.pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}
	
	
	@OneToMany(mappedBy="region", fetch=FetchType.LAZY)
	@XmlTransient
	public Set<Localidad> getLocalidades() {
		return this.localidades;
	}

	public void setLocalidades(Set<Localidad> localidades) {
		this.localidades = localidades;
	}
	
	@Transient
	public boolean isNew() {
		return (getId() == Long.MIN_VALUE);
	}
	
	@Override
	public int hashCode() {
		return Long.valueOf(this.id).hashCode();
	}
	
	public boolean equals(Region obj) {
		return getId() == obj.getId();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Region) {
			Region objEntity = (Region) obj;
			return equals(objEntity);
		}
		return false;
	}
	
	@Override
	public String toString() {
		return nombre;
	}		
	
}