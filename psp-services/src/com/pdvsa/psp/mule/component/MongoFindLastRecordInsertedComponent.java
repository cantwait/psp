package com.pdvsa.psp.mule.component;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import com.pdvsa.psp.model.xml.OpcInfoRegisterMongo;

public class MongoFindLastRecordInsertedComponent {
	
	private MongoTemplate mongoTemplate;
	
	public OpcInfoRegisterMongo findLastRecord(){
		return getMongoTemplate().findOne(new Query().with(new Sort(new Order(Direction.DESC, "timestamp"))).limit(1), OpcInfoRegisterMongo.class, "opcInfoRegisterHistoric");
	}
	
	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

}
