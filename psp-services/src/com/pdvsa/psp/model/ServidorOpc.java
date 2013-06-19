package com.pdvsa.psp.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Cascade;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "servidores_opc", schema = "cs")
public class ServidorOpc implements Serializable, BizEntity {
	
	public enum ACCESS_METHOD {SYNC, ASYNC20, SUBSCRIPTION }
	
	private static final long serialVersionUID = -1197525150527500840L;
	private Long id = Long.MIN_VALUE;
	private Boolean activo = true;
	private String clsid;
	private String descripcion;
	private String domain;
	private String host;
	private Boolean itemCache = false;
	private Integer sleepInterPooling = 5000;
	private ACCESS_METHOD accessMethod = ACCESS_METHOD.SYNC;
	private Boolean autoConectar = true;
	private Integer socketTimeout = 7000;
	private Integer refAddressBase = 40000;
	private String nombre;
	private String password;
	private String progid;
	private String proveedorOpc;
	private String username;
	private Integer version = 0;
	private String host_adquisicion;
	private Integer port_adquisicion = 502;
	private Localidad localidad;
	private Set<Tanque> tanques = new HashSet<Tanque>(0);
	private Set<ServidorGrupo> servidorGrupos = new HashSet<ServidorGrupo>(0);
	private Set<ServidorRol> servidorRoles = new HashSet<ServidorRol>(0);

	public ServidorOpc() {
	}

	@Id
	@SequenceGenerator(name = "SERVIDORES_OPC_ID_GENERATOR", sequenceName = "CS.SERVIDORES_OPC_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SERVIDORES_OPC_ID_GENERATOR")
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

	@Column(length = 50)
	public String getClsid() {
		return this.clsid;
	}

	public void setClsid(String clsid) {
		this.clsid = clsid;
	}

	@Column(length = 500)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(nullable = true, length = 50)
	public String getDomain() {
		return this.domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Column(nullable = false, length = 100)
	public String getHost() {
		return this.host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	@Column(name = "item_cache", nullable = false)
	public Boolean getItemCache() {
		return itemCache;
	}

	public void setItemCache(Boolean itemCache) {
		this.itemCache = itemCache;
	}

	@Column(nullable = false, length = 50)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(length = 50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(length = 50)
	public String getProgid() {
		return progid;
	}

	public void setProgid(String progid) {
		this.progid = progid;
	}

	@Column(name = "proveedor_opc", length = 100)
	public String getProveedorOpc() {
		return proveedorOpc;
	}

	public void setProveedorOpc(String proveedorOpc) {
		this.proveedorOpc = proveedorOpc;
	}

	@Column(name = "username", length = 50)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String user) {
		this.username = user;
	}

//	@Version
//	@Column(nullable = false)
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_localidad", nullable = false)
//	@OptimisticLock(excluded = true)
	public Localidad getLocalidad() {
		return this.localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	@OneToMany(mappedBy = "servidorOpc", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Cascade(value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
//	@OptimisticLock(excluded = true)
	@XmlTransient
	public Set<Tanque> getTanques() {
		return this.tanques;
	}

	public void setTanques(Set<Tanque> tanques) {
		this.tanques = tanques;
	}

	@OneToMany(mappedBy = "servidorOpc", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Cascade(value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	//@OptimisticLock(excluded = true)
	@XmlTransient
	public Set<ServidorGrupo> getServidorGrupos() {
		return this.servidorGrupos;
	}

	public void setServidorGrupos(Set<ServidorGrupo> servidorGrupos) {
		this.servidorGrupos = servidorGrupos;
	}

	@OneToMany(mappedBy = "servidorOpc", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Cascade(value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
//	@OptimisticLock(excluded = true)
	@XmlTransient
	public Set<ServidorRol> getServidorRoles() {
		return this.servidorRoles;
	}

	public void setServidorRoles(Set<ServidorRol> servidorRoles) {
		this.servidorRoles = servidorRoles;
	}

	@Column(length = 100, name = "host_adquisicion")
	public String getHost_adquisicion() {
		return host_adquisicion;
	}

	public void setHost_adquisicion(String hostAdquisicion) {
		host_adquisicion = hostAdquisicion;
	}

	@Column(name = "port_adquisicion")
	public Integer getPort_adquisicion() {
		return port_adquisicion;
	}

	public void setPort_adquisicion(Integer portAdquisicion) {
		port_adquisicion = portAdquisicion;
	}
	
	@Column(name = "access_method", nullable = true)
	@Enumerated(EnumType.ORDINAL)
	public ACCESS_METHOD getAccessMethod() {
		return accessMethod;
	}

	public void setAccessMethod(ACCESS_METHOD accessMethod) {
		this.accessMethod = accessMethod;
	}

	@Column(name = "auto_conectar", nullable = false)
	public Boolean getAutoConectar() {
		return autoConectar;
	}

	public void setAutoConectar(Boolean autoConectar) {
		this.autoConectar = autoConectar;
	}
	
	@Column(name = "sleep_inter_pooling", nullable = false)
	public Integer getSleepInterPooling() {
		return sleepInterPooling;
	}

	public void setSleepInterPooling(Integer sleepInterPooling) {
		this.sleepInterPooling = sleepInterPooling;
	}	

	@Column(name = "socket_timeout", nullable = false)	
	public Integer getSocketTimeout() {
		return socketTimeout;
	}	

	public void setSocketTimeout(Integer socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

	@Column(name = "ref_address_base", nullable = false)	
	public Integer getRefAddressBase() {
		return refAddressBase;
	}
	
	public void setRefAddressBase(Integer refAddressBase) {
		this.refAddressBase = refAddressBase;
	}	

	@Transient
	public boolean isNew() {
		return (getId() == Long.MIN_VALUE);
	}

	@Override
	public int hashCode() {
		return Long.valueOf(this.id).hashCode();
	}

	public boolean equals(ServidorOpc obj) {
		return getId() == obj.getId();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof ServidorOpc) {
			ServidorOpc objEntity = (ServidorOpc) obj;
			return equals(objEntity);
		}
		return false;
	}

	@Override
	public String toString() {
		return nombre;
	}
	
}