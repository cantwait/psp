package com.pdvsa.psp.mule.component;

import org.mule.api.annotations.param.Payload;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.pdvsa.psp.model.xml.OpcInfoRegisterMongo;
import com.pdvsa.psp.model.xml.OpcItemsTransfer;

public class MongoRemoveTransferedItemsComponent {
	
	private MongoTemplate mongoTemplate;
	
	
	public void remove(@Payload OpcItemsTransfer request){
		
		for (OpcInfoRegisterMongo opc : request.getOpcItems()) {
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
