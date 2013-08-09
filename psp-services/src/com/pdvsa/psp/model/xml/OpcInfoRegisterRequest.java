package com.pdvsa.psp.model.xml;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name="Opc.Request")
@XmlAccessorType(XmlAccessType.FIELD)
public class OpcInfoRegisterRequest {
	
	private Long stationId;
	private Date desde;
	private Date hasta;
	
	public OpcInfoRegisterRequest(){}
	
//	@XmlJavaTypeAdapter(com.pdvsa.psp.serializer.DateAdapter.class)	
	public Date getDesde() {
		return desde;
	}
		
	public void setDesde(Date desde) {
		this.desde = desde;
	}	
//	@XmlJavaTypeAdapter(com.pdvsa.psp.serializer.DateAdapter.class)
	public Date getHasta() {
		return hasta;
	}

	public void setHasta(Date hasta) {
		this.hasta = hasta;
	}
	
	
	public String toString(){
		StringBuilder sw = new StringBuilder();
		
		sw.append("Fecha Desde: ").append(getDesde()).append(" ").append("Fecha Hasta: ").append(getHasta());
		
		return sw.toString();
	}

	public Long getStationId() {
		return stationId;
	}

	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}
	
	

}
