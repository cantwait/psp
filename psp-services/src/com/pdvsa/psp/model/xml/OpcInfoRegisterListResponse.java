package com.pdvsa.psp.model.xml;

import java.util.ArrayList;
import java.util.List;



public class OpcInfoRegisterListResponse {
	
	public List<OpcInfoRegisterMongo> infos = new ArrayList<OpcInfoRegisterMongo>();
	
	public OpcInfoRegisterListResponse(){}
	
	public OpcInfoRegisterListResponse(List<OpcInfoRegisterMongo> collection){
		this.infos = collection;
	}

	public List<OpcInfoRegisterMongo> getInfos() {
		return infos;
	}

	public void setInfos(List<OpcInfoRegisterMongo> infos) {
		this.infos = infos;
	}
	
	public void add(OpcInfoRegisterMongo opc){
		this.infos.add(opc);
	}
	
	

}
