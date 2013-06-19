package com.pdvsa.psp.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.OptimisticLock;

@Entity
@Table(name="localidades", schema="public")
public class Localidad implements Serializable, BizEntity {
	private static final long serialVersionUID = 4876363122759229489L;
	private Long id = Long.MIN_VALUE;
	private Boolean activo = true;
	private String descripcion;
	private String nombre;
	private Integer version = 0;
	private Region region;
	private Set<ServidorOpc> servidoresOpc = new HashSet<ServidorOpc>(0);

    public Localidad() {
    }


	@Id
	@SequenceGenerator(name="LOCALIDADES_ID_GENERATOR", sequenceName="PUBLIC.LOCALIDADES_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LOCALIDADES_ID_GENERATOR")
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
	@JoinColumn(name="id_region", nullable=false)
	@OptimisticLock(excluded = true)
	public Region getRegion() {
		return this.region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}
	
	@OneToMany(mappedBy="localidad", fetch=FetchType.LAZY)
	@OptimisticLock(excluded = true)
	@XmlTransient
	public Set<ServidorOpc> getServidoresOpc() {
		return this.servidoresOpc;
	}

	public void setServidoresOpc(Set<ServidorOpc> servidoresOpc) {
		this.servidoresOpc = servidoresOpc;
	}	
	
	@Transient
	public boolean isNew() {
		return (getId() == Long.MIN_VALUE);
	}
	
	@Override
	public int hashCode() {
		return Long.valueOf(this.id).hashCode();
	}
	
	public boolean equals(Localidad obj) {
		return getId() == obj.getId();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Localidad) {
			Localidad objEntity = (Localidad) obj;
			return equals(objEntity);
		}
		return false;
	}
	
	@Override
	public String toString() {
		return nombre;
	}			
	
}