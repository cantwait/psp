package com.pdvsa.psp.mule.component;

import org.mule.api.annotations.param.Payload;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.pdvsa.psp.model.xml.MongoLogger;

public class MongoInsertLogComponent {

	private MongoTemplate mongoTemplate;

	public MongoLogger insert(@Payload MongoLogger payload) {
		mongoTemplate.insert(payload, "opcErrorTransaction");
		return payload;
	}

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

}
