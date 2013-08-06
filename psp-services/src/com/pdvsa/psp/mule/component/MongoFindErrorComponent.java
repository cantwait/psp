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

import com.mongodb.CommandResult;
import com.pdvsa.psp.model.xml.OpcErrorMongoRequest;
import com.pdvsa.psp.model.xml.OpcErrorResponse;
import com.pdvsa.psp.model.xml.TransferExceptionMongo;

public class MongoFindErrorComponent {
	
	private MongoTemplate mongoTemplate;

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	public OpcErrorResponse findErrores(@Payload OpcErrorMongoRequest payload){
		List<TransferExceptionMongo> errores = new ArrayList<TransferExceptionMongo>();
		OpcErrorResponse respuesta = new OpcErrorResponse();
		getMongoTemplate().find(new Query(), TransferExceptionMongo.class, "OpcErrorTransaction");
		errores = getMongoTemplate().find(new Query().addCriteria(Criteria.where("fecha").gte(changeDateFormat(payload.getDesde())).lte(changeDateFormat(payload.getHasta()))), TransferExceptionMongo.class, "OpcErrorTransaction");
		
		if(errores.size() > 0){
			respuesta.getErrores().addAll(errores);
		}
		System.out.println("Obtienes respuesta valida: " + respuesta.getErrores().size());
		return respuesta;
	}
	
	@SuppressWarnings("unused")
	private Date changeDateFormat(Date d){
		Date newDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		String ano = String.valueOf(cal.get(Calendar.YEAR));
		String hora = String.valueOf(cal.get(Calendar.HOUR_OF_DAY));
		String minuto = String.valueOf(cal.get(Calendar.MINUTE));
		String segundo = String.valueOf(cal.get(Calendar.SECOND));
		int mes = 0;
		int dia = 0;
		String mesStr = new String();
		String diaStr = new String();
		if((dia = cal.get(Calendar.DATE)) < 10){
			diaStr = "0"+dia;
		}else{
			diaStr = String.valueOf(cal.get(Calendar.DATE));
		}
		if((mes = cal.get(Calendar.MONTH) + 1) < 10){
			mesStr = "0" + String.valueOf(cal.get(Calendar.MONTH) + 1);
		}else{
			mesStr = String.valueOf(cal.get(Calendar.MONTH) + 1);
		}
		
		String formatedDate = ano + "-" + mesStr + "-" +dia + " " +hora+":"+minuto+":"+segundo; 
		System.out.println("formatedDate : " + formatedDate);  
		
		try {
			newDate =  sdf.parse(formatedDate);
		} catch (ParseException e) {			
			e.printStackTrace();
		}
		
		return newDate;
	}


}
