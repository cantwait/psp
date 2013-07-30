package com.pdvsa.psp.mule.component;

import org.mule.api.annotations.param.Payload;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.pdvsa.psp.model.xml.OpcInfoRegisterMongo;
import com.pdvsa.psp.model.xml.OpcInfoRegisterRequest;

public class MongoFindLastRecordComponent {
	
	private MongoTemplate mongoTemplate;
	
	public OpcInfoRegisterMongo findLastRecord(@Payload OpcInfoRegisterRequest idServidor){		
		return getMongoTemplate().findOne(new Query().addCriteria(Criteria.where("stationId").is(idServidor.getStationId())).with(new Sort(new Order(Direction.DESC, "timestamp"))).limit(1), OpcInfoRegisterMongo.class, "opcInfoRegisterHistorico");
	}
	

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	

}
