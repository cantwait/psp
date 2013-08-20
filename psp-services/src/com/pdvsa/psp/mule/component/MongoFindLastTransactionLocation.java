package com.pdvsa.psp.mule.component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.mule.api.annotations.param.Payload;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.pdvsa.psp.model.Localidad;
import com.pdvsa.psp.model.xml.OpcInfoRegisterMongo;

public class MongoFindLastTransactionLocation {
	
	private MongoTemplate mongoTemplate;
	
	
	public List<OpcInfoRegisterMongo> getLastItemsRecieved(@Payload Collection<Localidad> localidades){
		
		List<OpcInfoRegisterMongo> lastItems = new ArrayList<OpcInfoRegisterMongo>();
		
		for (Localidad l : localidades) {
			
			OpcInfoRegisterMongo item = getMongoTemplate().findOne(new Query().addCriteria(Criteria.where("localidadNombre").is(l.getNombre())).with(new Sort(new Order(Direction.DESC, "timestamp"))).limit(1), OpcInfoRegisterMongo.class, "opcInfoRegisterHistorico");
			
			if(item != null){
				lastItems.add(item);
			}

		}
		
		return lastItems;
		
	}

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	
	

}
