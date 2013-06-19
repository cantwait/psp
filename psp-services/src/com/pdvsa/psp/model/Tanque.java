package com.pdvsa.psp.model;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name="tanques", schema="cs")
public class Tanque implements Serializable, BizEntity {
	private static final long serialVersionUID = -7388419861891862952L;
	private Long id = Long.MIN_VALUE;
	private Boolean activo = true;
	private String descripcion;
	private String nombre;
	private Integer version = 0;
	private ServidorOpc servidorOpc;
	private Producto producto;

    public Tanque() {
    }


	@Id
	@SequenceGenerator(name="TANQUES_ID_GENERATOR", sequenceName="cs.tanques_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TANQUES_ID_GENERATOR")
	@Column(unique=true, nullable=false)
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


	@Column(nullable=false, length=50)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

//	@Version()
//	@Column(nullable=false)
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}


	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="id_servidor_opc", nullable=false)	
	public ServidorOpc getServidorOpc() {
		return this.servidorOpc;
	}

	public void setServidorOpc(ServidorOpc servidorOpc) {
		this.servidorOpc = servidorOpc;
	}
	
    @ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_producto", nullable=false)
	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}	
	
	@Transient
	public boolean isNew() {
		return (getId() == Long.MIN_VALUE);
	}
	
	@Override
	public int hashCode() {
		return Long.valueOf(this.id).hashCode();
	}
	
	public boolean equals(Tanque obj) {
		return getId() == obj.getId();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Tanque) {
			Tanque objEntity = (Tanque) obj;
			return equals(objEntity);
		}
		return false;
	}
	
	@Override
	public String toString() {
		return nombre;
	}	
}