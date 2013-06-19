package com.pdvsa.psp.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="productos", schema="cs")
public class Producto implements Serializable, BizEntity {
	private static final long serialVersionUID = 931332579988397177L;
	private Long id = Long.MIN_VALUE;
	private String descripcion;
	private String nombre;
	private Integer version = 0;

    public Producto() {
    }


	@Id
	@SequenceGenerator(name="PRODUCTOS_ID_GENERATOR", sequenceName="CS.PRODUCTOS_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRODUCTOS_ID_GENERATOR")
	@Column(updatable=false, unique=true, nullable=false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	@Column(length=500)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	@Column(nullable=false, length=50)
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

	@Transient
	public boolean isNew() {
		return (getId() == Long.MIN_VALUE);
	}
	
	@Override
	public int hashCode() {
		return Long.valueOf(this.id).hashCode();
	}
	
	public boolean equals(Producto obj) {
		return getId() == obj.getId();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Producto) {
			Producto objEntity = (Producto) obj;
			return equals(objEntity);
		}
		return false;
	}
	
	@Override
	public String toString() {
		return nombre;
	}
}