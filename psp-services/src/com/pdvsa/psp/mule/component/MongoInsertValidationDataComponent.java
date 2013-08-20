package com.pdvsa.psp.mule.component;

import org.mule.api.annotations.param.Payload;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.pdvsa.psp.model.xml.OpcItemsTransfer;

public class MongoInsertValidationDataComponent {
	
private MongoTemplate mongoTemplate;
	
	
	public OpcItemsTransfer saveHistoricItems(@Payload OpcItemsTransfer request){
		getMongoTemplate().insert(request.getOpcItems(), "opcInfoRegisterValidation");
		return request;
	}
	

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

}
