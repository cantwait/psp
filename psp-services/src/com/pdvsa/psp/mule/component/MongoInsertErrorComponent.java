package com.pdvsa.psp.mule.component;

import org.mule.api.annotations.param.Payload;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.pdvsa.psp.model.xml.TransferExceptionMongo;

public class MongoInsertErrorComponent {
	
	private MongoTemplate mongoTemplate;
	
	
	public TransferExceptionMongo insertError(@Payload TransferExceptionMongo payload){
		
		mongoTemplate.insert(payload, "OpcErrorTransaction");
		
		return payload;
	}
	

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}	

}
