package com.pdvsa.psp.mule.component;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import com.pdvsa.psp.model.xml.OpcInfoRegisterMongo;
import com.pdvsa.psp.model.xml.OpcItemsTransfer;

public class MongoFindItemsToTransferComponent {
	
	
	private MongoTemplate mongoTemplate;
	
	private Integer maxElements = 1000;
	
	
	public OpcItemsTransfer findAllItems(){
		
		OpcItemsTransfer response = new OpcItemsTransfer();
		List<OpcInfoRegisterMongo> items = new ArrayList<OpcInfoRegisterMongo>();		
		
		Query qry = new Query();
		qry.limit(maxElements);
		
		items = getMongoTemplate().find(qry, OpcInfoRegisterMongo.class, "opcInfoRegister");	
		response.getOpcItems().addAll(items);		
		return response;
	}
	

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}


	public Integer getMaxElements() {
		return maxElements;
	}


	public void setMaxElements(Integer maxElements) {
		this.maxElements = maxElements;
	}



}
