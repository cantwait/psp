package com.pdvsa.psp.mule.component;

import org.mule.api.annotations.param.Payload;
import org.springframework.data.mongodb.core.MongoTemplate;
import com.pdvsa.psp.model.xml.OpcItemsTransfer;

public class MongoInsertItemToTransferComponent {

	private MongoTemplate mongoTemplate;

	public OpcItemsTransfer insert(@Payload OpcItemsTransfer payload) {
		getMongoTemplate().insert(payload.getOpcItems(), "opcInfoRegister");
		return payload;
	}

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

}
