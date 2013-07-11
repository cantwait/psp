package com.pdvsa.psp.model.xml;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.pdvsa.psp.model.ServidorGrupo;
import com.pdvsa.psp.model.ServidorOpc;
import com.pdvsa.psp.model.ServidorRol;
import com.pdvsa.psp.model.Tanque;

@XmlRootElement(name="servidorTransfer")
public class ServidorTransferencia implements Serializable{

	private static final long serialVersionUID = -2273234077412397973L;
	private ServidorOpc servidor;
	private List<Tanque> tanques = new ArrayList<Tanque>();
	private List<ServidorRol> servidorRol = new ArrayList<ServidorRol>();
	private List<ServidorGrupo> servidorGrupo = new ArrayList<ServidorGrupo>();
	
	
	public ServidorTransferencia(){}
	
	


	public ServidorTransferencia(ServidorOpc servidor, List<Tanque> tanques, List<ServidorGrupo> servidorGrupo) {
		super();
		this.servidor = servidor;
		this.tanques = tanques;		
		this.servidorGrupo = servidorGrupo;
	}




	public ServidorOpc getServidor() {
		return servidor;
	}


	public void setServidor(ServidorOpc servidor) {
		this.servidor = servidor;
	}

	
	@XmlElementWrapper(name="tanques")
	@XmlElement(name="tanque")
	public List<Tanque> getTanques() {
		return tanques;
	}


	public void setTanques(List<Tanque> tanques) {
		this.tanques = tanques;
	}

	@XmlElementWrapper(name="servidorRoles")
	@XmlElement(name="servidorRol")
	public List<ServidorRol> getServidorRol() {
		return servidorRol;
	}


	public void setServidorRol(List<ServidorRol> servidorRol) {
		this.servidorRol = servidorRol;
	}

	@XmlElementWrapper(name="servidorGrupos")
	@XmlElement(name="servidorGrupo")
	public List<ServidorGrupo> getServidorGrupo() {
		return servidorGrupo;
	}


	public void setServidorGrupo(List<ServidorGrupo> servidorGrupo) {
		this.servidorGrupo = servidorGrupo;
	}
	
	
	
	

}
