package com.pdvsa.psp.model.xml;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name="Error")
public class TransferExceptionMongo implements Serializable{

	private static final long serialVersionUID = 6243639081989351350L;
	private String descripcion;
	private Date fecha;
	private String causa;
	
	
	public TransferExceptionMongo(){}
	
	public TransferExceptionMongo(String descripcion, Date fecha, String causa){
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.causa = causa;
	}
	
	public String getDescripcion() {
		return descripcion;
	}	
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@XmlJavaTypeAdapter(com.pdvsa.psp.serializer.DateAdapter.class)
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getCausa() {
		return causa;
	}

	public void setCausa(String causa) {
		this.causa = causa;
	}
	
	
		

}
