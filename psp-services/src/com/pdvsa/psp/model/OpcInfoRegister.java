package com.pdvsa.psp.model;

import java.io.Serializable;
import java.util.Date;
import com.pdvsa.psp.model.Item.DATA_TYPE;

public class OpcInfoRegister implements Serializable {
	private static final long serialVersionUID = -6334608448567929747L;
	private Long stationId;
	private String hostModbusSlave;
	private Integer portModbusSlave;	
	private Integer reference;
	private String tagOpc;
	private String tagName;
	private DATA_TYPE regType;
	private Date timestamp;
	private String regValue = "";
	private Short quality = 0;
	
	public OpcInfoRegister() {
		
	}
	
	public OpcInfoRegister(Long stationId, Integer reference, String tagOpc,
			String tagName, DATA_TYPE regType, String hostModbusSlave, Integer portModbusSlave) {
		super();
		this.stationId = stationId;
		this.hostModbusSlave = hostModbusSlave;
		this.portModbusSlave = portModbusSlave;
		this.reference = reference;
		this.tagOpc = tagOpc;
		this.tagName = tagName;
		this.regType = regType;
	}

	public OpcInfoRegister(Long stationId, Integer reference, String tagOpc, String tagName,
			DATA_TYPE regType, Date timestamp, String regValue, Short quality) {
		this.reference = reference;
		this.tagOpc = tagOpc;
		this.tagName = tagName;
		this.regType = regType;
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
	
	public DATA_TYPE getRegType() {
		return regType;
	}
	
	public void setRegType(DATA_TYPE regType) {
		this.regType = regType;
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
}
