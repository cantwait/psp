package org.home.psp.data;

import java.util.ArrayList;
import java.util.List;

public class OpcInfoRegisterListResponse {
	
	private String error;
	private List<OpcInfoRegister> infos = new ArrayList<OpcInfoRegister>();
	
	public OpcInfoRegisterListResponse(){}
	
	public OpcInfoRegisterListResponse(List<OpcInfoRegister> collection){
		this.infos = collection;
	}

	public List<OpcInfoRegister> getInfos() {
		return infos;
	}

	public void setInfos(List<OpcInfoRegister> infos) {
		this.infos = infos;
	}
	
	public void add(OpcInfoRegister opc){
		this.infos.add(opc);
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	
	
	

}
