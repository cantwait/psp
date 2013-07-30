package com.pdvsa.psp.mule.component;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.pdvsa.psp.model.xml.OpcInfoRegisterListResponse;
import com.pdvsa.psp.model.xml.OpcInfoRegisterMongo;
import com.pdvsa.psp.model.xml.OpcItemsTransfer;

public class MongoFindOpcTransferComponent {
	
	
	private MongoTemplate mongoTemplate;
	
	
	public OpcItemsTransfer findAllItems(){
		
		OpcItemsTransfer response = new OpcItemsTransfer();
		List<OpcInfoRegisterMongo> items = new ArrayList<OpcInfoRegisterMongo>();		
		items = getMongoTemplate().findAll(OpcInfoRegisterMongo.class, "opcInfoRegister");	
		response.getOpcItems().addAll(items);		
		return response;
	}
	

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}	

}
