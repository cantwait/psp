package com.pdvsa.psp.model.xml;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

//@XmlRootElement(name="log")
public class MongoLogger implements Serializable{

	private static final long serialVersionUID = 6243639081989351350L;
	private String descripcion;
	private Date fecha;
	private String causa;
	private Evento tipoEvento;
	
	public enum Evento{
		TRANSACCION, CONEXION, VALIDACION, DESCONOCIDO, EXITO
	}
	
	public MongoLogger(){}
	
	public MongoLogger(String descripcion, Date fecha, String causa, Evento movimiento){
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.causa = causa;
		this.tipoEvento = movimiento;
		
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

	public Evento getTipoMovimiento() {
		return tipoEvento;
	}

	public void setTipoMovimiento(Evento tipoMovimiento) {
		this.tipoEvento = tipoEvento;
	}

	public Evento getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(Evento tipoEvento) {
		this.tipoEvento = tipoEvento;
	}
	
	
		

}
