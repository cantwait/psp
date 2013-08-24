package com.pdvsa.psp.model.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="Opc.Response")
@XmlAccessorType(XmlAccessType.FIELD)
public class OpcInfoRegisterListResponse {
	
	@XmlElementWrapper(name="items")
	@XmlElement(name="Opc.Item")
	public List<OpcInfoRegisterMongo> items = new ArrayList<OpcInfoRegisterMongo>();
	
	public OpcInfoRegisterListResponse(){}
	
	
	public List<OpcInfoRegisterMongo> getItems() {
		return items;
	}

	public void setItems(List<OpcInfoRegisterMongo> items) {
		this.items = items;
	}	

	
	

}
