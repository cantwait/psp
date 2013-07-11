package com.pdvsa.psp.model.xml;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.pdvsa.psp.model.Grupo;
import com.pdvsa.psp.model.GrupoItem;

@XmlRootElement(name="grupoOpcTransfer")
public class GrupoTransferencia implements Serializable{	
	
	private static final long serialVersionUID = -8204310158577281079L;	
	private Grupo grupo;	
	private List<GrupoItem> grupoItems = new ArrayList<GrupoItem>();
	
	public GrupoTransferencia(){}
	
	public GrupoTransferencia(Grupo grupo, List<GrupoItem> grupoItems) {
		super();
		this.grupo = grupo;
		this.grupoItems = grupoItems;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	@XmlElementWrapper(name="grupoItems")
	@XmlElement(name="grupoItem")
	public List<GrupoItem> getGrupoItems() {
		return grupoItems;
	}
	
	
	public void setGrupoItems(List<GrupoItem> grupoItems) {
		this.grupoItems = grupoItems;
	}
	
	
	
}
