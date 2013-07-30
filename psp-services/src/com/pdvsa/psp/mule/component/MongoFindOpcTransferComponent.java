package com.pdvsa.psp.mule.component;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.pdvsa.psp.model.xml.OpcInfoRegisterListResponse;
import com.pdvsa.psp.model.xml.OpcInfoRegisterMongo;

public class MongoFindOpcTransferComponent {
	
	
	private MongoTemplate mongoTemplate;
	
	
	public OpcInfoRegisterListResponse findAllItems(){
		
		OpcInfoRegisterListResponse response = new OpcInfoRegisterListResponse();
		List<OpcInfoRegisterMongo> items = new ArrayList<OpcInfoRegisterMongo>();		
		items = getMongoTemplate().findAll(OpcInfoRegisterMongo.class, "opcInfoRegister");	
		response.getListaopc().addAll(items);		
		return response;
	}
	

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}	

}
