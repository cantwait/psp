package org.home.psp.dao;

import java.util.Collection;

import org.home.psp.data.OpcInfoRegister;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class OpcInfoRegisterDAO {
	
	private MongoTemplate mongoTemplate;
	
	public void saveEntities(Collection<OpcInfoRegister> opcs){
		for (OpcInfoRegister opcInfoRegister : opcs) {
			
			mongoTemplate.save(opcInfoRegister);
		}		
	}
	
	public OpcInfoRegister findOne(Long stationId){
		return mongoTemplate.findOne(Query.query(Criteria.where("stationId").is(stationId)), OpcInfoRegister.class);
	}
	
	public void updateOne(Long stationId){
		mongoTemplate.updateFirst(Query.query(Criteria.where("stationId").is(stationId)), new Update().set("propagado", Boolean.TRUE), OpcInfoRegister.class);
	}
	
	

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	

}
