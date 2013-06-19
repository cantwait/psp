package com.pdvsa.psp.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="tipos_unidad_medida", schema="cs")
public class TipoUnidadMedida implements Serializable, BizEntity {
	private static final long serialVersionUID = -2296735276115573625L;
	private Long id = Long.MIN_VALUE;
	private String descripcion;
	private String nombre;
	private Integer version = 0;

    public TipoUnidadMedida() {
    }


	@Id
	@SequenceGenerator(name="TIPOS_UNIDAD_MEDIDA_ID_GENERATOR", sequenceName="CS.TIPOS_UNIDAD_MEDIDA_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TIPOS_UNIDAD_MEDIDA_ID_GENERATOR")
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
	
	public boolean equals(TipoUnidadMedida obj) {
		return getId() == obj.getId();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof TipoUnidadMedida) {
			TipoUnidadMedida objEntity = (TipoUnidadMedida) obj;
			return equals(objEntity);
		}
		return false;
	}
	
	@Override
	public String toString() {
		return nombre;
	}	

}