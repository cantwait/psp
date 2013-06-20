package com.pdvsa.psp.model;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.pdvsa.psp.model.Item.DATA_TYPE;
@XmlRootElement(name="opc")
public class OpcInfoRegisterMongo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6080212548241392706L;
	private Long stationId;
	private String hostModbusSlave;
	private Integer portModbusSlave;	
	private Integer reference;
	private String tagOpc;
	private String tagName;
	private Date timestamp;
	private String regValue = "";
	private Short quality = 0;
	private Long localidadId;
	private String localidadNombre;
	private Long regionId;
	private String regionNombre;
	private Long paisId;
	private String paisNombre;
	
	public OpcInfoRegisterMongo() {
		
	}
	
	public OpcInfoRegisterMongo(Long stationId, Integer reference, String tagOpc,
			String tagName, String hostModbusSlave, Integer portModbusSlave) {
		super();
		this.stationId = stationId;
		this.hostModbusSlave = hostModbusSlave;
		this.portModbusSlave = portModbusSlave;
		this.reference = reference;
		this.tagOpc = tagOpc;
		this.tagName = tagName;		
	}

	public OpcInfoRegisterMongo(Long stationId, Integer reference, String tagOpc, String tagName,
			Date timestamp, String regValue, Short quality) {
		this.reference = reference;
		this.tagOpc = tagOpc;
		this.tagName = tagName;		
		this.timestamp = timestamp;
		this.regValue = regValue;
		this.quality = quality;
	}
	
	public Integer getReference() {
		return reference;
	}
	
	public void setReference(Integer reference) {
		this.reference = reference;
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

	public Long getStationId() {
		return stationId;
	}

	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}

	public String getHostModbusSlave() {
		return hostModbusSlave;
	}

	public void setHostModbusSlave(String hostModbusSlave) {
		this.hostModbusSlave = hostModbusSlave;
	}

	public Integer getPortModbusSlave() {
		return portModbusSlave;
	}

	public void setPortModbusSlave(Integer portModbusSlave) {
		this.portModbusSlave = portModbusSlave;
	}

	public void setQuality(Short quality) {
		this.quality = quality;
	}

	public Short getQuality() {
		return quality;
	}

	public Long getLocalidadId() {
		return localidadId;
	}

	public void setLocalidadId(Long localidadId) {
		this.localidadId = localidadId;
	}

	public String getLocalidadNombre() {
		return localidadNombre;
	}

	public void setLocalidadNombre(String localidadNombre) {
		this.localidadNombre = localidadNombre;
	}

	public Long getRegionId() {
		return regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	public String getRegionNombre() {
		return regionNombre;
	}

	public void setRegionNombre(String regionNombre) {
		this.regionNombre = regionNombre;
	}

	public Long getPaisId() {
		return paisId;
	}

	public void setPaisId(Long paisId) {
		this.paisId = paisId;
	}

	public String getPaisNombre() {
		return paisNombre;
	}

	public void setPaisNombre(String paisNombre) {
		this.paisNombre = paisNombre;
	}

	
	
	
	
	

}
