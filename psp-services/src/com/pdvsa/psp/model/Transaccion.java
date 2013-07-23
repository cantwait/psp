package com.pdvsa.psp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

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
	@Enumerated(EnumType.STRING)
	private TipoTransaccion tipoTransaccion;
	@OneToMany(mappedBy="transaccion", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private Set<TransaccionOperacionUsuario> transaccionOperaciones = new HashSet<TransaccionOperacionUsuario>();
	private String archivoZul;
	@ManyToOne
	@JoinColumn(name="padre")
	private Transaccion padre;
	
	@OneToMany(mappedBy="padre", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<Transaccion> hijos = new HashSet<Transaccion>();
	
	@Transient
	private Set<Transaccion> aux = new HashSet<Transaccion>();
	
	
//	@Sort(type = SortType.COMPARATOR, comparator = OperacionComparator.class)
//	@ManyToMany(cascade={CascadeType.REFRESH},fetch = FetchType.EAGER)
//	@JoinTable(name = "transaccionoperacion", joinColumns = @JoinColumn(name = "transaccion"), inverseJoinColumns = @JoinColumn(name = "operacion"))
//	private Set<Operacion> operaciones = new TreeSet<Operacion>(new OperacionComparator());
//	@ManyToMany(cascade={CascadeType.REFRESH},fetch = FetchType.EAGER)
//	@JoinTable(name = "permisotransaccion", joinColumns = @JoinColumn(name = "transaccion"), inverseJoinColumns = @JoinColumn(name = "grupo"))
//	private Set<Grupo> grupos = new HashSet<Grupo>();
	
	public enum TipoTransaccion {
		ROOT, FOLDER, TRANSACTION
	}
	
	
	public List<TipoTransaccion> getTiposTransaccion(){
		List<TipoTransaccion> tipos = new ArrayList<TipoTransaccion>();
		
		for (TipoTransaccion tipoTransaccion : TipoTransaccion.values()) {
			tipos.add(tipoTransaccion);
		}
		
		return tipos;
	}
	
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
	
	

	public TipoTransaccion getTipoTransaccion() {
		return tipoTransaccion;
	}

	public void setTipoTransaccion(TipoTransaccion tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}

	@XmlTransient
	public Set<TransaccionOperacionUsuario> getTransaccionOperaciones() {
		return transaccionOperaciones;
	}

	public void setTransaccionOperaciones(
			Set<TransaccionOperacionUsuario> transaccionOperaciones) {
		this.transaccionOperaciones = transaccionOperaciones;
	}
	
	

	public String getArchivoZul() {
		return archivoZul;
	}

	public void setArchivoZul(String archivoZul) {
		this.archivoZul = archivoZul;
	}

	public Transaccion getPadre() {
		return padre;
	}

	public void setPadre(Transaccion padre) {
		this.padre = padre;
	}

	@XmlTransient
	public Set<Transaccion> getHijos() {
		return hijos;
	}

	public void setHijos(Set<Transaccion> hijos) {
		this.hijos = hijos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}
	
	

	public Set<Transaccion> getAux() {
		return aux;
	}

	public void setAux(Set<Transaccion> aux) {
		this.aux = aux;
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
