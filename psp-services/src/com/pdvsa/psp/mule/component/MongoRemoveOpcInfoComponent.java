package com.pdvsa.psp.mule.component;

import org.mule.api.annotations.param.Payload;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.pdvsa.psp.model.xml.OpcInfoRegisterMongo;
import com.pdvsa.psp.model.xml.OpcItemsTransfer;

public class MongoRemoveOpcInfoComponent {
	
	private MongoTemplate mongoTemplate;
	
	
	public void removeItems(@Payload OpcItemsTransfer request){
		
		for (OpcInfoRegisterMongo opc : request.getOpcItems()) {
			System.out.println("quitando objeto: " + opc.getTimestamp());
			getMongoTemplate().remove(opc, "opcInfoRegister");
		}
		
		
	}

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	

}
