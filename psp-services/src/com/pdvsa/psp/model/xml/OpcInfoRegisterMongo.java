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


//@XmlRootElement(name="object")
//@XmlAccessorType(XmlAccessType.FIELD)
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
	
	private String tagOpc;
	private String tagName;
	private Date timestamp;
	private String regValue = "";
	private Short quality = 0;	
	private String unidadMedida;	
	private String localidadNombre;	
	private String tanqueNombre;	
	private String regionNombre;	
	private String paisNombre;
	
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

	public String getTagOpc() {
		return tagOpc;
	}
	
	public void setTagOpc(String tagOpc) {
		this.tagOpc = tagOpc;
	}
	
	public String getTagName() {
		return tagName;
	}
	
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
//	@JsonSerialize(using= JsonTimeSerializer.class)
	@XmlElement(name = "timestamp", required = true) 
    @XmlJavaTypeAdapter(com.pdvsa.psp.serializer.DateAdapter.class)
	public Date getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getRegValue() {
		return regValue;
	}
	
	public void setRegValue(String regValue) {
		this.regValue = regValue;
	}

	public void setQuality(Short quality) {
		this.quality = quality;
	}

	public Short getQuality() {
		return quality;
	}

	public String getLocalidadNombre() {
		return localidadNombre;
	}

	public void setLocalidadNombre(String localidadNombre) {
		this.localidadNombre = localidadNombre;
	}

	public String getRegionNombre() {
		return regionNombre;
	}

	public void setRegionNombre(String regionNombre) {
		this.regionNombre = regionNombre;
	}

	public String getPaisNombre() {
		return paisNombre;
	}

	public void setPaisNombre(String paisNombre) {
		this.paisNombre = paisNombre;
	}

	public String getTanqueNombre() {
		return tanqueNombre;
	}

	public void setTanqueNombre(String tankeNombre) {
		this.tanqueNombre = tankeNombre;
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
				+ ((localidadNombre == null) ? 0 : localidadNombre.hashCode());
		result = prime * result
				+ ((nombreServidor == null) ? 0 : nombreServidor.hashCode());
		result = prime * result
				+ ((paisNombre == null) ? 0 : paisNombre.hashCode());
		result = prime * result
				+ ((regionNombre == null) ? 0 : regionNombre.hashCode());
		result = prime * result + ((tagOpc == null) ? 0 : tagOpc.hashCode());
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
		if (localidadNombre == null) {
			if (other.localidadNombre != null)
				return false;
		} else if (!localidadNombre.equals(other.localidadNombre))
			return false;
		if (nombreServidor == null) {
			if (other.nombreServidor != null)
				return false;
		} else if (!nombreServidor.equals(other.nombreServidor))
			return false;
		if (paisNombre == null) {
			if (other.paisNombre != null)
				return false;
		} else if (!paisNombre.equals(other.paisNombre))
			return false;
		if (regionNombre == null) {
			if (other.regionNombre != null)
				return false;
		} else if (!regionNombre.equals(other.regionNombre))
			return false;
		if (tagOpc == null) {
			if (other.tagOpc != null)
				return false;
		} else if (!tagOpc.equals(other.tagOpc))
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

	
	
	
	
	

}
