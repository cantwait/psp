package com.pdvsa.psp.mule.component;

import org.springframework.data.mongodb.core.MongoTemplate;

public class MongoDeleteHistoricComponent {
	
	private MongoTemplate mongoTemplate;

	public void deleteAllHistoricRegisters(){
		getMongoTemplate().dropCollection("opcInfoRegisterHistoric");
	}

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

}
