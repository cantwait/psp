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
	 private static final SimpleDateFormat xmlFormater = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	
	public OpcInfoRegisterListResponse findItems(@Payload OpcInfoRegisterRequest request){
		OpcInfoRegisterListResponse response = new OpcInfoRegisterListResponse();
		List<OpcInfoRegisterMongo> items = new ArrayList<OpcInfoRegisterMongo>();
//		BasicQuery bq;
		if(request.getStationId() != null && request.getDesde() != null && request.getHasta() != null && !request.getDesde().after(request.getHasta())){			
			items = getMongoTemplate().find(new Query().addCriteria(Criteria.where("stationId").is(request.getStationId()).andOperator(Criteria.where("timestamp").gte(request.getDesde()), Criteria.where("timesptamp").lte(request.getHasta()))), OpcInfoRegisterMongo.class, "opcInfoRegisterHistoric");
		}else if(request.getDesde() != null && request.getHasta() != null && !request.getDesde().after(request.getHasta())){
			items = getMongoTemplate().find(new Query().addCriteria(Criteria.where("timestamp").gte(changeDateFormat(request.getDesde())).lte(changeDateFormat(request.getHasta()))), OpcInfoRegisterMongo.class, "opcInfoRegisterHistoric");
		}
		response.getItems().addAll(items);
		
		return response;
	}
	

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	@SuppressWarnings("unused")
	private static Date changeDateFormat(Date d){
		Date newDate = new Date();
//		Calendar newDate = Calendar.getInstance();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
		
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
		
		System.out.println(cal.getTime());
		
		String formatedDate = ano + "-" + mesStr + "-" +dia + "'T'" +hora+":"+minuto+":"+segundo+"'Z'"; 
//		String formatedDate = "2013-08-19 09:10:58";
		System.out.println("formatedDate : " + formatedDate);  
		
					try {
						newDate =  sdf.parse(formatedDate);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//		newDate = javax.xml.bind.DatatypeConverter.parseDateTime(cal.getTime().toString());
		
		return newDate;
	}
	
	public static void main(String... args){
		Date d = Calendar.getInstance().getTime();
		
//		Date newDate = changeDateFormat(d);
		
		
		
		System.out.println(formatXMLDate(d));
	}
	
	 //Use the JAXB data type conversion, since it already know the xml valid date format
    private static java.util.Date parseXMLDate(final String date) {
        Calendar cal = javax.xml.bind.DatatypeConverter.parseDateTime(date);
        
        return cal.getTime();
    }
     
    //convert to the string representation of the datetime format
    //NOTES: this should be the same as formatDate() but keep 
    // and preferred to use this one instead.  Just in case the JAXB
    // document DatetypeConverter change in the future.
    public static String formatXMLDate(final java.util.Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return javax.xml.bind.DatatypeConverter.printDateTime(cal);
    }
     
    // should yield the same results as formatXMLDate() method above.
    public static String formatDate(final java.util.Date date) {
        return xmlFormater.format(date);
    }

}
