package com.pdvsa.psp.model.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="Opc.Response")
public class OpcInfoRegisterListResponse {
	
	public List<OpcInfoRegisterMongo> listaopc = new ArrayList<OpcInfoRegisterMongo>();
	
	public OpcInfoRegisterListResponse(){}
	
	public OpcInfoRegisterListResponse(List<OpcInfoRegisterMongo> collection){
		this.listaopc = collection;
	}
	
	@XmlElementWrapper(name="items")
	@XmlElement(name="Opc.Item")
	public List<OpcInfoRegisterMongo> getListaopc() {
		return listaopc;
	}

	public void setListaopc(List<OpcInfoRegisterMongo> listaopc) {
		this.listaopc = listaopc;
	}

	public void add(OpcInfoRegisterMongo opc){
		this.listaopc.add(opc);
	}
	
	

}
