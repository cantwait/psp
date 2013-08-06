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
	private List<TransferExceptionMongo> errores = new ArrayList<TransferExceptionMongo>();
	
	public OpcErrorResponse(){
		
	}
	
	public OpcErrorResponse(List<TransferExceptionMongo> errores){
		this.errores = errores;
	}
	
	@XmlElementWrapper(name="errores")
	@XmlElement(name="Error")
	public List<TransferExceptionMongo> getErrores() {
		return errores;
	}

	public void setErrores(List<TransferExceptionMongo> errores) {
		this.errores = errores;
	}
	
	

}
