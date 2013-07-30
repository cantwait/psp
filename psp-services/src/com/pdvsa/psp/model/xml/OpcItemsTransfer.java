package com.pdvsa.psp.model.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="opcItemsTransfer")
public class OpcItemsTransfer {
	
	private List<OpcInfoRegisterMongo> opcItems = new ArrayList<OpcInfoRegisterMongo>();
	
	public OpcItemsTransfer(){}
	
	public OpcItemsTransfer(List<OpcInfoRegisterMongo> opcItems){
		this.opcItems = opcItems;
	}

	@XmlElementWrapper(name="opcItems")
	@XmlElement(name="opc")
	public List<OpcInfoRegisterMongo> getOpcItems() {
		return opcItems;
	}

	public void setOpcItems(List<OpcInfoRegisterMongo> opcItems) {
		this.opcItems = opcItems;
	}
	
	

}
