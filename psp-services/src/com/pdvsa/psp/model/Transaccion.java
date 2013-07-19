package com.pdvsa.psp.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;


@Entity
@Table(name="transacciones", schema="public")
public class Transaccion implements Serializable{

	private static final long serialVersionUID = -5588301895436470044L;
	@Id
	@SequenceGenerator(name = "TRANSACCION_ID_GENERATOR", sequenceName = "TRANSACCION_CODIGO_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRANSACCION_ID_GENERATOR")
	@Column(name = "codigo", unique = true, nullable = false)
	private Integer codigo;
	@Column(name = "nombre", length = 100)
	private String nombre;
	@Column(name = "descripcion")
	private String descripcion;
	@Column(name = "estado", length = 1)
	private String estado;
	
//	@Sort(type = SortType.COMPARATOR, comparator = OperacionComparator.class)
//	@ManyToMany(cascade={CascadeType.REFRESH},fetch = FetchType.EAGER)
//	@JoinTable(name = "transaccionoperacion", joinColumns = @JoinColumn(name = "transaccion"), inverseJoinColumns = @JoinColumn(name = "operacion"))
//	private Set<Operacion> operaciones = new TreeSet<Operacion>(new OperacionComparator());
//	@ManyToMany(cascade={CascadeType.REFRESH},fetch = FetchType.EAGER)
//	@JoinTable(name = "permisotransaccion", joinColumns = @JoinColumn(name = "transaccion"), inverseJoinColumns = @JoinColumn(name = "grupo"))
//	private Set<Grupo> grupos = new HashSet<Grupo>();
	
	public Transaccion(){}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaccion other = (Transaccion) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	

}
