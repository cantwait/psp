package org.home.psp.data;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.home.psp.DateConverterMapper;

import com.thoughtworks.xstream.annotations.XStreamConverter;

@XmlRootElement(name="opcInfoRegisterRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class OpcInfoRegisterRequest {
	
	
	private Date desde;
	private Date hasta;
	
	public OpcInfoRegisterRequest(){}

	public Date getDesde() {
		return desde;
	}

	public void setDesde(Date desde) {
		this.desde = desde;
	}

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
	
	

}
