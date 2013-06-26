package com.pdvsa.psp.model.xml;

import java.util.ArrayList;
import java.util.List;



public class OpcInfoRegisterListResponse {
	
	public List<OpcInfoRegisterMongo> listaopc = new ArrayList<OpcInfoRegisterMongo>();
	
	public OpcInfoRegisterListResponse(){}
	
	public OpcInfoRegisterListResponse(List<OpcInfoRegisterMongo> collection){
		this.listaopc = collection;
	}
	
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
