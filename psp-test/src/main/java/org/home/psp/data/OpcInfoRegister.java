package org.home.psp.data;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@XmlRootElement(name="opc")
public class OpcInfoRegister implements Serializable {
	
	private static final long serialVersionUID = -6334608448567929747L;
	@Indexed
	private Long stationId = RandomUtils.nextLong();
	private String hostModbusSlave;
	private String portModbusSlave;	
	private String reference;
	private String tagOpc;
	private String tagName;
	private String regType;
	private String timestamp;
	private String regValue = "";
	private String quality;
	
	
	public OpcInfoRegister() {
		super();
		// TODO Auto-generated constructor stub
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
	public String getPortModbusSlave() {
		return portModbusSlave;
	}
	public void setPortModbusSlave(String portModbusSlave) {
		this.portModbusSlave = portModbusSlave;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
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
	public String getRegType() {
		return regType;
	}
	public void setRegType(String regType) {
		this.regType = regType;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getRegValue() {
		return regValue;
	}
	public void setRegValue(String regValue) {
		this.regValue = regValue;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	
	
	
	
	
	
	
}
