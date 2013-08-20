package com.pdvsa.psp.mule.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.pdvsa.psp.model.xml.OpcInfoRegisterListResponse;
import com.pdvsa.psp.model.xml.OpcInfoRegisterMongo;

@Path("/")
public class MongoFindItemsPentahoRest {

	private MongoTemplate mongoTemplate;

	@GET
	@Produces("text/xml")
	@Path("servicio")
	public OpcInfoRegisterListResponse findHistoricOpcData(
			@QueryParam(value = "pais") String pais,
			@QueryParam(value = "region") String region,
			@QueryParam(value = "localidad") String localidad,
			@QueryParam(value = "desde") Date desde,
			@QueryParam(value = "hasta") Date hasta) {
		OpcInfoRegisterListResponse list = new OpcInfoRegisterListResponse();
		List<OpcInfoRegisterMongo> items = new ArrayList<OpcInfoRegisterMongo>();
		Query qry = new Query();
		
		if((pais != null && pais.trim().length() > 0) && (region != null && region.trim().length() > 0)){
			qry.addCriteria(Criteria.where("paisNombre").is(pais).and("regionNombre").is(region).and("localidadNombre").is(localidad).and("timestamp").gte(changeDateFormat(desde)).and("timestamp").lte(changeDateFormat(hasta)));
		}
		
		items = getMongoTemplate().find(qry, OpcInfoRegisterMongo.class, "opcInfoRegisterHistoric");
		
		if(items != null && items.size() > 0){
			list.getListaopc().addAll(items);
		}
				
		return list;

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

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

}
