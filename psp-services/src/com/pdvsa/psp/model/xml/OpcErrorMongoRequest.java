package com.pdvsa.psp.model.xml;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.springframework.format.annotation.DateTimeFormat;

@XmlRootElement(name="OpcErrorRequest")
public class OpcErrorMongoRequest implements Serializable{

	private static final long serialVersionUID = 2449541296727612161L;
	private Date desde;
	private Date hasta;
	
	
	public OpcErrorMongoRequest(){}
	
	public OpcErrorMongoRequest(Date desde, Date hasta){
		this.desde = desde;
		this.hasta = hasta;
	}
	
	
	@XmlJavaTypeAdapter(com.pdvsa.psp.serializer.DateAdapter.class)
	public Date getDesde() {
		return desde;
	}

	public void setDesde(Date desde) {
		this.desde = desde;
	}
	
	@XmlJavaTypeAdapter(com.pdvsa.psp.serializer.DateAdapter.class)
	public Date getHasta() {
		return hasta;
	}

	public void setHasta(Date hasta) {
		this.hasta = hasta;
	}
	
	

}
