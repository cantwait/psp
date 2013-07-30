package com.pdvsa.psp.mule.component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



import org.mule.api.annotations.param.Payload;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.pdvsa.psp.model.xml.OpcInfoRegisterListResponse;
import com.pdvsa.psp.model.xml.OpcInfoRegisterMongo;
import com.pdvsa.psp.model.xml.OpcInfoRegisterRequest;

public class MongoFindComponent {
	
	private MongoTemplate mongoTemplate;
	
	
	public OpcInfoRegisterListResponse findItems(@Payload OpcInfoRegisterRequest request){
		OpcInfoRegisterListResponse response = new OpcInfoRegisterListResponse();
		List<OpcInfoRegisterMongo> items = new ArrayList<OpcInfoRegisterMongo>();
//		BasicQuery bq;
		if(request.getStationId() != null && request.getDesde() != null && request.getHasta() != null && !request.getDesde().after(request.getHasta())){			
			
			items = getMongoTemplate().find(new Query().addCriteria(Criteria.where("stationId").is(request.getStationId()).and("timestamp").gte(request.getDesde()).lte(request.getHasta())), OpcInfoRegisterMongo.class, "opcInfoRegister");
		}else if(request.getDesde() != null && request.getHasta() != null && !request.getDesde().after(request.getHasta())){
			
			items = getMongoTemplate().find(new Query().addCriteria(Criteria.where("timestamp").gte(changeDateFormat(request.getDesde())).lte(changeDateFormat(request.getHasta()))), OpcInfoRegisterMongo.class, "opcInfoRegister");
		}
		
		response.getListaopc().addAll(items);
		
		return response;
	}
	

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	@SuppressWarnings("unused")
	private Date changeDateFormat(Date d){
		Date newDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		String ano = String.valueOf(cal.get(Calendar.YEAR));
		String dia = String.valueOf(cal.get(Calendar.DATE));
		int mes = 0;
		String mesStr = new String();
		if((mes = cal.get(Calendar.MONTH) + 1) < 10){
			mesStr = "0" + String.valueOf(cal.get(Calendar.MONTH) + 1);
		}else{
			mesStr = String.valueOf(cal.get(Calendar.MONTH) + 1);
		}
		
		String formatedDate = ano + "-" + mesStr + "-" +dia; 
		System.out.println("formatedDate : " + formatedDate);  
		
		try {
			newDate =  sdf.parse(formatedDate);
		} catch (ParseException e) {			
			e.printStackTrace();
		}
		
		return newDate;
	}

}
