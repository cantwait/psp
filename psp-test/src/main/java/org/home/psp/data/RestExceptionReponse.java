package org.home.psp.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ExceptionResponse")
public class RestExceptionReponse{
	
	
	private enum Mensajes{
		ERROR
	}
	
	private String mensaje = Mensajes.ERROR.name();
	private String descripcion;
	
	public RestExceptionReponse(){
		
	}
	
	public RestExceptionReponse(String descripcion){
		this.descripcion = descripcion;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
	
	

}
