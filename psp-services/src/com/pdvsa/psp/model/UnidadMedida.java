package com.pdvsa.psp.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="unidades_medida", schema="cs")
public class UnidadMedida implements Serializable, BizEntity {
	private static final long serialVersionUID = 2150047473286354386L;
	private Long id = Long.MIN_VALUE;
	private String descripcion;
	private String nombre;
	private Integer version = 0;
	private TipoUnidadMedida tipoUnidadMedida;

    public UnidadMedida() {
    }
    
    

	public UnidadMedida(Long id, String descripcion, String nombre) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.nombre = nombre;
	}



	@Id
	@SequenceGenerator(name="UNIDADES_MEDIDA_ID_GENERATOR", sequenceName="CS.UNIDADES_MEDIDA_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="UNIDADES_MEDIDA_ID_GENERATOR")
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
	
    @ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="tipo_medida_id", nullable=false)
	public TipoUnidadMedida getTipoUnidadMedida() {
		return this.tipoUnidadMedida;
	}

	public void setTipoUnidadMedida(TipoUnidadMedida tipoUnidadMedida) {
		this.tipoUnidadMedida = tipoUnidadMedida;
	}
	
	
	@Transient
	public boolean isNew() {
		return (getId() == Long.MIN_VALUE);
	}
	
	@Override
	public int hashCode() {
		return Long.valueOf(this.id).hashCode();
	}
	
	public boolean equals(UnidadMedida obj) {
		return getId() == obj.getId();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof UnidadMedida) {
			UnidadMedida objEntity = (UnidadMedida) obj;
			return equals(objEntity);
		}
		return false;
	}
	
	@Override
	public String toString() {
		return nombre;
	}	
}