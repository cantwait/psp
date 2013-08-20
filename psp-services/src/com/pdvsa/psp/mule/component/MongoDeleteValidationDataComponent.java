package com.pdvsa.psp.mule.component;

import org.springframework.data.mongodb.core.MongoTemplate;

public class MongoDeleteValidationDataComponent {
	
	private MongoTemplate mongoTemplate;

	public void deleteAllHistoricRegisters(){
		getMongoTemplate().dropCollection("opcInfoRegisterValidation");
	}

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

}
