package com.pdvsa.psp.model.xml;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="OpcErrorResponse")
public class OpcErrorResponse implements Serializable{

	private static final long serialVersionUID = -7324991563471824645L;
	private List<MongoLogger> bitacoras = new ArrayList<MongoLogger>();
	
	public OpcErrorResponse(){
		
	}
	
	public OpcErrorResponse(List<MongoLogger> errores){
		this.bitacoras = errores;
	}
	
	@XmlElementWrapper(name="bitacoras")
	@XmlElement(name="log")
	public List<MongoLogger> getErrores() {
		return bitacoras;
	}

	public void setErrores(List<MongoLogger> errores) {
		this.bitacoras = errores;
	}
	
	

}
