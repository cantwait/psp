package com.pdvsa.psp.model.xml;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="opcInfoRegister")
public class OpcInfoRegisterMongo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6080212548241392706L;
	@Id
	private String id = UUID.randomUUID().toString();

	@Transient
	private Long idServidor;
	
	private String nombreOPC;
	private String variable;
	private Date timestamp;
	private String valor = "";
	private Short calidad = 0;	
	private String unidadMedida;	
	private String localidad;	
	private String tanque;	
	private String region;	
	private String pais;
	private String producto;
	
	@Indexed	
	private String nombreServidor;
	
	public OpcInfoRegisterMongo() {
		
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombreOPC() {
		return nombreOPC;
	}
	
	public void setNombreOPC(String tagOpc) {
		this.nombreOPC = tagOpc;
	}
	
	public String getVariable() {
		return variable;
	}
	
	public void setVariable(String tagName) {
		this.variable = tagName;
	}
	
	@XmlElement(name = "timestamp", required = true) 
    @XmlJavaTypeAdapter(com.pdvsa.psp.serializer.DateAdapter.class)
	public Date getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getValor() {
		return valor;
	}
	
	public void setValor(String regValue) {
		this.valor = regValue;
	}

	public void setCalidad(Short quality) {
		this.calidad = quality;
	}

	public Short getCalidad() {
		return calidad;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidadNombre) {
		this.localidad = localidadNombre;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String regionNombre) {
		this.region = regionNombre;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String paisNombre) {
		this.pais = paisNombre;
	}

	public String getTanque() {
		return tanque;
	}

	public void setTanque(String tankeNombre) {
		this.tanque = tankeNombre;
	}

	public String getNombreServidor() {
		return nombreServidor;
	}

	public void setNombreServidor(String nombreServidor) {
		this.nombreServidor = nombreServidor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((localidad == null) ? 0 : localidad.hashCode());
		result = prime * result
				+ ((nombreServidor == null) ? 0 : nombreServidor.hashCode());
		result = prime * result
				+ ((pais == null) ? 0 : pais.hashCode());
		result = prime * result
				+ ((region == null) ? 0 : region.hashCode());
		result = prime * result + ((nombreOPC == null) ? 0 : nombreOPC.hashCode());
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
		OpcInfoRegisterMongo other = (OpcInfoRegisterMongo) obj;
		if (localidad == null) {
			if (other.localidad != null)
				return false;
		} else if (!localidad.equals(other.localidad))
			return false;
		if (nombreServidor == null) {
			if (other.nombreServidor != null)
				return false;
		} else if (!nombreServidor.equals(other.nombreServidor))
			return false;
		if (pais == null) {
			if (other.pais != null)
				return false;
		} else if (!pais.equals(other.pais))
			return false;
		if (region == null) {
			if (other.region != null)
				return false;
		} else if (!region.equals(other.region))
			return false;
		if (nombreOPC == null) {
			if (other.nombreOPC != null)
				return false;
		} else if (!nombreOPC.equals(other.nombreOPC))
			return false;
		return true;
	}


	public Long getIdServidor() {
		return idServidor;
	}


	public void setIdServidor(Long stationId) {
		this.idServidor = stationId;
	}


	public String getUnidadMedida() {
		return unidadMedida;
	}


	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}


	public String getProducto() {
		return producto;
	}


	public void setProducto(String productoNombre) {
		this.producto = productoNombre;
	}

	
	
	
	
	

}
